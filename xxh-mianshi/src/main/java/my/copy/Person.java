package my.copy;

import lombok.Data;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

@Data
public class Person implements Cloneable{

    private String name;
    private Address address;
    public Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Person(){

    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    static class Address {
        private String city;
        public Address(String city) {
            this.city = city;
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException, InvocationTargetException, IllegalAccessException {
        Address address = new Address("Beijing");
        Person person1 = new Person("Tom", address);
        Person person2 = (Person) person1.clone();
        System.out.println(person1 == person2); // false
        System.out.println(person1.getAddress() == person2.getAddress()); // true


        Person copyPerson = new Person();
        BeanUtils.copyProperties(person1,copyPerson);


        System.out.println(person1 == copyPerson); // false
        System.out.println(copyPerson.getAddress() == copyPerson.getAddress()); // true  这里其实也是浅copy


    }

}
