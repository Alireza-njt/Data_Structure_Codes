// name : Alireza Nejati
// Student Id : 4011262156

package com.company.java;

public class BinaryTree {

    static public void insert_new_node(Node Dummy_node, final Node new_node) {
        Node Current_node = Dummy_node;
        while (true) {
            if (Current_node.getKey() >= new_node.getKey()) {
                if (Current_node.getLeft_Child() == null) {
                    Current_node.setLeft_Child(new_node);
                    break;
                } else {
                    Current_node = Current_node.getLeft_Child();
                }
            } else {
                if (Current_node.getRight_Child() == null) {
                    Current_node.setRight_Child(new_node);
                    break;
                } else {
                    Current_node = Current_node.getRight_Child();
                }
            }
        }
    }

    static public void inorder_traversal(Node node) {
        if (node != null) {
            inorder_traversal(node.getLeft_Child());
            System.out.print(node);
            inorder_traversal(node.getRight_Child());
        }
    }
}