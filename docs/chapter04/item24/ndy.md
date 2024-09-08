---
title: ndy
---
# Item 24: Favor static member classes over nonstatic

![[excalidraws/java-nested-classes.excalidraw.png]]

Static class

    위치만 중첩되어있고 별도의 파일로 구성된 클래스라고 생각해도 문법적으로 99% 이상 동일하다.


Inner class 

- Event 핸들러나 안드로이드 애플리케이션에서 자주 사용한다고 한다.
- 외부 클래스 인스턴스와 무조건 연결되어 생성된다.
- InnerClass 에서는 OuterClass 의 필드에 직접 접근 할 수 있다.

```java
class OuterClass {

	class InnerClass {

	}
}
```

```java
OuterClass            outer = new OuterClass()
OuterClass.InnerClass inner = outer.new Inner()
```
