---
title: echo
---
# 06 불필요한 객체 생성을 피해라
```java
String s = new String("hi");
```
다음 코드는 문장이 실행될 때마다 새로운 String 클래스를 생성하고 있다.
```java
String s = "hi";
```
이렇게 하면 동일한 String 클래스를 계속해서 사용할 수 있다.

생성자 대신 정적 팩토리 메서드를 사용하면 이런 객체 재사용을 할 수 있다.
```java
static boolean isRoman(String s){
    return s.matches("...");
}
```
위 코드의 문제점은 matches가 호출될 때마다 Pattern 인스턴스를 생성하는데, 이것이 한번 사용하고 버려지는 GC의 대상이 된다는 것이다.

이를 재사용하는 코드는 다음과 같다.
```java
static class RomanNumerals {
    private static Pattern ROMAN = Pattern.compile("...");
    
    static boolean isRoman(String s){
        return ROMAN.matchers(s).matches();
    }
}
```

실제로 6.5배 정도 성능이 개선된다.

`어뎁터`, `오토박싱` 객체도 사실 공통적으로 하나만 있으면 된다.
오토박싱의 경우 불필요한 객체생성을 피하기 위해 기본타입을 사용하자.

단 프로그램의 `명확성`, `간결성`을 높이기 위해서 `객체를 추가 생성하는 것은 피하지 말자`.
오히려 공통으로 사용했다가 (`방어적 복사`) 낭패를 보는 경우에 피해가 더 크다. 