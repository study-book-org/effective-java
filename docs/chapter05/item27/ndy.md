---
title: ndy
---
# Item27: Eliminate unchecked warnings

Eliminate every *unchecked warning* that you can.

> [!NOTE] unchecked warning
>  - 컴파일러가 타입안정성을 보장할 수 없는 코드에 대해서 발생시키는 경고

- If you can’t eliminate a warning, but you can prove that the code that provoked the warning is typesafe, then (and only then) suppress the warning with an @SuppressWarnings("unchecked") annotation

- Always use the SuppressWarnings annotation on the smallest scope possible.

- Every time you use a @SuppressWarnings("unchecked") annotation, add a comment saying why it is safe to do so.

---

java 의 예시

- ![[images/ndy.png]]

- ![[images/ndy2.png]]

- https://velog.io/@bedshanty/자바의-ArrayList가-제네릭-타입-E가-아닌-Object를-저장하는-이유