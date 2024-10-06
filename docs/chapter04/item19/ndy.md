---
title: ndy
---
# Item19. 상속을 고려해 설계하고 문서화하라. 그러지 않았다면 상속을 금지하라

클래스가 상속을 고려하여 설계되고 문서화 된다는 것의 의미.

## 1. 클래스는 overridable 할 수 있는 메서드를 override 했을때 발생하는 영향을 정확히 문서화해야 한다.

	- overridable 메서드 = `non-final/ public, protected` 메서드

이는 java 8 에 추가된 javadoc tag 인 `@implSpec` 에 작성해야 한다.

![[images/Pasted image 20240831190912.png]]

1. 호출되는 재정의 가능한 자기사용 메서드 이름  
2. 호출되는 순서  
3. 호출 결과에 따른 영향도  
4. 재정의 시 발생할 수 있는 모든 상황


@Implspec 의 트레이드오프

![[excalidraws/tradeoff.excalidraw.png]]


## 2. 상속을 고려한 클래스 (abstract 클래스, interface 포함) 를 꼭 테스트 해라

- spring-web/src    > [org.springframwork.web.filter.OncePerRequestFilter](https://github.com/spring-projects/spring-framework/blob/main/spring-web/src/main/java/org/springframework/web/filter/OncePerRequestFilter.java)
- spring-web/test  > [org.springframwork.web.filter.OncePerRequestFilterTest.](https://github.com/spring-projects/spring-framework/blob/main/spring-web/src/test/java/org/springframework/web/filter/OncePerRequestFilterTests.java)

## 3. Serializable 클래스를 상속이 가능하도록 디자인 할 때는 더 주의해야 한다.

