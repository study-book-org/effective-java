---
title: echo
---

# equals는 일반 규약을 지켜 재정의하라 
재정의 하지 않으면 참조값이 같은 것, 즉 자기 자신과만 동일하게 여긴다.

다음과 같으 상황에선 재정의 하지 않아도 된다.
- 논리적 동치성을 검사할 일이 없다.
- 각 인스턴스가 본질적으로 고유하다.
- 상속받은 상위 클래스에 equals가 딱 맞다.
- equlas를 사용할 일이 없다. 

호출을 막기 위해서 다음과 같이 정의해두자
```java
@Override
public boolean equals(Object o){
    throw new AssertionError();
}
```
equals 규약을 어기면 다른 객체들이 어떻게 반응할지 알 수 없다.

참고자료 : https://sysgongbu.tistory.com/169