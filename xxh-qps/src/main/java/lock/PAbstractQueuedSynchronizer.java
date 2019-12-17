package lock;

import sun.misc.Unsafe;

import java.io.Serializable;
import java.util.concurrent.locks.AbstractOwnableSynchronizer;
import java.util.concurrent.locks.LockSupport;

/**
 * qps 依葫芦画瓢用
 */
public abstract class PAbstractQueuedSynchronizer extends AbstractOwnableSynchronizer implements Serializable {


    static final class Node {
        static final Node SHARED = new Node();
        static final Node EXCLUSIVE = null;
        static final int CANCELLED = 1;
        static final int SIGNAL = -1;
        static final int CONDITION = -2;
        static final int PROPAGATE = -3;
        volatile int waitStatus;
        volatile Node prev;
        volatile Node next;
        volatile Thread thread;
        Node nextWaiter;

        final boolean isShared() {
            return nextWaiter == SHARED;
        }

        final Node predecessor() throws NullPointerException {
            Node p = prev;
            if (p == null) {
                throw new NullPointerException();
            } else {
                return p;
            }
        }

        Node() {
        }

        Node(Thread thread, Node mode) {
            this.thread = thread;
            this.nextWaiter = mode;
        }

        Node(Thread thread, int waitStatus) {
            this.waitStatus = waitStatus;
            this.thread = thread;
        }
    }

    private static final Unsafe unsafe = Unsafe.getUnsafe();
    private static final long stateOffset;
    private static final long headOffset;
    private static final long tailOffset;
    private static final long waitStatusOffset;
    private static final long nextOffset;

    static {
        try {
            stateOffset = unsafe.objectFieldOffset
                    (PAbstractQueuedSynchronizer.class.getDeclaredField("state"));
            headOffset = unsafe.objectFieldOffset
                    (PAbstractQueuedSynchronizer.class.getDeclaredField("head"));
            tailOffset = unsafe.objectFieldOffset
                    (PAbstractQueuedSynchronizer.class.getDeclaredField("tail"));
            waitStatusOffset = unsafe.objectFieldOffset
                    (PAbstractQueuedSynchronizer.Node.class.getDeclaredField("waitStatus"));
            nextOffset = unsafe.objectFieldOffset
                    (PAbstractQueuedSynchronizer.Node.class.getDeclaredField("next"));

        } catch (Exception ex) {
            throw new Error(ex);
        }
    }

    /**
     * CAS head field. Used only by enq.
     */
    private final boolean compareAndSetHead(PAbstractQueuedSynchronizer.Node update) {
        return unsafe.compareAndSwapObject(this, headOffset, null, update);
    }

    /**
     * CAS tail field. Used only by enq.
     */
    private final boolean compareAndSetTail(PAbstractQueuedSynchronizer.Node expect, PAbstractQueuedSynchronizer.Node update) {
        return unsafe.compareAndSwapObject(this, tailOffset, expect, update);
    }

    /**
     * CAS waitStatus field of a node.
     */
    private static final boolean compareAndSetWaitStatus(PAbstractQueuedSynchronizer.Node node,
                                                         int expect,
                                                         int update) {
        return unsafe.compareAndSwapInt(node, waitStatusOffset,
                expect, update);
    }

    /**
     * CAS next field of a node.
     */
    private static final boolean compareAndSetNext(PAbstractQueuedSynchronizer.Node node,
                                                   PAbstractQueuedSynchronizer.Node expect,
                                                   PAbstractQueuedSynchronizer.Node update) {
        return unsafe.compareAndSwapObject(node, nextOffset, expect, update);
    }

    private transient volatile Node head;
    private transient volatile Node tail;

    private volatile int state;

    protected final int getState() {
        return state;
    }

    protected final void setState(int state) {
        this.state = state;
    }

    protected final boolean compareAndSetState(int expect, int update) {
        return unsafe.compareAndSwapInt(this, stateOffset, expect, update);
    }

    static final long spinForTimeoutThreshold = 1000L;

    /**
     * 将节点插入队列必要的时候进行初始化（插入尾部）
     */
    private Node enq(final Node node) {
        for (; ; ) {
            Node t = tail;
            if (t == null) {
                if (compareAndSetHead(new Node())) {
                    tail = head;
                }
            } else {
                node.prev = t;
                if (compareAndSetTail(t, node)) {
                    t.next = node;
                    return t;
                }
            }
        }
    }

