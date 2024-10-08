---
title: echo
---

# finalizer와 cleaner의 사용을 피해라
자바는 두가지 객체 소멸자를 제공한다. 그 중 finalizer는 예측할 수 없고, 상황에 따라 위험할 수 있어 일반적인 사용은 피해야한다.
그래서 자바 9에서는 cleaner를 대체제로 제공하지만 이 또한 불필요하다. 

### 언제 수행 될 지 예측할 수 없다. 
이들은 즉시 수행된다는 보장이 없다.

시스템이 동시에 열 수 있는 파일 갯수는 한계가 있는데, 파일 닫기를 finalizer나 cleaner에게 맡긴다면 오류를 낼 수 있다.

이것의 실행은 GC 알고리즘에 달렸다. finalizer 스레드는 다른 애플리케이션 스레드보다 우선순위가 낮아서 OutOfMemoryError를 발생시킬 수 있다.

상태를 영구적으로 수정하는 작업에서는 절대 finalizer, cleaner에 의존해서는 안된다.

예를 들어서 데이터베이스 같은 공유자원의 영구 락 해제를 finalizer나 cleaner에 맡겨 놓으면 분산 시스템 전체가 서서히 멈춰설 것이다.

System.runFinalizersOnExit, Runtime.runFinalizersOnExit이 확실하게 실행을 보장해주지만, 다른 에러가 있다.

### 효율이 낮다. 시간이 오래 걸린다.

### finalizer 보안 공격에 노출된다.

## 대안>?
AutoCloseable을 구현해주면된다. 그리고 클라이언트에서 인스턴스를 다 쓰고 나면 close 메서드를 호출하면 된다.(일반적으로 예외가 터져도 잘 호출될 수 있도록 try-catch-resources을 사용한다.)

## 그렇다면 적절한 용도
자원의 사용자가 close 메서드를 호출하지 않을 것에 대비한 안정망 역할

네이티브 피어와 연결된 객체에서 사용한다.
네이티브 피어 > 일반 객체가 네이티브 메서드를 통해 기능을 위임한 네이티브 객체를 말한다. ???

