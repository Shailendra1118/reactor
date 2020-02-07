package com.example.demo;

public class LinkedList {


    static class Node{
        int val;
        Node next;
        Node(int val){
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);

        reverseIt(head);
        printIt(head);

    }

    private static void printIt(Node node) {
        while(node != null){
            System.out.print(node.val+"-->");
            node = node.next;
        }
        System.out.println("null");
    }

    private static void reverseIt(Node head) {
        Node current = head;
        while(current.next != null){
            Node nxt = current.next;
            Node tmp = nxt.next;

            nxt.next = head;
            current.next = tmp;
            head = nxt;

            printIt(head);
        }
    }
}
