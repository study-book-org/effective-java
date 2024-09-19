---
title: ndy
---
# Item 25: Limit source files to a singlt top-level class

![[images/ndy.png]]

https://chatgpt.com/share/66ec1cbe-9ab8-8002-a7ab-6c2bf9cc37a7

충분히 intellij 의 경고가 주어지고 `public` 으로는 선언되지 않기때문에 활용하려고 해도 매우 한정적임.

그나마 통일성을 갖추어 사용한다면 꽤 유용하게 사용할 수 있을것 같은 사례

- 단순 DTO 클래스를 모아서 관리하는 정도로 활용하면 꽤 유용하다고 생각함.
- https://github.com/raeperd/realworld-springboot-java/tree/master/src/main/java/io/github/raeperd/realworld/application/article

하지만...

> [!QUOTE]
> **Never put multiple top-level classes or interfaces in a single source file**
> 이러한 격언이 돌아다니는 만큼 여러 사람과 함께하는 프로젝트라면 충분한 설득이 필요할 것임
