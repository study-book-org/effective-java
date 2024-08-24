---
title: ndy
---
## 관련 예시

### `java.awt.Point` - 👎

잘못 설계 되었지만 하위 호환성을 위해 유지중이다.

```java
package java.awt

public class Point extends Point2D implements java.io.Serializable {
    public int x;
	public int y;
```
### `java.util.HashMap#Node` - 👍

default 접근제어자를 가진 정적 내부 클래스에서 default 접근제어자를 가진 필드를 가지는 경우

```java
package java.util

public class HashMap ... {

    static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;

        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
}
```
