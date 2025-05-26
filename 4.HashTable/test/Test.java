/* Copyright (C) Sayed Kamaledin Ghiasi-Shirazi, Ferdowsi Univerity of Mashhad
 * 23 Azar 1403(Hijri shamsi)
 * 13 Dec 2024
 * Author: Sayed Kamaledin Ghiasi-Shirazi, Ali Moghaddaszadeh, Melika Zaihi, MohammadMahdi Rahneshin, Sajad zirak
 *
 * 
 */
import ac.um.ds.HashFunction;
import ac.um.ds.HashTable;

//For test1
class hashFunc1Test1 implements HashFunction<Integer> {
    @Override
    public int hash(Integer key) {
        int map[] = {0, 5};
        return key + map[key % 2];
    }
}

class hashFunc2Test1 implements HashFunction<Integer> {
    @Override
    public int hash(Integer key) {
        return 1;
    }
}


//For test2
class hashFunc1Test2 implements HashFunction<Integer> {
    @Override
    public int hash(Integer key) {
        return key;
    }
}

class hashFunc2Test2 implements HashFunction<Integer> {
    @Override
    public int hash(Integer key) {
        return 1;
    }
}

//For testGet
class hashFuncTestGet implements HashFunction<Integer> {
    @Override
    public int hash(Integer key) {
        return key;
    }
}

class hashFunc2TestGet implements HashFunction<Integer> {
    @Override
    public int hash(Integer key) {
        return 1;
    }
}

public class Test {

    static void testAssignRemove1(){
        int test[] = {6, 12, 34, 29, 28, 11, 23, 29, 7, 0, 62, 96};
        int deleteItems[] = {34, 12, 40, 62};
        int capacity[] = {7, 7, 7, 7, 17, 17, 17, 17, 17, 17, 17, 17};
        int result[][] = {
            {-1, -1, -1, -1, -1, -1, 6},
            {-1, -1, -1, -1, -1, 12, 6},
            {34, -1, -1, -1, -1, 12, 6},
            {34, 29, -1, -1, -1, 12, 6},
            {34, 29, -1, -1, -1, -1, 6, -1, -1, -1, -1, 28, 12, -1, -1, -1, -1},
            {34, 29, -1, -1, -1, -1, 6, -1, -1, -1, -1, 28, 12, -1, -1, -1, 11},
            {34, 29, -1, -1, -1, -1, 6, -1, -1, -1, -1, 28, 12, 23, -1, -1, 11},
            {34, 29, -1, -1, -1, -1, 6, -1, -1, -1, -1, 28, 12, 23, -1, -1, 11},
            {34, 29, -1, -1, -1, -1, 6, -1, -1, -1, -1, 28, 12, 23, 7, -1, 11},
            {34, 29, 0, -1, -1, -1, 6, -1, -1, -1, -1, 28, 12, 23, 7, -1, 11},
            {34, 29, 0, -1, -1, -1, 6, -1, -1, -1, -1, 28, 12, 23, 7, 62, 11},
            {34, 29, 0, 96, -1, -1, 6, -1, -1, -1, -1, 28, 12, 23, 7, 62, 11}};
        int deleteResult[][] = {
            {29, 0, 96, -1, -1, -1, 6, -1, -1, -1, -1, 28, 12, 23, 7, 62, 11},
            {29, 0, -1, -1, -1, -1, 6, -1, -1, -1, -1, 28, 23, 7, 62, 96, 11},
            {29, 0, -1, -1, -1, -1, 6, -1, -1, -1, -1, 28, 23, 7, 62, 96, 11},
            {29, 0, -1, -1, -1, -1, 6, -1, -1, -1, -1, 28, 23, 7, 96, -1, 11}};

        HashTable<Integer, Integer> hashTable;
        hashTable = new HashTable<Integer, Integer>(new hashFunc1Test1(), new hashFunc2Test1(), .7f, 7);

        for (int i = 0; i < 12; i++)
        {
            System.out.println( "Added: " + String.valueOf(test[i]));
            hashTable.assign(test[i], test[i]);

            System.out.println( "Capacity is: " + String.valueOf(hashTable.capacity()));
            System.out.println( "             " + String.valueOf(capacity[i])+ " <<Correct answer");

            if(capacity[i] != hashTable.capacity())
                throw new RuntimeException("Incorrect capacity!");

            hashTable.print();
            for (int j = 0; j < capacity[i]; j++)
            {
                if (result[i][j] != -1)
                    System.out.print( String.valueOf(result[i][j]) + "\t");
                else
                    System.out.print( "*  \t");
            }
            System.out.println( "<<Correct answer\n");


            String resString = convertToString(result[i], capacity[i]);
            String studentString = hashTable.toString();
            if(resString.compareTo(studentString) != 0){
                System.out.println(resString);
                System.out.println(studentString);
                throw new RuntimeException("Incorrect assign!");
            }
        }

        for (int i = 0; i < 4; i++)
        {
            System.out.println( "Remove: " + String.valueOf(deleteItems[i]));
            hashTable.remove(deleteItems[i]);

            hashTable.print();
            for (int j = 0; j < 17; j++)
            {
                if (deleteResult[i][j] != -1)
                    System.out.print( String.valueOf(deleteResult[i][j]) + "\t");
                else
                    System.out.print( "* \t");
            }
            System.out.println( "<<Correct answer\n");

            String resString = convertToString(deleteResult[i], 17);
            if(resString.compareTo(hashTable.toString()) != 0){
                throw new RuntimeException("Incorrect remove!");
            }
        }
    }

