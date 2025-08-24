import java.util.*;

class ListNode {
    int val;
    ListNode next;
    
    public ListNode() {};

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class MergekSortedLists {
    public static ListNode mergeKLists(ListNode[] lists) {
        // base cases
        if(lists == null || lists.length == 0) return null;

        // convert the lists array into ArrayList
        List<ListNode> list = new ArrayList<>(Arrays.asList(lists));
        
        // while we have atleast 2 linked-lists to merge
        while(list.size() > 1) {
            List<ListNode> mergedList = new ArrayList<>();

            for(int i = 0; i < list.size(); i += 2) {
                ListNode l1 = list.get(i);
                ListNode l2 = (i + 1 < list.size()) ? list.get(i + 1) : null;

                mergedList.add(mergeTwoLists(l1, l2));
            }

            list = mergedList;
        }

        return list.get(0);
    }

    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode tail = dummy;

        while(l1 != null && l2 != null) {
            if(l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }

            tail = tail.next;
        }

        if(l1 != null) {
            tail.next = l1;
        }
        if(l2 != null) {
            tail.next = l2;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        // 1. Create the input lists
        ListNode l1 = new ListNode(1, new ListNode(4, new ListNode(5)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode l3 = new ListNode(2, new ListNode(6));

        // Put them into an array
        ListNode[] lists = {l1, l2, l3};

        // 2. Call the function
        ListNode mergedHead = mergeKLists(lists);

        // 3. Print the result
        System.out.println("The merged list is:");
        printList(mergedHead);
    }

    // Helper function to print a linked list
    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}
