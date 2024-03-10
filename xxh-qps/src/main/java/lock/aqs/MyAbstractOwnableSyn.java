package lock.aqs;

public abstract class MyAbstractOwnableSyn {


    protected MyAbstractOwnableSyn(){}

    private transient Thread execlusiveOwnerThread;


    protected final void setExclusiveOwnerThread(Thread thread){
        execlusiveOwnerThread = thread;
    }
    protected final Thread getExeclusiveOwnerThread(){
        return execlusiveOwnerThread;
    }



}
