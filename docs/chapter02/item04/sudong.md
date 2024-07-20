---
title: echo
---
# 04 인스턴스화를 막으려거든, private 생성자를 사용하라.
정적 메서드와 정적 필드만 모아놓은 클래스를 만들 때가 있다.
`java.lang.Math`, `java.util.Arrays`, `java.util.Collections` 등이 대표적이다.

-> 이런 클래스는 인스턴스로 만들어쓰려고 하는게 아니지만, 생성자를 표시하지 않으면 컴파일러가 자동으로 기본 생성자를 만들어준다.

생성을 막는 방법 1) 추상클래스로 만들기
추상클래스로 만들어도 하위 클래스를 만들어서 인스턴스화 할 수 있다. 상속해서 쓰라고 오해할 수 있다.

`private 생성자`를 만들면 된다.

```java
public class UtilityClass{
    private UtilityClass{
        throw new AssertionError();
    }
}
```
생성자에서 에러를 반환하는 이유는 클래스 내부에서 실수로라도 생성자 호출을 하지않도록 막아준다.