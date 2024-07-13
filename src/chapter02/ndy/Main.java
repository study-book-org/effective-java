package chapter02.ndy;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");

        List<String> list = List.of("l", "i", "s", "t");
        Set<String> set = Set.of("s", "e", "t");

        Set.of(list, set).stream()
                .map(Collections::unmodifiableCollection)
                .forEach(c -> System.out.println(c.getClass().getSimpleName()));

        Set.of(list, set).stream()
                .map(Collections::synchronizedCollection)
                .forEach(c -> System.out.println(c.getClass().getSimpleName()));
    }
}

