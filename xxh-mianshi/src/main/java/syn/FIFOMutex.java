package syn;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class FIFOMutex {

    private final AtomicBoolean locked = new AtomicBoolean(false);

    private final Queue<Thread> waiters = new ConcurrentLinkedQueue<>();

}