    static void testAssignRemove2(){
        int test[] = {14, 31, 2, 5, 48, 65, 82, 99, 116, 133, 167};
        int deleteItems[] = {31, 116, 16, 14};
        int capacity[] = {7, 7, 7, 7, 17, 17, 17, 17, 17, 17, 17};
        int result[][] = {{14, -1, -1, -1, -1, -1, -1},
            {14, -1, -1, 31, -1, -1, -1},
            {14, -1, 2, 31, -1, -1, -1},
            {14, -1, 2, 31, -1, 5, -1},
            {-1, -1, 2, -1, -1, 5, -1, -1, -1, -1, -1, -1, -1, -1, 14, 31, 48},
            {65, -1, 2, -1, -1, 5, -1, -1, -1, -1, -1, -1, -1, -1, 14, 31, 48},
            {65, 82, 2, -1, -1, 5, -1, -1, -1, -1, -1, -1, -1, -1, 14, 31, 48},
            {65, 82, 2, 99, -1, 5, -1, -1, -1, -1, -1, -1, -1, -1, 14, 31, 48},
            {65, 82, 2, 99, 116, 5, -1, -1, -1, -1, -1, -1, -1, -1, 14, 31, 48},
            {65, 82, 2, 99, 116, 5, 133, -1, -1, -1, -1, -1, -1, -1, 14, 31, 48},
            {65, 82, 2, 99, 116, 5, 133, 167, -1, -1, -1, -1, -1, -1, 14, 31, 48},};
        int deleteResult[][] = { 
            {82, 99, 2, 116, 133, 5, 167, -1, -1, -1, -1, -1, -1, -1, 14, 48, 65},
            {82, 99, 2, 133, 167, 5, -1, -1, -1, -1, -1, -1, -1, -1, 14, 48, 65},
            {82, 99, 2, 133, 167, 5, -1, -1, -1, -1, -1, -1, -1, -1, 14, 48, 65},
            {99, 133, 2, 167, -1, 5, -1, -1, -1, -1, -1, -1, -1, -1, 48, 65, 82},
        };

        HashTable<Integer, Integer> hashTable;
        hashTable = new HashTable<Integer, Integer>(new hashFunc1Test2(), new hashFunc2Test2(), .7f, 7);

        for (int i = 0; i < 11; i++)
        {
            System.out.println( "Added: " + String.valueOf(test[i]));
            hashTable.assign(test[i], test[i]);

            System.out.println( "Capacity is: " + String.valueOf(hashTable.capacity()));
            System.out.println( "             " + String.valueOf(capacity[i]) + " <<Correct answer");

            if(capacity[i] != hashTable.capacity())
                throw new RuntimeException("Incorrect capacity!");

            hashTable.print();
            for (int j = 0; j < capacity[i]; j++)
            {
                if (result[i][j] != -1)
                    System.out.print( String.valueOf(result[i][j]) + "\t");
                else
                    System.out.print( "* \t");
            }
            System.out.println( "<<Correct answer\n");

            String resString = convertToString(result[i], capacity[i]);
            if(resString.compareTo(hashTable.toString()) != 0){
                throw new RuntimeException("Incorrect assign!");
            }
        }

        for (int i = 0; i < 4; i++)
        {
            System.out.println( "Remove: " + String.valueOf(deleteItems[i]));
            hashTable.remove(deleteItems[i]);

            hashTable.print();
            for (int j = 0; j < 17; j++)
            {
                if (deleteResult[i][j] != -1)
                    System.out.print( String.valueOf(deleteResult[i][j]) + "\t");
                else
                    System.out.print( "* \t");
            }
            System.out.println( "<<Correct answer\n");

            String resString = convertToString(deleteResult[i], 17);
            if(resString.compareTo(hashTable.toString()) != 0){
                throw new RuntimeException("Incorrect remove!");
            }
        }
    }