    /**
     * 为当前线程和给定模块创建队列
     */
    private Node addWaiter(Node mode) {
        Node node = new Node(Thread.currentThread(), mode);
        //先进行插入队列尾部
        Node pred = tail;
        if (pred != null) {
            node.prev = pred;
            if (compareAndSetTail(pred, node)) {
                pred.next = node;
                return node;
            }
        }
        //队列尾部为空的时候  则进行直接插入队列其中采用的是自旋的方式
        enq(node);
        return node;
    }

    /**
     * 将队列头设置为节点，从而取消排队，仅有调用获取方也会为了GC清空未使用的字段
     * 抑制不必要的信号和遍历
     */
    private void setHead(Node node) {
        head = node;
        node.thread = null;
        node.prev = null;
    }

    /**
     * 这个方法现在还没有看太懂啊
     *
     * @param node 如果存在节点的继承，则这个方法用来唤醒
     */
    private void unparkSuccessor(Node node) {
        int ws = node.waitStatus;
        /**
         * 如果状态为负，准备发出信号的时候清除，
         * 如果失败或者状态被等待 线程改变 这是ok的
         */
        if (ws < 0) {
            compareAndSetWaitStatus(node, ws, 0);
        }

        /**
         *要断开链接的线程保存在后续线程中，通常是只是下一个节点，
         * 但如果被取消或者明显无效，从尾部向后移动以找到实际的未取消的继任者
         */
        Node s = node.next;
        if (s == null || s.waitStatus > 0) {
            s = null;
            for (Node t = tail; t != null && t != node; t = t.prev) {
                if (t.waitStatus <= 0) {
                    s = t;
                }
            }
        }
        if (s != null) {
            LockSupport.unpark(s.thread);
        }
    }

    /**
     * 共享模式的释放操作---通知后续操作并确保传播。
     * 注意：对于独占模式，仅释放数量如需要信号，
     * 调用head的unparkSuccessor
     */
    private void doReleaseShared() {
        for (;; ) {
            Node h = head;
            if (h != null && h != tail) {
                int ws = h.waitStatus;
                if (ws == Node.SIGNAL) {
                    if (!compareAndSetWaitStatus(h, Node.SIGNAL, 0)) {
                        continue;
                    }
                    unparkSuccessor(h);
                }else if(ws == 0 && !compareAndSetWaitStatus(h,0,Node.PROPAGATE)){
                    continue;
                }
                if(h == head){
                    break;
                }
            }
        }
    }

    /**
     * 设置队列头，并检查继任者是否正在等待在共享模式下，
     * 如果传播大于0或者已经设置传播状态
     */
    private void setHeadAndPropagate(Node node,int propagate){
        Node h = head;
        setHead(node);
        if(propagate >0|| h== null || h.waitStatus <0||
                (h=head)==null || h.waitStatus <0){
            Node s = node.next;
            if(s == null || s.isShared()){
                doReleaseShared();
            }

        }
    }

    /**
     * 取消一个正在进行的尝试
     */
    private void cancelAcquire(Node node){
        if(node == null){
            return;
        }
        node.thread = null;
        Node pred = node.prev;
        while (pred.waitStatus>0){
            node.prev = pred = pred.prev;
        }
        Node predNext = pred.next;
        node.waitStatus = Node.CANCELLED;
        if(node == tail && compareAndSetTail(node,pred)){
            compareAndSetNext(pred,predNext,null);
        }else{
            int ws;
            if(pred != head && ((ws = pred.waitStatus)== Node.SIGNAL||
                (ws <=0 && compareAndSetWaitStatus(pred,ws,Node.SIGNAL)))&&
            pred.thread != null){
                Node next = node.next;
                if(next != null && next.waitStatus <=0){
                    compareAndSetNext(pred,predNext,next);
                }
            }else {
                unparkSuccessor(node);
            }
            node.next = node;
        }
    }


    public final void acquire(int arg){
        if(!tryAcquire(arg)&& acquireQueued(addWaiter(Node.EXCLUSIVE),arg)){
           // selfInterrupt();//1199
        }


    }

    protected  boolean acquireQueued(Node node, int arg){
        boolean failed = true;
        try {
            boolean interrupted = false;
            for(;;){
                final Node p = node.predecessor();
                if(p == head && tryAcquire(arg)){
                    setHead(node);
                    p.next = null;
                    failed = false;
                    return interrupted;
                }
            }
        }finally {

            return failed;
        }

    }

    protected  boolean tryAcquire(int arg){
        throw new UnsupportedOperationException();
    }


}
