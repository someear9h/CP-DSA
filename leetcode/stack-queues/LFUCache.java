import java.util.*;

public class LFUCache {
    class Node {
        int key, val, freq;
        Node prev, next;

        Node(int k, int v) {
            key = k;
            val = v;
            freq = 1;
        }
    }

    class DLL {
        Node head;
        Node tail;
        int size;

        DLL() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        void insert(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
            size++;
        }

        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        Node removeLast() {
            if(size > 0) {
                Node node = tail.prev;
                remove(node);
                return node;
            }

            return null;
        }
    }

    Map<Integer, DLL> freqToList; // to store the lists according to frequency
    Map<Integer, Node> keyToNode; // to map keys to nodes
    int size, capacity, minFreq;

    LFUCache(int capacity) {
        freqToList = new HashMap<>();
        keyToNode = new HashMap<>();
        this.size = 0;
        this.capacity = capacity;
        this.minFreq = 0;
    }

    private void updateFreq(Node node) {
        int oldFreq = node.freq;
        DLL oldList = freqToList.get(oldFreq);
        oldList.remove(node);

        if(oldFreq == minFreq && oldList.size == 0) {
            minFreq++;
        }

        node.freq++;
        freqToList.computeIfAbsent(node.freq, _ -> new DLL()).insert(node);
    }

    public int get(int key) {
        if(!keyToNode.containsKey(key)) {
            return -1;
        }

        Node node = keyToNode.get(key);
        updateFreq(node);
        return node.val;
    }

    public void put(int key, int value) {
        if(capacity == 0) return;

        if(keyToNode.containsKey(key)) {
            Node node = keyToNode.get(key);
            node.val = value;
            updateFreq(node);
        } else {
            if(size == capacity) {
                DLL minList = freqToList.get(minFreq);
                Node toRemove = minList.removeLast();
                keyToNode.remove(toRemove.key);
                size--;
            }

            Node newNode = new Node(key, value);
            keyToNode.put(key, newNode);
            freqToList.computeIfAbsent(1, _ -> new DLL()).insert(newNode);
            minFreq = 1;
            size++;
        }
    }

    public static void main(String[] args) {
        LFUCache lfu = new LFUCache(2);

        lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
        lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
        System.out.println(lfu.get(1)); // 1, cnt(1)=2
        lfu.put(3, 3);   // evict 2, cache=[3,1]
        System.out.println(lfu.get(2)); // -1
        System.out.println(lfu.get(3)); // 3, cnt(3)=2
        lfu.put(4, 4);   // evict 1 (tie broken by LRU), cache=[4,3]
        System.out.println(lfu.get(1)); // -1
        System.out.println(lfu.get(3)); // 3
        System.out.println(lfu.get(4)); // 4
    }
}