    static void testGet(){

        int test[] = {3, 14, 8, 6, 25};
        int capacity[] = {5, 5, 11, 11, 11};
        int correctGet[] = {103, 125, 108};

        HashTable<Integer, X> hashTable = new HashTable<Integer, X>(new hashFuncTestGet(), new hashFunc2TestGet(), .5f, 5);
        for (int i = 0; i < 5; i++)
        {
            System.out.println( "Added: " + test[i]);
            hashTable.assign(test[i], new X(100 + test[i]));

            System.out.println( "Capacity is: " + String.valueOf(hashTable.capacity()));
            System.out.println( "             " + String.valueOf(capacity[i]) + " <<Correct answer");

            if(capacity[i] != hashTable.capacity())
                throw new RuntimeException("Incorrect capacity!");
        }

        System.out.println( "Get key: 3");
        System.out.println( hashTable.get(3).getValue());
        System.out.println( correctGet[0] + " <<Correct answer");

        if(hashTable.get(3).getValue() != correctGet[0]) {
            System.out.println( String.valueOf(hashTable.get(3).getValue()) + "*-*" +
                                String.valueOf(correctGet[0]));
            hashTable.print();
            throw new RuntimeException("Incorrect get!");
        }

        System.out.println( "Get key: 25");
        System.out.println( hashTable.get(25).getValue());
        System.out.println( String.valueOf(correctGet[1]) + " <<Correct answer");

        if(hashTable.get(25).getValue() != correctGet[1])
            throw new RuntimeException("Incorrect get!");

        System.out.println( "Get key: 8");
        System.out.println( hashTable.get(8).getValue());
        System.out.println( String.valueOf(correctGet[2]) + " <<Correct answer");

        if(hashTable.get(8).getValue() != correctGet[2])
            throw new RuntimeException("Incorrect get!");
    }

    static String convertToString(int res[], int n){
        int size = 0;
        String s = "";
        for(int i = 0; i < n; i++){
            if(res[i] != -1)
                size++;
        }
        s += "size:" + String.valueOf(size) + "\n";
        for(int i = 0; i < n-1; i++){
            if(res[i] != -1)
                s += String.valueOf(res[i]) + "," + String.valueOf(res[i]) + ", ";
            else
                s += "-1, ";
        }
        if(res[n-1] != -1)
            s += String.valueOf(res[n-1]) + "," + String.valueOf(res[n-1]);
        else
            s += "-1";

        return s;
    }

    public static void main(String[] args) {
        System.out.println( "=============================================");
        System.out.println( "Test 1. Assign/Remove");
        System.out.println( "=============================================");
        testAssignRemove1();
        System.out.println( "\n");
        System.out.println( "=============================================");
        System.out.println( "Test 2. Assign/Remove");
        System.out.println( "=============================================");
        testAssignRemove2();
        System.out.println( "\n\n");
        System.out.println( "=============================================");
        System.out.println( "Test 3. Get (operator[])");
        System.out.println( "=============================================");
        testGet();
    }
}
