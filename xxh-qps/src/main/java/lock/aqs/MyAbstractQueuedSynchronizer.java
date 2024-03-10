package lock.aqs;

import sun.misc.Unsafe;

public class MyAbstractQueuedSynchronizer extends MyAbstractOwnableSyn {

    protected MyAbstractQueuedSynchronizer(){}


    public final void acquire(int arg){
        if(!tryAcquire(arg) && acquireQueded(addWaiter(Node.EXECLUSEIVE),arg)){

        }

    }

    final boolean acquireQueded(final Node node,int arg){
        boolean failed =true;
        try{
            boolean interrupted = false;
            for(;;){
                //找到的是一个哨兵节点
                final Node p = node.prev;
                if(p == head && tryAcquire(arg)){

                    return interrupted;
                }
                //如果不是一个哨兵节点证明前面还有节点
//                if(houldParkAfterFailedAcquire(p, node) && parkAndCheckInterrupt()){
//                    interrupted = true;
//                }
            }
        }catch (Exception e){


        }
        return failed;


    }




    private Node addWaiter(Node execluseive) {
        Node node = new Node(Thread.currentThread(),execluseive);
        Node pred = tail;
        if(pred != null){
            //这里是为了把当前节点设置成未节点

        }
        //如果尾节点未空这里处理做什么 还是设置尾节点  不过这里有个初始化的过程。
        //enq(node);
        return node;
    }


    private transient volatile  Node head;
    private transient volatile  Node tail;
    private volatile  int state;
    protected void setState(int state){
        this.state =state;
    }
    protected int getState(){
        return state;
    }


    protected boolean tryAcquire(int arg) {
        throw new UnsupportedOperationException();
    }





    static final class Node{
        static final Node SHARED = new Node();
        static final Node EXECLUSEIVE =null;
        /**
         * 节点状态 1，-1，-2，-3 ，0
         */
        volatile int waitStatus;

        /**
         * 前置节点
         */
        volatile Node prev;

        /**
         * 后置节点
         */
        volatile Node next;

        volatile Thread thread;

        Node nextWaiter;

        final  boolean isShared(){
            return nextWaiter == SHARED;
        }

        Node() {    // Used to establish initial head or SHARED marker
        }
        Node(Thread thread,Node mode){
            this.nextWaiter = mode;
            this.thread =thread;
        }

        final Node predecessor(){
            Node p = prev;
            return p;

        }

    }

    /**
     * 看双向链表 中是否还由要消费的节点
     * @return
     */
    public final boolean hasQueuedPredecessors(){
        Node t =tail;
        Node h = head;
        Node s ;

        //h->n->t
        return h != t && ((s = h.next)== null || s.thread != Thread.currentThread());


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
                    (MyAbstractQueuedSynchronizer.class.getDeclaredField("state"));
            headOffset = unsafe.objectFieldOffset
                    (MyAbstractQueuedSynchronizer.class.getDeclaredField("head"));
            tailOffset = unsafe.objectFieldOffset
                    (MyAbstractQueuedSynchronizer.class.getDeclaredField("tail"));
            waitStatusOffset = unsafe.objectFieldOffset
                    (MyAbstractQueuedSynchronizer.Node.class.getDeclaredField("waitStatus"));
            nextOffset = unsafe.objectFieldOffset
                    (MyAbstractQueuedSynchronizer.Node.class.getDeclaredField("next"));

        } catch (Exception ex) { throw new Error(ex); }
    }
    protected final boolean compareAndSetState(int expect,int update){
       return unsafe.compareAndSwapInt(this,stateOffset,expect,update);

    }
}
