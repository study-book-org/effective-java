---
title: ndy
---

# Item 22: Use interfaces only to define types

- 인터페이스는 타입 정의를 위해서만야한다.
- 그렇지 않은 경우가 있을까? 싶지만 바로 예시를 들어준다. 물론 안티패턴이다.

## 상수 관리

### `constant interface` 👎

```java
// Constant interface antipattern - do not use! 
public interface PhysicalConstants { 
  // Avogadro's number (1/mol)
  static final double AVOGADROS_NUMBER = 6.022_140_857e23; 

  // Boltzmann constant (J/K) 
  static final double BOLTZMANN_CONSTANT = 1.380_648_52e-23;

  // Mass of the electron (kg) 
  static final double ELECTRON_MASS = 9.109_383_56e-31;
}
```
위 처럼 정의된 `상수` 는 `implementation detail` 이다. Interface 에 정의되어 외부에 노출될 필요가 없다. 더 중요하게는 이런식의 인터페이스를 구현해 버린 클래스가 등장하면 아주 상태가 곤란해진다. 빠지면 섭섭하게... Java Platform 에도 이런 예시가 있다. `java.io.ObjectStreamConstants`

### 대안 👍

- 그냥 클래스에 추가 
	- `Integer.MIN_VALUE`, `Integer.MAX_VALUE`
- Enumeration 이라면 - enum 으로 관리
	- @since 3.0 spring-web > ... [`public enum HttpStatus implements HttpStatusCode`](https://github.com/spring-projects/spring-framework/blob/main/spring-web/src/main/java/org/springframework/http/HttpStatus.java)
	- @since 6.0 spring-web > ... [`public sealed interface HttpStatusCode ... permits DefaultHttpStatusCode, HttpStatus`](https://github.com/spring-projects/spring-framework/blob/main/spring-web/src/main/java/org/springframework/http/HttpStatusCode.java)
	- 단순한 enum 이었는데... 언제 이렇게 계층이 복잡해졌나...
- untility class 에 추가

## 추가

- `Use interfaces only to define types` 라는 격언은 이번 Item 에서 다룬 `constant interface` 와 같은 이제는 모든 자바 개발자가 체화되어 알고 있다고 여겨지는 원칙에도 적용이 되지만 앞선 Item 20, 21 에서 다룬 default method 를 통한 Interface 에 구현을 담는 행위에도 해당 되는 말이다.