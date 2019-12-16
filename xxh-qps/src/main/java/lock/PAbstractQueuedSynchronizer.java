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
        static final  int SIGNAL = -1;
        static  final int CONDITION =-2;
        static final int PROPAGATE = -3;
        volatile  int waitStatus;
        volatile Node prev;

        volatile  Node next;

        volatile Thread thread;

        Node nextWaiter;

        final boolean isShared(){
            return nextWaiter == SHARED;
        }
        final  Node predecessor() throws NullPointerException{
            Node p = prev;
            if(p == null){
                throw  new NullPointerException();
            }else{
                return p;
            }
        }
        Node(){}
        Node (Thread thread,Node mode){
            this.nextWaiter = mode;
            this.thread = thread;
        }
        Node(Thread thread,int waitStatus){
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

        } catch (Exception ex) { throw new Error(ex); }
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

    protected final int getState(){return  state;}

    protected final void setState(int state){this.state =state;}

    protected final boolean compareAndSetState(int expect, int update){
        return unsafe.compareAndSwapInt(this,stateOffset,expect,update);
    }

    static final long spinForTimeoutThreshold = 1000L;

    private Node enq(final  Node node){
        for(;;){
            Node  t = tail;
            if(t == null){
                if(compareAndSetHead(new Node())){
                    tail = head;
                }
            }else{
                node.prev = t;
                if(compareAndSetTail(t,node)){
                    t.next =node;
                    return t;
                }
            }
        }
    }


    private Node addWaiter(Node mode){
        Node node = new Node(Thread.currentThread(),mode);
        Node pred = tail;
        if(pred !=null){
            node.prev = pred;
            if(compareAndSetTail(pred,node)){
                pred.next = node;
                return node;
            }
        }
        enq(node);
        return node;
    }

    private void setHead(Node node){
        head = node;
        node.thread = null;
        node.prev = null;
    }

    /**
     * 这个方法现在还没有看太懂啊
     * @param node
     */
    private void unparkSuccessor(Node node){
        int ws = node.waitStatus;
        if(ws < 0){
            compareAndSetWaitStatus(node,ws,0);
        }
        Node s = node.next;
        if(s == null || s.waitStatus >0){
            s = null;
            for(Node t = tail; t != null&& t!= node;t=t.prev){
                if(t.waitStatus <=0){
                    s=t;
                }
            }
        }
        if (s != null) {
            LockSupport.unpark(s.thread);
        }
    }

    private void doReleaseShared(){
        for(;;){
            Node h = head;
            if(h != null && h != tail){
                int ws = h.waitStatus;
                if(ws == Node.SIGNAL){
//                    if(){
//
//                    }
                }


            }


        }



    }


}
