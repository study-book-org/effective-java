---
title: ndy
---

# Item 3: Enforce the singleton property with a private constructor or an enum type

https://www.notion.so/ndy-dev/Singleton-Pattern-1f264ffeee04490cb881e74d63d35df4

## 싱글턴

- 정확히 한개의 인스턴스만을 가지는 클래스
- 일반적으로 기능을 담당하는, 상태가 없는 객체, 혹은 본질적으로 유일한 system component 에 사용된다.

- 로거 클래스
- 설정 클래스
- 팩토리 클래스
- [**`java.lang.Runtime#getRuntime()`**](http://docs.oracle.com/javase/8/docs/api/java/lang/Runtime.html#getRuntime--)
- [**`java.lang.System#getSecurityManager()`**](http://docs.oracle.com/javase/8/docs/api/java/lang/System.html#getSecurityManager--)

### 방법 1

```java
// Singleton with public final field 
public class Elvis { 
	public static final Elvis INSTANCE = new Elvis(); 
	private Elvis() { ... } 
	public void leaveTheBuilding() { ... } 
}
```

- private 생성자와 public static final 필드를 이용한 방식
- AccessibleObject.setAccessible 를 이용한 접근제어자 변경 공격이 발생할 수 있다.

### 방법 2

```java
// Singleton with static factory
public class Elvis { 
	private static final Elvis INSTANCE = new Elvis(); 
	private Elvis() { ... } 
	public static Elvis getInstance() { return INSTANCE; } 
	public void leaveTheBuilding() { ... } 
}
```

- 정적 펙토리 메서드 (ITEM1) `getInstance` 를 이용한 방식
- 클래스 이름을 통해 , Elvis 가 싱글턴 임을 예상 할 수 있다.
- 쓰레드 안전하다.


- 장점
	- API의 변경없이 생성 정책을 변경할 수 있다.
	    - e.g.) 완전 싱글턴 → 쓰레드별로 인스턴스 제공 (see also - ThreadLocal)
	- static factory method → generic singletone factory로 변경가능
	- Method Reference 활용 가능
	    - e.g.) `Elvis::getinstance` 를 `Supplier<Elvis>`로 활용

- 위 장점이 별로 필요 없다면 일밙거으로 `방법1` 이 더 선호 된다.


위 방식으로 싱글턴을 구현할 때 `Serilizable` 을 구현한다면 주의해야 한다. \[ITEM89]

### 방법 3

```java
// Enum singleton - the preferred approach 
public enum Elvis { 
	INSTANCE; 
	public void leaveTheBuilding() { ... } 
}
```

Singleton 을 목적으로 항목이 하나 만 있는 Enum 타입을 작성하는 것이 일반적이지 않게 느껴지지만, 방법 1,2 에서 고민해야할 reflection/ serialiation 관련 이슈가 없다. 대부분의 상황에서는 가장 좋은 방법이다.

대상 싱글턴 객체가 Enum 외의 클래스를 상속해야하는 경우 사용할 수 없음

