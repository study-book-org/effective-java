---
comments: true
---
# 05주차 - Item 13 ~ 15

## 개요

- 일시: 2024-08-18 10:00 ~ 
- 인원: ndy, smj, joon

## 추가 자료

- [Baeldung > Access Modifiers in Java](https://www.baeldung.com/java-access-modifiers#canonical-order-of-modifiers)
	- Java Modifiers 수식어(?)의 작성 순서
	- Annotation 이 중간에 올 수도 있다!
		- `final private static @Id long ID = 1;`
- Josh Bloch on Design, [“Copy Constructor versus Cloning”](https://www.artima.com/articles/josh-bloch-on-design)
	- clone 메서드 대신 권장하는 방법

## 공유

| Item                          | ndy                                                                                                    | smj                                                | joon                                                                                                                      |
| ----------------------------- | ------------------------------------------------------------------------------------------------------ | -------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------- |
| 아이템 13. clone 재정의는 주의해서 진행하라  | [🤖](https://chatgpt.com/share/f894fa7d-9643-4e11-aec1-084c20134e10)[📄](../chapter03/item13/ndy.md)📢 | [🔗](https://shinminjin.github.io/posts/item13/)   | [🔗](https://wonjoon.gitbook.io/joons-til/books/effective-java/item13.-override-clone-judiciously)                        |
| 아이템 14. Comparable을 구현할지 고려하라 | [🔗](https://www.baeldung.com/java-comparator-comparable)                                              | [🔗](https://shinminjin.github.io/posts/item14/)📢 | [🔗](https://wonjoon.gitbook.io/joons-til/books/effective-java/item14.-consider-implementing-comparable)                  |
| 아이템 15. 클래스와 멤버의 접근 권한을 최소화하라 | [🔗](https://lima1016.tistory.com/105?category=998504)📢                                               | [🔗](https://shinminjin.github.io/posts/item15/)   | [🔗](https://wonjoon.gitbook.io/joons-til/books/effective-java/item15.-minimize-the-accessibility-of-classes-and-members) |
