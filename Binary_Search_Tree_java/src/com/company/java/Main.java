// name : Alireza Nejati
// Student Id : 4011262156

package com.company.java;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Node Dummy_Root = new Node(Integer.MAX_VALUE, null, null);
        System.out.print("Enter node Count : ");
        Scanner scanner = new Scanner(System.in) ;
        int n = scanner.nextInt();
        int[] Keys = generateUniqueRandomArray(n);
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(Keys[i], null, null);
        }
        System.out.println("________________________________________________________________________________________________________________________________");
        System.out.println("Unsorted Array : ");
        printArray(Keys);
        System.out.println("________________________________________________________________________________________________________________________________");

        for (int i = 0; i < n; i++) {
            BinarySearchTree.insert_new_node(Dummy_Root, nodes[i]);
        }

        System.out.println("Sorted Array : ");

        Node First_node = Dummy_Root.getLeft_Child();
        BinarySearchTree.inorder_traversal(First_node);

    }

    public static int[] generateUniqueRandomArray(int n) {

        Random random = new Random();
        HashSet<Integer> uniqueNumbers = new HashSet<Integer>();
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            int randomNumber;
            do {
                randomNumber = 10 + random.nextInt(90); // 10 , 11 , 12 , ... , 99
            } while (uniqueNumbers.contains(randomNumber));

            uniqueNumbers.add(randomNumber);
            result[i] = randomNumber;
        }

        return result;
    }

    public static void printArray(int[] array) {
        for (int a : array) {
            System.out.print(a + " , ");
        }
        System.out.println();
    }

}