---
title: ndy
---
# Item 4: Enforce noinstantiability with a private contructor

## Utility Class 에는 Private Constructor 를 사용해라

*Utility Class* 는 인스턴스가 필요없습니다. 하지만 컴파일러는 자동으로 아무 인자가 없는 `default constructor` 를 클래스에 부여합니다. 이를 막기위해 명시적으로 `private contructor` 를 추가하여 클래스의 인스턴스가 생성될 수 없도록 강제 하는것이 좋습니다.

> [!WARNING] Class 를 abstract 로 만들어 `non-instantiability` 를 강제 할 수 없다.
> - 클래스를 상속하여 하위 클래스를 instaintiate 할 수 있고
> - 개발자에게 이 클래스가 상속하기 위해 설계되었다는 오해를 낳을수 있다.
> 

Java 의 Util 클래스인 Arrays 와 Collections 에 적용된 **Item 4**

```java
package java.util;

public class Arrays {

	...

    // Suppresses default constructor, ensuring non-instantiability.
    private Arrays() {}

```

```java
package java.util;

public class Collections {
    // Suppresses default constructor, ensuring non-instantiability.
    private Collections() {
    }
```

## Lombok 을 이용해 Utility 클래스를 정의하는 방법
- private constructor 를 annotation 으로 간단히 정의 하는 할 수 있다.

```java
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MyUtil {

    public static void doSomething() {
        System.out.println("Doing something...");
    }

    public static int add(int a, int b) {
        return a + b;
    }

    // other utility methods...
}
```

`@UtilityClass` 를 사용하면 Lombok이 자동으로 클래스의 모든 메서드를 `static`으로 만들고, private 생성자를 생성하여 인스턴스화를 방지한다.

An annotation to create utility classes. If a class is annotated with `@UtilityClass`, the following things happen to it:

- It is marked final.
- If any constructors are declared in it, an error is generated. Otherwise, a private no-args constructor is generated; it throws a `UnsupportedOperationException`.
- All methods, inner classes, and fields in the class are marked static.

```java
import lombok.experimental.UtilityClass;

@UtilityClass
public class MyUtil {

    public void doSomething() {
        System.out.println("Doing something...");
    }

    public int add(int a, int b) {
        return a + b;
    }

    // other utility methods...
}
```

