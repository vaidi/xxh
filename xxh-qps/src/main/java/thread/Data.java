package thread;

import java.time.format.DateTimeFormatter;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class Data implements Delayed {
    private static final AtomicLong atomic = new AtomicLong(0);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss-n");
    // 数据的失效时间点
    private final long time;
    // 序号
    private final long seqno;
    /**
     * @param deadline 数据失效时间点
     */
    public Data(long deadline) {
        this.time = deadline;
        this.seqno = atomic.getAndIncrement();
    }
    /**
     * 返回剩余有效时间
     * @param unit 时间单位
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.time - System.nanoTime(), TimeUnit.NANOSECONDS);
    }
    /**
     * 比较两个Delayed对象的大小, 比较顺序如下:
     * 1. 如果是对象本身, 返回0;
     * 2. 比较失效时间点, 先失效的返回-1,后失效的返回1;
     * 3. 比较元素序号, 序号小的返回-1, 否则返回1.
     * 4. 非Data类型元素, 比较剩余有效时间, 剩余有效时间小的返回-1,大的返回1,相同返回0
     */
    @Override
    public int compareTo(Delayed other) {
        // compare zero if same object
        if (other == this){
            return 0;
        }
        if (other instanceof Data) {
            Data x = (Data) other;
            // 优先比较失效时间
            long diff = this.time - x.time;
            if (diff < 0){
                return -1;
            } else if (diff > 0){
                return 1;
            } else if (this.seqno < x.seqno) {
                // 剩余时间相同则比较序号
                return -1;
            } else{
                return 1;
            }
        }
        // 一般不会执行到此处，除非元素不是Data类型
        long diff = this.getDelay(TimeUnit.NANOSECONDS) - other.getDelay(TimeUnit.NANOSECONDS);
        return (diff < 0) ? -1 : (diff > 0) ? 1 : 0;
    }

    @Override
    public String toString() {
        return "Data{" +
                "time=" + time +
                ", seqno=" + seqno +
                "}, isValid=" + isValid();
    }

    private boolean isValid() {
        return this.getDelay(TimeUnit.NANOSECONDS) > 0;
    }
}
