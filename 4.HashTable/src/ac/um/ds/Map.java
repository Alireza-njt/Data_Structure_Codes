/* Copyright (C) Sayed Kamaledin Ghiasi-Shirazi, Ferdowsi Univerity of Mashhad
 * 23 Azar 1403(Hijri shamsi)
 * 13 Dec 2024
 * Author: Sayed Kamaledin Ghiasi-Shirazi, Ali Moghaddaszadeh, Melika Zaihi, MohammadMahdi Rahneshin, Sajad zirak
 *
 * 
 */
package ac.um.ds;

public interface Map< K extends Comparable<K> ,  V> {
    void assign(K key, V value);
    void remove(K key);
    boolean hasKey(K key);
    V get(K key);
    int size();
}

