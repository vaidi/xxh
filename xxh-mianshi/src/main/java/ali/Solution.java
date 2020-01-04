package ali;

import java.util.Stack;

/**
 * 高效的单向链表逆向输出
 *利用栈这个结构进行逆排序
 * @param <T>
 */
public class Solution<T> {
    public static void main(String[] args) {
        ListNode<Integer> head = new ListNode<Integer>(4);
        ListNode<Integer> head1 = new ListNode<Integer>(3);
        ListNode<Integer> head2 = new ListNode<Integer>(2);
        ListNode<Integer> head3 = new ListNode<Integer>(1);
        head.next=head1;
        head1.next = head2;
        head2.next = head3;
        ListNode<Integer> currentNode = head;
        while (currentNode.next !=null){
            System.out.println(currentNode.val);
            currentNode = currentNode.next;
        }
        Solution solution = new Solution();
        solution.reverse(head);
        while (currentNode.next !=null){
            System.out.println("逆排序后的"+currentNode.val);
            currentNode = currentNode.next;
        }

    }


    public void reverse(ListNode<T> head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode<T> currentNode = head;
        Stack<ListNode<T>> stack = new Stack<ListNode<T>>();
        while (currentNode != null) {
            stack.push(currentNode);
            ListNode<T> temp = currentNode.next;
            currentNode.next = null;
            currentNode = temp;
        }
        head = stack.pop();
        currentNode = head;
        while (!stack.isEmpty()) {
            currentNode.next = stack.pop();
            currentNode = currentNode.next;
        }
    }


}

class ListNode<T> {
    T val;

    public ListNode(T val) {
        this.val = val;
    }

    ListNode<T> next;
}