package jvm.threadpool;

import java.util.Comparator;

public class PriorityTaskComparator implements Comparator<PriorityTask> {
    @Override
    public int compare(PriorityTask o1, PriorityTask o2) {
        return  o1.getPriority() != o2.getPriority() ?
                Integer.compare(o1.getPriority(),o2.getPriority()):
                Integer.compare(o1.getTaskSequence(),o2.getTaskSequence());

    }
}
