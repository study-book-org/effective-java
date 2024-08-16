package chapter03.ndy;

import chapter03.ndy.person.CloneablePerson;

public class Item11Main {



    public static void main(String[] args) throws CloneNotSupportedException {
        System.out.println(new CloneablePerson("ndy").clone());


    }
}
