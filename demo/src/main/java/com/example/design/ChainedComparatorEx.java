package com.example.design;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ChainedComparatorEx {

    public static void main(String[] args) {
        List<Person> list = Arrays.asList(new Person("Shailendra", 31),
                new Person("Aman", 50), new Person("Aman", 34 ));
        Collections.sort(list, new ChainedComparator(Arrays.asList(new NameComparator(), new AgeComparator())));
        list.forEach(System.out::println);

        // New java 8 way
        Collections.sort(list, new NameComparator().thenComparing(new AgeComparator()));
        System.out.println("New way ---");
        list.forEach(System.out::println);
    }


}


@Getter
@AllArgsConstructor
@ToString
class Person {
    String name;
    Integer age;
}

class NameComparator implements Comparator<Person>{

    @Override
    public int compare(Person p1, Person p2) {
        return p1.getName().compareTo(p2.getName());
    }
}

class AgeComparator implements Comparator<Person>{

    @Override
    public int compare(Person p1, Person p2) {
        return p1.getAge().compareTo(p2.getAge());
    }
}

class ChainedComparator implements Comparator<Person>{

    List<Comparator<Person>> comparators;

    public ChainedComparator(List<Comparator<Person>> comparators){
        this.comparators = comparators;
    }

    @Override
    public int compare(Person person1, Person person2) {
        int value = 0;
        for(Comparator comparator : comparators){
            value = comparator.compare(person1, person2);
            if(value != 0) {
                return value;
            }
        }
        return value;
    }
}
