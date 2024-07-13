---
title: ndy
---

# Item 01 - Consider static factory methods instead of constructors

## 정적 팩토리 메서드 예시

`boolean` 의 Wrapper 클래스인 `Boolean` 이 제공하는 정적 팩토리 메서드
```java
public static Boolean valueOf(boolean b) { 
 return b ? Boolean.TRUE : Boolean.FALSE; 
}
```

## 정적 팩토리 메서드 != 팩토리 메서드 패턴

-  `정적 팩토리 메서드`는 한 클래스의 생성자에 적절한 이름을 부여하여 `new` 를 숨기는것
- `팩토리 메서드 패턴`은 여러 구제 타입을 생성할 수 있는 기능을 하나의 팩토리 메서드를 통해 구현하는것.


## 정적 팩토리 메서드의 장점

### 1. 생성자와 달리, **이름을 가질 수 있다.**

생성자의 파라미터만으로 생성자의 역할을 드러낼 수 없는 경우가 많다.

`BigInteger.probablePrime` 메서드는 Java의 `BigInteger` 클래스에서 사용 가능한 정적 메서드로, 지정된 비트 길이의 소수를 무작위로 생성함`

```java
public static BigInteger probablePrime(int bitLength, Random rnd)
```

`확률적으로 (probable)` `소수 (prime)` 를 생성한다는 의미를 정적 팩토리 메서드의 이름을 통해 드러냄.


\+ 개발 시 계층간의 모델 변환에 아래와 같은 정적 팩토리 메서드를 사용하는 것이 꽤나 유용함.

```java
public static SomeDto fromEntity(SomeEntity entity)
```

### 2. 생성자와 달리, 매번 생성을 안해도 된다.

- 이를 통해, 불변 클래스가 미리 생성된 객체를 활용하거나, 생성 시점에 객체를 캐싱 할 수 있음
-  e.g.) 싱글턴 패턴의 대표적인 구현방식 인 `private static INSTANCE` 필드를 `public static getInstance` 메서드에서 캐싱하여 반환

```java
public class Singleton {
    private static Singleton instance;
    private Singleton() { }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

### 3. 생성자와 달리, 반환 타입의 어떠한 하위 타입이라도 반환 할 수 있다.

- 이를 통해, 유연하고 훨씬 간결한 코드를 유지 할 수 있다.
- e.g.) `java.util.Collections` 의 `unmodifiableXXX`, `syncrhonizedXXX`

```java
public static <T> Collection<T> unmodifiableCollection(Collection<? extends T> c)
public static <T> Set<T> unmodifiableSet(Set<? extends T> s)
public static <K,V> Map<K,V> unmodifiableMap(Map<? extends K, ? extends V> m)

...

public static <T> Collection<T> synchronizedCollection(Collection<T> c)
public static <T> Set<T> synchronizedSet(Set<T> s)

...
```

반환 타입은 `Collection`, `Set` 으로 선언되어 있지만 실제 반환 타입은 `Collections` 내부에 정적 클래스로 정의된 `UnmodifiableXxx`, `SyncrhonizedXxx` 타입의 클래스이다. 반환 받은 클라이언트는 실제 타입이 아닌 인터페이스 타입을 받음으로써 더 유연함을 가질 수 있다.

일반적으로 `good practice` 인 `구체 타입 대신 인터페이스 타입을 사용하라 (ITEM 64)` 에도 부합한다.

### 4. 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있다.


`java.util.EnumSet` 의 정적 팩토리 메서드

```java
public static <E extends Enum<E>> EnumSet<E> noneOf(Class<E> elementType) {  
    Enum<?>[] universe = getUniverse(elementType);  
    if (universe == null)  
        throw new ClassCastException(elementType + " not an enum");  
  
    if (universe.length <= 64)  
        return new RegularEnumSet<>(elementType, universe);  
    else  
        return new JumboEnumSet<>(elementType, universe);  
}
```


`java.util.Collection` 의 `reverseOrder` 메서드

```java
public static <T> Comparator<T> reverseOrder(Comparator<T> cmp) {  
    if (cmp == null) {  
        return (Comparator<T>) ReverseComparator.REVERSE_ORDER;  
    } else if (cmp == ReverseComparator.REVERSE_ORDER) {  
        return (Comparator<T>) Comparators.NaturalOrderComparator.INSTANCE;  
    } else if (cmp == Comparators.NaturalOrderComparator.INSTANCE) {  
        return (Comparator<T>) ReverseComparator.REVERSE_ORDER;  
    } else if (cmp instanceof ReverseComparator2) {  
        return ((ReverseComparator2<T>) cmp).cmp;  
    } else {  
        return new ReverseComparator2<>(cmp);  
    }  
}
```


### 5. 정적 팩터리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다.

예를 들어, `JDBC` 의 Connection 을 획득하기 위한 정적 팩토리 메서드인  `DriverManager.getConnection` 는 이 메서드가 작성되는 시점에 존재하지 않는 클래스 일지라도 

- Connection 을 구현하였고, 
- `DriverManager.registerDriver` 를 통해 JDBC 에 Driver 가 등록 되었다면

조회 할 수 있다.


이를 Service Provider Framework 패턴 이라고 한다.

이 패턴은 - Bridge 패턴/ DI 등의 다양한 변형이 있다.

## 정적 팩토리 메서드의 한계

### 1. protected/public 접근제어자를 가진 생성자 없이 정적 팩토리 메서드만 제공 한다면 하위 타입을 만들 수 없다.

- java 의 상속 (하위) 클래스는 부모 클래스의 생성자를 호출해야 하기 때문
- 사실 크게 단점이라고 보기 애매하다. 필요하면 명시적으로 protected 생성자를 제공 하면 됨

### 2. 생성자와 달리, 언어 자체의 규약이 아니기 때문에 개발자가 찾기 힘들다.

- 따라서 보통 아래와 같은 정형화 된 이름을 가진 정적 팩토리 메서드를 많이 사용한다.

-  from
	- 매개변수가 1개이고, 해당 타입의 인스턴스를 반환하는 메서드
```java
Date d = Date.from(instant);
```

- of
	- 매개변수가 n개이고, 적합한 타입의 인스턴스를 반환하는 메서드
```java
Set<Rank> cards = EnumSet.of(JACK, QUEEN, KING);
```

- valueOf
- instance / getInstance
- create / newInstance
- getType
