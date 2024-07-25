---
title: ndy
---
# 아이템 7. 다 쓴 객체 참조를 해제하라


> [!QUOTE] 참고자료
> - Baeldung > [Guide to WeakHashMap in Java](https://www.baeldung.com/java-weakhashmap)

![[weekHashMapDebugging.png]]

 guava/ caffeine 등의 주요 로컬 캐시 라이브러리도 WeakKey 개념을 지원한다.
- https://guava.dev/releases/23.0/api/docs/com/google/common/cache/CacheBuilder.html
- https://github.com/ben-manes/caffeine/wiki/Eviction#reference-based
