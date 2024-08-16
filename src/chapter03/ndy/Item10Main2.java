package chapter03.ndy;

import java.util.Objects;
import java.util.Set;

public class Item10Main2 {


    static class Person{
        String name;

        public Person(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            return this != o;
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
    public static void main(String[] args) {
        Person ndy = new Person("ndy");
        Set<Person> people = Set.of(ndy);
        System.out.println("people.contains(ndy) = " + people.contains(ndy));
    }
}
