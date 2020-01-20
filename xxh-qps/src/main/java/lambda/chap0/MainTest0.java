package lambda.chap0;

import java.awt.print.PrinterGraphics;
import java.util.function.Predicate;

public class MainTest0 {

    public static void main(String[] args) {
        Predicate<Student> highStudent = student -> student.getAge()>10;
        Predicate<Student> highAndWeightStudent =highStudent.and(student -> student.getWeight()>100);
        Student student = new Student();
        student.setAge(100);
        student.setWeight(1000);
        boolean ok =highAndWeightStudent.test(student);
        System.out.println(ok);

    }


}
class Student{
    private int age;

    private int weight;

    private String sex;
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}