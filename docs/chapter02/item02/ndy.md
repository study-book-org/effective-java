---
title: ndy
---

# Item 2: Consider a builder when faced with many constructor parameters

## 빌더 패턴

### 빌더 패턴의 등장 배경

#### 1. Telescope 생성자 패턴

```java
public class Person {
    private final String firstName;
    private final String lastName;
    private final int age;

    // 첫 번째 생성자: 필수 매개변수만 포함
    public Person(String firstName) {
        this(firstName, null, 0);
    }

    // 두 번째 생성자: 필수 매개변수와 선택적 매개변수 1개 포함
    public Person(String firstName, String lastName) {
        this(firstName, lastName, 0);
    }

    // 세 번째 생성자: 필수 매개변수와 선택적 매개변수 2개 포함
    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {
        // 다양한 생성자 사용 예
        Person person1 = new Person("John");
        Person person2 = new Person("John", "Doe");
        Person person3 = new Person("John", "Doe", 30);

        System.out.println(person1);
        System.out.println(person2);
        System.out.println(person3);
    }
}

```

이 패턴을 사용하면, 필수 매개변수만으로 객체를 생성하거나, 선택적 매개변수를 포함하여 객체를 생성할 수 있습니다. 그러나 많은 매개변수를 처리해야 하는 경우, 이러한 방식은 다소 번거로울 수 있으며, 가독성이 떨어질 수 있습니다. 이를 보완하기 위해 보통 빌더 패턴을 사용하는 것이 더 나은 선택이 될 수 있습니다.

#### 2. JavaBeans 패턴

```java
public class Person {
    private String firstName;
    private String lastName;
    private int age;

    // 기본 생성자
    public Person() {
    }

    // getter 및 setter 메서드
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {
        // 기본 생성자로 객체 생성
        Person person = new Person();

        // 속성 설정
        person.setFirstName("John");
        person.setLastName("Doe");
        person.setAge(30);

        System.out.println(person);
    }
}

```

JavaBeans 패턴은 객체 생성 후에 속성을 설정할 수 있는 유연성을 제공하지만, 불변 객체를 만들 수 없고, 객체가 완전히 설정되기 전에 사용될 위험이 있습니다. 이런 이유로, 복잡한 객체를 생성할 때는 빌더 패턴을 더 선호할 수 있습니다.

### 빌더 패턴 예시

```java
public class Person {
    private final String firstName;
    private final String lastName;
    private final int age;

    private Person(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private int age;

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Person build() {
            return new Person(this);
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}

// 사용 예
public class Main {
    public static void main(String[] args) {
        Person person = new Person.Builder()
            .firstName("John")
            .lastName("Doe")
            .age(30)
            .build();

        System.out.println(person);
    }
}

```

### 빌더 패턴의 장점

빌더 패턴은 객체 생성 시 복잡한 인스턴스 설정을 간편하고 가독성 있게 만들기 위해 사용됩니다. 특히 많은 매개변수를 가진 객체를 생성할 때 유용합니다. 빌더 패턴의 주요 장점은 다음과 같습니다:

#### 1. (위 두 방법 보다) 가독성이 좋다.

#### 2. 불변성을 가질 수 있다.

#### 3. 유연하다.

```java
Person person = new Person.Builder("John", "Doe")
    .age(30)
    .build();
```

- GPT 가 소개한 필수 인자를 Builder 의 생성자 자체에 포함하는 방식.
- 별로... 좋아 보이지는 않는다.


### 빌더 패턴의 단점

#### 1. 순서가 보장되지 않는다.

```java
Person person1 = new Person.Builder()
            .firstName("John")
            .lastName("Doe")
            .age(30)
            .build();

Person person2 = new Person.Builder()
            .lastName("Doe")
            .firstName("John")
            .age(30)
            .build();
```

#### 2. 필요한 인자를 빠뜨릴 수 있다.

```java
Person person2 = new Person.Builder()
            .build();

```

이름과 성, 나이도 없는 사람이 생성되었다. 
어떠한 컴파일 에러도 발생하지 않음

#### 3.  IDE 지원이 약하다.

이 모든 단점과 빌더를 이용한 생성 자체에 IDE 의 지원을 받기 어렵다.

#### 4. + Named Parameter 가 없어서 억지로 만들어 쓰는 방식 별로 좋아 하지 않음

```kotlin
Person(
  firstName = "John",
  lastName = "Doe"
  age = "John"
)
```
