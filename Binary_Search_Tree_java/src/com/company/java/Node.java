// name : Alireza Nejati
// Student Id : 4011262156

package com.company.java;

public class Node {

    private final int Key;
    private Node Left_Child;
    private Node Right_Child;

    public Node(int Key, Node Left_Child, Node Right_Child) {
        this.Key = Key;
        this.Right_Child = Right_Child;
        this.Left_Child = Left_Child;
    }

    public int getKey() {
        return Key;
    }

    public Node getLeft_Child() {
        return Left_Child;
    }

    public Node getRight_Child() {
        return Right_Child;
    }

    public void setLeft_Child(Node left_Child) {
        Left_Child = left_Child;
    }

    public void setRight_Child(Node right_Child) {
        Right_Child = right_Child;
    }

    @Override
    public String toString() {
        return Key + " , ";
    }
}
