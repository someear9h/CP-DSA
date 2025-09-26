import java.util.*;

public class LRUCache {
    class Node {
        Node prev, next;
        int key, val;

        Node(int _key, int _val) {
            key = _key;
            val = _val;
        }
    }

    Node head = new Node(0, 0), tail = new Node(0, 0);
    Map<Integer, Node> mp;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        mp = new HashMap<>();
        head.next = tail;
        tail.prev = head;
    }

    // removes the node from doubly ll and also from map
    private void remove(Node node) {
        mp.remove(node.key);

        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    // inserts the node after head, this is recently used node
    private void insertAfterHead(Node node) {
        mp.put(node.key, node);

        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    public int get(int key) {
        if(mp.containsKey(key)) {
            Node node = mp.get(key);
            remove(node);
            insertAfterHead(node);

            return node.val;
        } else {
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if(mp.containsKey(key)) {
            remove(mp.get(key));
        }

        if(capacity == mp.size()) {
            remove(tail.prev);
        }

        insertAfterHead(new Node(key, value));
    }

    public static void main(String[] args) {
        LRUCache obj = new LRUCache(2);

        obj.put(1, 1);
        obj.put(2, 2);

        System.out.println(obj.get(1));

        obj.put(3, 3);

        System.out.println(obj.get(2));

        obj.put(4, 4);

        System.out.println(obj.get(1));
        System.out.println(obj.get(3));
        System.out.println(obj.get(4));
    }
}
