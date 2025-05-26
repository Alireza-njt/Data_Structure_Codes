// name : Alireza Nejati
// Student ID : 4011262156

/* Copyright (C) Sayed Kamaledin Ghiasi-Shirazi, Ferdowsi Univerity of Mashhad
 * 23 Azar 1403(Hijri shamsi)
 * 13 Dec 2024
 * Author: Sayed Kamaledin Ghiasi-Shirazi, Ali Moghaddaszadeh, Melika Zaihi, MohammadMahdi Rahneshin, Sajad zirak
 *
 * 
 */

package ac.um.ds;

public class HashTable<K extends Comparable<K> , V> implements Map<K, V> {
	private class KeyValuePair<K, V> {
		public K key;
		public V value;
	}

	private int size;
	private int capacity;

	/**********************************/

	private HashFunction h1;
	private HashFunction h2;

	/**********************************/

	private float maxLoadingFactor;

	/**********************************/

	private boolean[] stateTable;    // 0 => empty ,  1 => full
	private KeyValuePair<K, V>[] table;

	// My added function to the code !!!!!!!!!
	private int capacity_generator (int old_capacity) {
		old_capacity *= 2 ;
		old_capacity ++ ;
		LOOP : for (;;old_capacity++) {
			for (int i=2 ; i<old_capacity ; i++) {
				if (old_capacity % i == 0) continue LOOP;
			}
			return old_capacity ;
		}
	}



	public HashTable(HashFunction h1, HashFunction h2, float maxLoadingFactor, int initCapacity) {
		this.h1 = h1;
		this.h2 = h2;
		this.maxLoadingFactor = maxLoadingFactor ;
		this.capacity = initCapacity ;
		size = 0 ;
		stateTable = new boolean[capacity] ;
		table = new KeyValuePair[capacity] ;
	}

	@Override
	public void assign(K key, V value) {
		if (hasKey(key)) {
			for (int i = 0; i < capacity; i++) {
				if (table[i].key == key) {
					table[i].value = value;
					break;
				}
			}
		} else {
			int hash_number_1 = h1.hash(key) ;
			int hash_number_2 = h2.hash(key) ;
			int index = hash_number_1 % capacity;
			for (int i = 0; i < capacity; i++) {
				if (!stateTable[index]) {
					table[index] = new KeyValuePair<K, V>();
					table[index].key = key;
					table[index].value = value;
					stateTable[index] = true;
					size++;
					break;
				} else {
					index = (hash_number_1 + (i + 1) * hash_number_2) % capacity;
				}
			}
		}
		if (size > capacity * maxLoadingFactor) {
			int newCapacity = capacity_generator(capacity);
			KeyValuePair<K, V>[] newTable = new KeyValuePair[newCapacity];
			boolean[] newStateTable = new boolean[newCapacity];
			for (int i = 0; i < capacity; i++) {
				if (stateTable[i]) {
					int hash_number_1 = h1.hash(table[i].key) ;
					int hash_number_2 = h2.hash(table[i].key) ;
					int index = hash_number_1 % newCapacity;
					for (int j = 0; j < newCapacity; j++) {
						if (!newStateTable[index]) {
							newTable[index] = new KeyValuePair<K, V>();
							newTable[index].key = table[i].key;
							newTable[index].value = table[i].value;
							newStateTable[index] = true;
							break;
						} else {
							index = (hash_number_1 + (j + 1) * hash_number_2) % newCapacity;
						}
					}
				}
			}
			table = newTable;
			stateTable = newStateTable;
			capacity = newCapacity;
		}
	}

	@Override
	public void remove(K key) {
		if (hasKey(key)) {
			for (int i = 0; i < capacity; i++) {
				if (stateTable[i]) {
					if (table[i].key == key) {
						stateTable[i] = false;
						break;
					}
				}
			}
			KeyValuePair<K, V>[] newTable = new KeyValuePair[capacity];
			boolean[] newStateTable = new boolean[capacity];
			for (int i = 0; i < capacity; i++) {
				if (stateTable[i]) {
					int index = h1.hash(table[i].key) % capacity;
					for (int j = 0; j < capacity; j++) {
						if (!newStateTable[index]) {
							newTable[index] = new KeyValuePair<K, V>();
							newTable[index].key = table[i].key;
							newTable[index].value = table[i].value;
							newStateTable[index] = true;
							break;
						} else {
							index = (h1.hash(table[i].key) + (j + 1) * h2.hash(table[i].key)) % capacity;
						}
					}
				}
			}
			table = newTable ;
			stateTable =newStateTable;
			size -- ;
		}
	}

	public int capacity() {
		return capacity;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean hasKey(K key) {
		int hash_number_1 = h1.hash(key) ;
		int hash_number_2 = h2.hash(key) ;
		int index = hash_number_1 % capacity;
		for (int i = 0; i < capacity; i++) {
			if (stateTable[index]) {
				if (table[index].key == key) {
					return true;
				}
			}
			index = (hash_number_1 + (i + 1) * hash_number_2) % capacity;
		}
		return false;
	}

	@Override
	public V get(K key) {
		int hash_number_1 = h1.hash(key) ;
		int hash_number_2 = h2.hash(key) ;
		int index = hash_number_1 % capacity;
		for (int i = 0; i < capacity ;  i++) {
			if (stateTable[index]) {
				if (table[index].key == key) {
					return table[index].value;
				}
			}
			index = (hash_number_1 + (i + 1) * hash_number_2) % capacity;
		}
		return (V) new Exception();
	}

	public void print() {
		for (int i = 0; i < capacity; i++)
			System.out.print(String.valueOf(i) + "\t");
		System.out.println();

		for (int i = 0; i < capacity; i++) {
			if (stateTable[i])
				System.out.print(String.valueOf(table[i].value) + "\t");
			else
				System.out.print("*" + "\t");
		}
		System.out.println();
	}

	public String toString() {
		String s = "";
		s += "size:" + String.valueOf(size) + "\n";
		for (int i = 0; i < capacity - 1; i++) {
			if (stateTable[i])
				s += String.valueOf(table[i].key) + "," + String.valueOf(table[i].value) + ", ";
			else
				s += "-1, ";
		}
		if (stateTable[capacity - 1])
			s += String.valueOf(table[capacity - 1].key) + "," + String.valueOf((table[capacity - 1]).value);
		else
			s += "-1";
		return s;
	}

}
