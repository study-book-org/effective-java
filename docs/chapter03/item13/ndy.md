---
title: ndy
---

## Copy Factory 메서드를 사용할때의 안티패턴

Item 13 은 아래의 결론으로 마무리 된다.

```
A better approach to object copying is to provide a copy constructor or copy factory
```

하지만 팀내 여러 영역에서 Copy Factory Method 를 활용하여 개발에 활용하는데 별로 좋지 않은것 같다.

## 1. 테스트 Fixture 생성

```java
public Fixtures {
	public static Person samplePerson = new Person(name = "ndy", age = 10, ...)
}

@Test 
void some_test_with_person(){
	// given
	Person personAge20 = samplePerson.copy(age = 20)

	// when
	// then
}
```

- `copy`, `clone` 라는 이름은 해당 객체를 왜 생성하는지 의미를 드러내지 못한다.
- 뭔가 뭔가 읽었을때 흐름이 이상하다.
	- 나이가 20 살인 사람을 생성하는데 왜 samplePerson 을 복사하지?
	- samplePerson 의 다른 필드는 어떻게 채워지는 거지?



```java
public Fixtures {
	public static createPerson(
		String name = "ndy",
		int age = 10 
	) {
		return new Person(name, age)
	}
}

@Test 
void some_test_with_person(){
	// given
	Person personAge20 = createPerson(age = 20)

	// when
	// then
}
```
- 그나마 이게 더 낫다고 생각함.  samplePerson 의 다른 필드는 어떻게 채워지는 거지? 에 대한 의문은 여전히 있음. 하지만 그 필드에 대한 검증을 안 하면 됨

## 2. 객체를 조금식 채워나가는 방식으로 작성된 코드

```java
static class Response{
	String a
	String b
}

static class ServiceA {
	Response callA(response: Response) 
}

static class ServiceB {
	Response callB(response: Response) 
}

public HelloController {
	ServiceA serviceA;
	ServiceB serviceB;

	Response hello(){
		// 

		Response response = new Response();
		Response responseA = serviceA.call(response)
		Response responseB = serviceB.call(response)
		
		response.copy(a = responseA.a)
		response.copy(b = responseA.b)
		return response
	}
}
```

- 왜 Response 를 받아서 Response 를 반환하는 거지?
- ServiceA 와 ServiceB 에서는 각각 어떤 필드가 채워지는 거지?
- 이걸 어떻게 읽으라고... 세상이 밉다.


```java
static class Response{
	String a
	String b
}

static class ServiceA {
	String callA(someRequiredFiledsForCallA ...) 
}

static class ServiceB {
	String callB(someRequiredFiledsForCallB ...) 
}

public HelloController {
	ServiceA serviceA;
	ServiceB serviceB;

	Response hello(){
		// 

		String a = serviceA.call(...)
		String b = serviceB.call(...)
		
		return new Response(a, b)
	}
}
```

각 인터페이스에 맞는 파라미터와 반환타입을 정의하자. clone 을 남용하지 말자.