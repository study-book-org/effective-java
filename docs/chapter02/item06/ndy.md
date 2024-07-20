---
title: ndy
---
# Item 6: Avoid creating unnecessary objects

> [!QUOTE] 참고 자료
> - [아임쿨 - Effective Java Item 6](https://velog.io/@imcool2551/Effective-Java-Item6-불필요한-객체-생성을-피하라)

> [!NOTE] 
> - 불필요한 객체를 만들지 마라!
> - 공통적으로 미리 처리될 수 있는 것은 미리 처리해서 재활용 하라
> 	- e.g.) regex Pattern compile, Database Connection
> 

- Map 의 `keySet` 은 조회 당시의 keySet 을 카피 뜨는것이 아니라 Map 의 keySet 을 바라보는 어댑터를 제공한다.
https://www.programiz.com/online-compiler/34XGY4TmYw8YN