package org.coursera.module12;

public class SeparateChainingHashST<Key, Value> {

    private int M = 97; // Number of chains
    private Node[] st = new Node[M]; // array of chains

    private static class Node {
        private Object key;
        private Object val;
        private Node next;

        public Node(Object key, Object val, Node node) {
            this.key = key;
            this.val = val;
            this.next = node;
        }
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return (Value) x.val;
            }
        }
        return null;
    }

    public void put(Key key, Value val) {
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        st[i] = new Node(key, val, st[i]);
    }

}
