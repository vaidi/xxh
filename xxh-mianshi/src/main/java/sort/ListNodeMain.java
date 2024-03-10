package sort;


import java.util.*;

public class ListNodeMain {


    public static void main(String[] args) {
        ListNode p2 = ListNode.of(1, 4, 2, 3,1);
       // System.out.println(isPalindrome1(p2));
        ListNode reverse = oddEvenList(p2);
        while (reverse != null) {
            System.out.println(reverse.val);
            reverse = reverse.next;
        }

    }

    /**
     * 是否有环
     * @param head
     * @return
     */
    public static boolean hasCycle(ListNode head) {
        ListNode last = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            if (last == fast) {
                return true;
            }
            last = last.next;
            fast = fast.next.next;
        }
        return false;
    }


    /**
     *
     * @param head
     * @return
     */
    public static ListNode oddEvenList(ListNode head) {
        ListNode s = new ListNode(-1, null);
        ListNode cur = s;
        int num = 1;
        ListNode ans = head;
        while (ans != null && ans.next != null && ans.next.next != null) {
            ListNode next = ans.next;
            num++;
            if (num % 2 == 0) {
                ListNode temp = new ListNode(next.val, null);
                ans.next = ans.next.next;
                ans = ans.next;
                cur.next = temp;
                cur = cur.next;
            }
        }
        if (num % 2 == 0) {
            cur.next = ans.next;
        }
        ans.next = s.next;
        return head;
    }

    /**
     * 两数之和
     * @param nums
     * @param k
     * @return
     */
    public int maxOperations(int[] nums, int k) {
        Set<Integer> set = new HashSet();
        int num = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(i)) {
                continue;
            }
            if (nums[i] > k) {
                break;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (set.contains(j) || set.contains(i)) {
                    continue;
                }

                if (nums[i] + nums[j] == k) {
                    num++;
                    set.add(i);
                    set.add(j);
                }
            }
        }
        return num;
    }

    /**
     * 删除中间节点
     * @param head
     * @return
     */
    public static ListNode deleteMiddle(ListNode head) {
        ListNode myHeader = new ListNode(-1, head);
        ListNode p1 = myHeader;
        ListNode p2 = myHeader.next;
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
        }
        p1.next = p1.next.next;
        return myHeader.next;
    }

    /**
     * 反转链表
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode s = new ListNode(-1, null);
        while (head != null) {
            s.next = new ListNode(head.val, s.next);
            head = head.next;
        }
        return s.next;
    }


    public static int find2(int num) {
        int v = (int) (Math.log10(num) / Math.log10(2)) + 1;
        int target = 1 << v;
        return target;
    }


    /**
     * todo 搞什么啊
     * @param head
     * @param x
     * @return
     */
    public static ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode s = new ListNode(-1, head);
        ListNode fast = head;
        ListNode last = s;
        ListNode o1 = new ListNode(-1, null);
        while (fast != null) {
            if (last.next != fast) {
                if (fast.val >= x) {
                    fast = fast.next;
                } else {
                    o1.next = last.next;
                    ListNode cur = o1.next;
                    while (cur.next != fast) {
                        cur = cur.next;
                    }
                    //慢指针的赋值
                    last.next = fast;
                    last = last.next;
                    //快指针赋值
                    cur.next = fast.next;
                    fast = cur;

                }
            } else {
                if (fast.val >= x) {
                    fast = fast.next;
                } else {
                    fast = fast.next;
                    last = last.next;
                }
            }
        }
        if (o1.next != null) {
            last.next = o1.next;
        }
        return s.next;
    }


    /**
     * 反转链表
     * @param head
     * @return
     */
    public static ListNode revese(ListNode head) {
        ListNode pFast = head;
        ListNode p1 = null;
        while (pFast != null) {
            ListNode o1 = pFast.next;
            pFast.next = p1;
            p1 = pFast;
            pFast = o1;
        }
        return p1;
    }

    /**
     * 判断是否是一个回环链
     * @param head
     * @return
     */
    public static boolean isPalindrome1(ListNode head){
        LinkedList<Integer> linkedList = new LinkedList<>();
        ListNode cur = head;
        while (cur != null){
            linkedList.push(cur.val);
            cur = cur.next;
        }
        ListNode nowCur = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null){
            Integer pop = linkedList.pop();
            if(nowCur.val != pop){
                return Boolean.FALSE;
            }
            nowCur = nowCur.next;
            fast = fast.next.next;
        }
        return  Boolean.TRUE;
    }




    /**
     * 判断是否是否回环链
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode sentinel = new ListNode(-1, head);
        ListNode last = head;
        ListNode fast = head;
        //反转前面的字符传
        ListNode pFast = head;
        ListNode p1 = null;
        while (fast != null) {
            //取中间的
            if (fast.next == null) {
                fast = null;
            } else {
                fast = fast.next.next;
                last = last.next;
            }
            p1 = new ListNode(pFast.val, p1);
            pFast = pFast.next;

        }
        ListNode middle = last;
        while (middle != null) {
            if (middle.val != p1.val) {
                return false;
            }
            p1 = p1.next;
            middle = middle.next;
        }
        return true;
    }


    /**
     * 合并两个数组
     * @param array1
     * @param array2
     * @return
     */
    public static int[] mergeArray(int[] array1, int[] array2) {
        int[] sum = new int[(array1.length + array2.length)];
        int p1 = 0;
        int p2 = 0;
        int index = 0;
        while (p1 < array1.length && p2 < array2.length) {
            if (array1[p1] > array2[p2]) {
                sum[index++] = array2[p2++];
            } else {
                sum[index++] = array1[p1++];
            }
        }
        if (p1 < array1.length) {
            for (; p1 < array1.length; p1++) {
                sum[index++] = array2[p1];
            }
        }
        if (p2 < array1.length) {
            for (; p2 < array2.length; p2++) {
                sum[index++] = array2[p2];
            }
        }
        return sum;
    }


    /**
     * 判断是否有环
     * @param listNode
     * @return
     */
    public static boolean hasCircle(ListNode listNode) {
        if (listNode == null || listNode.next == null) {
            return false;
        }
        ListNode p1 = listNode;
        ListNode p2 = listNode.next;
        while (p2 != null && p2.next != null) {
            if (p1 == p2) {
                return true;
            }
            p1 = p1.next;
            p2 = p2.next.next;
        }
        return false;


    }


    /**
     * 找中间节点
     * @param head
     * @return
     */
    public static ListNode findMinddleNode(ListNode head) {
        ListNode last = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            last = last.next;
            fast = fast.next.next;
        }
        return last;
    }


    public static ListNode meergeListNodeAll(ListNode[] listNodes) {
        return spliteList(listNodes, 0, listNodes.length);

    }

    public static ListNode spliteList(ListNode[] listNodes, int left, int right) {
        if (left == right) {
            return listNodes[left];
        }
        int m = (left + right) / 2;
        ListNode leftNode = spliteList(listNodes, 0, m);
        ListNode rightNode = spliteList(listNodes, m + 1, right);
        return mergeListNode(leftNode, rightNode);
    }


    /**
     * 合并两个链表
     * @param p1
     * @param p2
     * @return
     */
    public static ListNode mergeListNode(ListNode p1, ListNode p2) {
        ListNode s = new ListNode(-1, null);
        ListNode cur = s;
        int maxVal;
        while (p1 != null || p2 != null) {
            if (p1 == null) {
                cur.next = p2;
                break;
            }
            if (p2 == null) {
                cur.next = p1;
                break;
            }
            int p1Val = p1.val;
            int p2Val = p2.val;
            maxVal = Math.max(p1Val, p2Val);
            if (p1Val < maxVal) {
                cur.next = new ListNode(p1.val, null);
                p1 = p1.next;
            } else {
                cur.next = new ListNode(p2.val, null);
                p2 = p2.next;
            }
            cur = cur.next;
        }
        return s.next;

    }


    /**
     * 递归调用
     * @param listNode
     * @return
     */
    public static ListNode circleDelDuplication(ListNode listNode) {
        if (listNode == null || listNode.next == null) {
            return listNode;
        }
        if (listNode.val == listNode.next.val) {
            return circleDelDuplication(listNode.next);
        } else {
            listNode.next = circleDelDuplication(listNode);
            return listNode;
        }
    }


    /**
     * 删除连续重复的节点
     * @param listNode
     * @return
     */
    public static ListNode delDuplicates(ListNode listNode) {
        if (listNode == null || listNode.next == null) {
            return listNode;
        }
        ListNode last = listNode;
        ListNode fast = listNode.next;
        while (fast != null) {
            if (last.val == fast.val) {
                last.next = fast.next; //last 保持不变
                fast = fast.next;
            } else {
                fast = fast.next;
                last = last.next;
            }
        }
        return listNode;

    }





    /**
     * 删除某个节点
     * @param listNode
     * @param n
     * @return
     */
    public static ListNode removeTail(ListNode listNode, int n) {
        ListNode s = new ListNode(-1, listNode);
        ListNode head = s;
        int idx = 1;
        while (s != null && s.next != null) {
            if (idx == n) {
                s.next = s.next.next;
            }
            s = s.next;
            idx++;
        }
        return head.next;
    }


    /**
     * todo sb
     *
     * @param listNode
     * @return
     */
    public static ListNode removerListNodeValue(ListNode listNode) {
        if (listNode == null) {
            return listNode;
        }
        ListNode sentinel = new ListNode(-1, listNode);
        ListNode last = sentinel;
        ListNode fast = sentinel.next.next;
        while (fast != null) {
            if (last.next.val == fast.val) {
                fast = fast.next;
            } else {
                if (last.next.next != fast) {
                    last.next = fast; //这里last不用变
                    fast = fast.next;
                } else {
                    last = last.next;
                    fast = fast.next;
                }
            }
            if (fast == null && last.next.next != null) {
                last.next = null;
            }
        }
        return sentinel.next;

    }


    /**
     * 跳过链表中等于某个值的
     *
     * @param listNode
     * @param val
     * @return
     */
    public static ListNode removeListNode(ListNode listNode, int val) {
        if (listNode == null) {
            return listNode;
        }
        ListNode sentileNode = new ListNode(-1, listNode);
        ListNode last = sentileNode;
        ListNode fast = sentileNode.next;
        while (last != null && fast != null) {
            if (fast.val == val) {
                fast = fast.next;
                last.next = fast;
            } else {
                fast = fast.next;
                last = last.next;
            }
        }
        return sentileNode.next;

    }

    /**
     * 删除数组中的某一个节点
     *
     * @param array
     * @param value
     * @return
     */
    public int[] removeArray(int[] array, int value) {
        if (array == null || array.length == 0) {
            return array;
        }
        java.util.List<Integer> objects = new ArrayList<>();
        Arrays.stream(array).forEach(e -> {
            if (e != value) {
                objects.add(e);
            }

        });
        int[] res = new int[objects.size()];
        int index = 0;
        for (int re : objects) {
            res[index++] = re;
        }
        return objects.stream().mapToInt(Integer::intValue).toArray();
    }


    /**
     * 反转链表2
     *
     * @param root
     * @return
     */
    public ListNode reverList(ListNode root) {
        ListNode n1 = null;
        while (root != null) {
            ListNode o2 = root.next;
            root.next = n1;
            n1 = root;
            root = o2;
        }
        return n1;
    }


    static class ListNo {

        ListNode head;

        public ListNo(ListNode node) {
            this.head = node;
        }

        public void addFirst(ListNode first) {
            first.next = head;
            head = first;
        }

        public ListNode removeFirst() {
            ListNode first = head;
            if (first != null) {
                head = head.next;
            }
            return first;
        }


    }

    /**
     * 复制链表
     *
     * @param head
     * @return
     */
    public static ListNode copyListNode(ListNode head) {
        ListNode listNode = new ListNode(-1, null);
        ListNode root = listNode;
        while (head != null) {
            listNode.next = new ListNode(head.val, null);
            listNode = listNode.next;
            head = head.next;
        }
        return root.next;
    }

    /**
     * 反转链表
     *
     * @param listNode
     * @return
     */
    public static ListNode reverse(ListNode listNode) {
        ListNode newNode = null;
        ListNode p = listNode;
        while (p != null) {
            newNode = new ListNode(p.val, newNode);

            System.out.println("val:" + newNode.val + "nextVal:" + newNode.next);
            p = p.next;
        }
        return newNode;
    }


    static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public static ListNode of(int... ints) {
            ListNode head = new ListNode(-1, null);
            ListNode curNode = head;
            for (int anInt : ints) {
                curNode.next = new ListNode(anInt, null);
                curNode = curNode.next;
            }
            return head.next;
        }
    }


    static class RecentCounter {

        LinkedList<Integer> queue;

        public RecentCounter() {
            queue = new LinkedList<>();
        }

        public int ping(int t) {
            queue.offer(t);
            while (queue.peek() < t - 3000) {
                queue.poll();
            }
            return queue.size();
        }
    }

}
