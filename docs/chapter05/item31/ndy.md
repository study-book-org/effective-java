---
title: ndy
---
# ITEM 31: USE BOUNDED WILDCARDS TO INCREASE API FLEXIBILITY


> [!QUOTE] 참고자료
> - Joshua Bloch 가 설명해주는 Wildcards - https://youtu.be/V1vQf4qyMXg?t=1347

Parameterized type 은 invariant 하다. (note Array 는 아님)

![[excalidraws/parameterized-types-are-invariant.excalidraw.png]]

리스코프 치환 원칙 (LSP) 

- 부모가 할 수 있는 모든 행위를 자식으로 대체 되어도 아무 문제 없어야 한다.
- Object 가 할 수 있는 equals/ hashcode 등의 메서드는 String 에서도 아무 문제 없이 사용할 수 있다. Client 가 (실제 String) 인 타입을 Object 처럼 활용했다면 타입 선언을 String 으로 바꾸어도 아무 문제가 없다.
- 반면 List\<Object> 가 할 수있는 `Object` 를 담는 행위를 List\<String> 은 할 수 없다.


---
이는 상당히 직관적이지 않다.
- is-a 관계로 읽었을때 `문자열 List` is-a `객체 List` 이다.  (왜냐하면 `문자열` is-a `객체` 임)
- Object 를 담을 수 있는데 String 을 담을 수 없다? 그러면 String 을 굳이 굳이 List\<Object> 에 담기 위해서 형 변환을 다시 해 주어야 한다.
- 상당히 번거롭고 더 좋은 방법이 필요하다...

    USE BOUNDED WILDCARDS TO INCREASE API FLEXIBILITY


- List\<String> is a subtype of List\<? extends Object>
- List\<Object> is a subtype of List\<? super String>

## A Mnemonic for Wildcard Usage

- **PECS** - **P**roducer **e**xtends, **C**onsumer **s**uper
	- For a T producer, use Foo\<? extends T>
	- For a T consumer, use Foo\<? super T>

- Only applies to input parameters
	- Don't use wildcard types as return types


![[images/pecs-table.png]]


---

## Two possible declarations for the swap method 

> [!QUOTE] 참고 자료
> 토비가 설명해주는 generic 메서드 선언 - https://youtu.be/ipT2XG1SHtQ?t=3128

- public static \<E> void swap(List\<E> list, int i, int j); 
- public static void swap(List\<?> list, int i, int j);
	- 나는 list 의 원소에 대한 것은 신경안쓸래

두번째 가 낫다.

- **만약 메서드 선언에 타입 매개변수가 한 번만 나오면 와일드카드로 대체하자.**

