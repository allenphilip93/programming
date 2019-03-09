/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

import java.util.PriorityQueue;
import java.lang.Comparable;

public class MergeKLists {
    
    private class ListNode {
        int val;
        ListNode next;
        public ListNode (int val) {
            this.val = val;
        }
    }
    
    private class Node<T, W extends Comparable<? super W>> implements Comparable<Node<T,W>> {
        T data;
        W priority;
        
        public Node(T data, W priority) {
            this.data = data;
            this.priority = priority;
        }
        
        public int compareTo(Node<T, W> node) {
            return this.priority.compareTo(node.priority);
        }
    }
    
    public ListNode mergeKLists(ListNode[] lists) {
        int k = lists.length;
        PriorityQueue<Node<Integer, Integer>> pq = new PriorityQueue<>();
        for (int i=0; i < k; i++) {
            if (lists[i] != null) {
                Node<Integer, Integer> node = new Node<>(i, lists[i].val);
                pq.offer(node);
                lists[i] = lists[i].next;
            }
        }
        ListNode res = null, lastnode = null;
        while (pq.size() > 0) {
            Node<Integer, Integer> minNode = pq.poll();
            int listIdx = minNode.data;
            if (lists[listIdx] != null) {
                Node<Integer, Integer> offerNode = new Node<>(listIdx, lists[listIdx].val);
                pq.offer(offerNode);
                lists[listIdx] = lists[listIdx].next;
            }
            ListNode newNode = new ListNode(minNode.priority);
            if (res == null) {
                res = newNode;
                lastnode = res;
            } else {
                lastnode.next = newNode;
                lastnode = newNode;
            }
        }
        return res;
    }
}