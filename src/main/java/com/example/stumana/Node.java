package com.example.stumana;

public class Node {
    Student info;
    Node next;

    Node(Student info) {
        this.info = info;
        this.next = null;
    }
}