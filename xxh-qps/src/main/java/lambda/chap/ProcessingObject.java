package lambda.chap;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * 责任链模式
 *
 * @param <T>
 */
public abstract class ProcessingObject<T> {

    protected ProcessingObject<T> process;

    public void setProcess(ProcessingObject<T> success) {
        this.process = success;
    }

    public T handle(T input) {
        T r = handleWork(input);
        if (process != null) {
            return process.handle(r);
        }
        return r;
    }

    abstract protected T handleWork(T t);
}

class HeaderTextProcessing extends ProcessingObject<String> {
    @Override
    protected String handleWork(String text) {
        return "From Raoul, Mario and Alan: " + text;
    }
}

class SpellCheckerProcessing extends ProcessingObject<String> {
    @Override
    public String handleWork(String text) {
        return text.replaceAll("labda", "lambda");
    }
}

class MainTest {

    public static void main(String[] args) {
        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCheckerProcessing();
        p1.setProcess(p2);
        String result = p1.handle("Aren't labdas really sexy?!!");
        System.out.println(result);
        System.out.println("#####################################");
        UnaryOperator<String> headerProcessing = (String text) -> "From Raoul, Mario and Alan: " + text;
        UnaryOperator<String> spellCheckerProcessing = (String text) -> text.replaceAll("labda", "lambda");
        Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing);
        String r = pipeline.apply("Aren't labdas really sexy?!!");
        System.out.println("rrrrrrrrrrr:"+r);
    }

}












