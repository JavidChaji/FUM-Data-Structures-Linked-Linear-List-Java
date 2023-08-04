/* Copyright (C) Kamaledin Ghiasi-Shirazi, Ferdowsi Univerity of Mashhad
 * 24 Aban 1396(Hijri shamsi)
 * 15 November 2017
 * Authors: Pooya Hosseinzadeh, Fatemeh Tabadkani, Kamaledin Ghiasi-Shirazi
 */
package ac.um.ds;
import java.util.Iterator;


public class LinkedLinearList<T> {
    private class Node<T> {
        public T data;
        public Node prev;
        public Node next;
    }

    public class LinkedListForwardIterator<T> implements Iterator<T> {
        private Node<T> currNode;

        //constructors:
        public LinkedListForwardIterator() {
            this.currNode = null;
        }

        public LinkedListForwardIterator(Node<T> node) {
            this.currNode = node;
        }
        public T retrieve() {//it's get equivalent
            return this.currNode.data;
        }

        //overloading operators:
        @Override
        public T next() { // preincrement
            // Write your code here
            currNode = currNode.next;
            return retrieve();
        }

        @Override
        public boolean hasNext() {
            return currNode.next != headerNode;
        }
    }

    public class LinkedListBackwardIterator<T> implements Iterator<T> {
        Node<T> currNode;

        public LinkedListBackwardIterator() {
            this.currNode = null;
        }

        public LinkedListBackwardIterator(Node<T> node) {
            this.currNode = node;
        }

        public T retrieve() {//it's get equivalent
            return this.currNode.data;
        }

        //overloading operators:
        @Override
        public T next() { // preincrement
            // Write your code here
            currNode = currNode.prev;
            return retrieve();
        }

        @Override
        public boolean hasNext() {
            return currNode.prev != headerNode;
        }
    }

    public LinkedLinearList() {
        mSize = 0;
        headerNode = new Node<T>();
        headerNode.next = headerNode;
        headerNode.prev = headerNode;
    }

    public LinkedListForwardIterator<T> ForwardIterator() {
        return new LinkedListForwardIterator(headerNode);
    }

    public LinkedListBackwardIterator<T> BackwardIterator() {
        return new LinkedListBackwardIterator(headerNode);
    }

    // The returned iterator would point to the inserted element.
    public LinkedListForwardIterator<T> insert(LinkedListForwardIterator<T> it, T data) throws IndexOutOfBoundsException {
        // Write your code here
        Node temp = new Node();
        temp.data = data;
        temp.next = it.currNode.next;
        temp.prev = it.currNode;
        it.currNode.next = temp;
        it.currNode.next.next.prev = temp;
        mSize++;
        return new LinkedListForwardIterator<>(temp);
    }

    // The returned iterator would point to the inserted element.
    public LinkedListBackwardIterator<T> insert(LinkedListBackwardIterator<T> it, T data) throws IndexOutOfBoundsException {
        // Write your code here
        Node temp = new Node();
        temp.data = data;
        temp.prev = it.currNode.prev;
        temp.next = it.currNode;
        it.currNode.prev = temp;
        it.currNode.prev.prev.next = temp;
        mSize++;
        return new LinkedListBackwardIterator<>(temp);
    }

    // The returned iterator would point to the element after the removed one.
    public LinkedListForwardIterator<T> remove(LinkedListForwardIterator<T> it) throws IndexOutOfBoundsException {
        // Write your code here
        it.currNode.prev.next = it.currNode.next;
        it.currNode.next.prev = it.currNode.prev;
        mSize--;
        return new LinkedListForwardIterator<>(it.currNode.next);
    }

    // The returned iterator would point to the element before the removed one.
    public LinkedListBackwardIterator<T> remove(LinkedListBackwardIterator<T> it) throws IndexOutOfBoundsException {
        // Write your code here
        it.currNode.next.prev = it.currNode.prev;
        it.currNode.prev.next = it.currNode.next;
        mSize--;
        return new LinkedListBackwardIterator<>(it.currNode.prev);
    }

    public int size() {
        return mSize;
    }

    @Override
    public String toString() {
        String str = "";
        Node currNode = headerNode;
        for (int i = 0; i < mSize; i++) {
            currNode = currNode.next;
            str += (" " + currNode.data);
        }
        return str;
    }

    private Node<T> headerNode;
    private int mSize;
}
