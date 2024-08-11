---
title: ndy
---
# 아이템 10. equals는 일반 규약을 지켜 재정의하라

## Equals 메서드가 필요 없는 경우

- 객체가 본질적으로 유일한 경우 (Singleton ?)
- 필요 없는 경우 (논리적 동등성을 제공할 필요가 없는 경우)
- 상위 클래스에서 제공하는 equals 메서드로 충분한 경우
- 호출 될 일이 없는 경우 (default, private modifier)

## RULE 1. Reflective - 반사성
임의의 객체 a 에 대해 `a.equals(a) == true`

```java
public class Item10Main2 {  
  
  
    static class Person{  
        String name;  
  
        public Person(String name) {  
            this.name = name;  
        }  
  
        @Override  
        public boolean equals(Object o) {  
            return this != o;  
        }  
  
        @Override  
        public int hashCode() {  
            return Objects.hash(name);  
        }  
    }  
    public static void main(String[] args) {  
        Person ndy = new Person("ndy");  
        Set<Person> people = Set.of(ndy);  
        System.out.println("people.contains(ndy) = " + people.contains(ndy));  
    }  
}
```

- **결과**: 객체를 키로 사용하는 해시맵(`HashMap`)이나 해시셋(`HashSet`)에서 의도치 않은 동작을 초래할 수 있습니다. 예를 들어, 자신을 키로 가진 값이 있는 맵에서 그 값을 찾지 못할 수 있습니다.
## RULE 2. Symmetric
임의의 객체 a,b 에 대해 `a.equals(b) == b.equals(a)`

- **결과**: 두 객체가 같다는 판단이 상황에 따라 달라질 수 있어, 컬렉션의 검색, 삭제, 포함 여부 확인 등의 동작이 불안정해질 수 있습니다.
## RULE 3. Transitive
임의의 객체 a,b,c 에 대해 `if a.equals(b) && b.equals(c) => a.equals(c)`

- **결과**: 객체의 동등성에 대한 일관성이 깨지게 되어, 컬렉션에서 객체를 찾거나 조작할 때 예기치 않은 동작이 발생할 수 있습니다. 예를 들어, 트리 구조를 사용하는 컬렉션(예: `TreeSet`)에서 잘못된 정렬이나 중복 제거가 발생할 수 있습니다.

---
## RULE 4/5
- **일관성 (Consistent)**: `x.equals(y)`의 결과는 객체가 변경되지 않는 한 항상 동일해야 합니다. 즉, 여러 번 호출하더라도 결과는 변하지 않아야 합니다.    
- **null-아님**: `null`과의 비교에서는 항상 `false`를 반환해야 합니다. 즉, `x.equals(null)`은 항상 `false`여야 합니다.
```java title="null 과의 ==, equals 비교"
public class Item10Main {  
  
    public static void main(String[] args) {  
        Integer a = null;  
        System.out.println("a==null = " + a == null);  
        System.out.println("a.equals(null) = " + a.equals(null));  
    }  
}
```

```text title="실행결과"
false
Exception in thread "main" java.lang.NullPointerException: Cannot invoke "java.lang.Integer.equals(Object)" because "a" is null
	at chapter3.ndy.Item10Main.main(Item10Main.java:7)

```


```java
public class MyClass {
    private int id;
    private String name;

    // Constructor, getters, and setters

    @Override
    public boolean equals(Object obj) {
        // 자기 자신과의 비교인 경우
        if (this == obj) {
            return true;
        }
        
        // null과의 비교인 경우
        if (obj == null) {
            return false;
        }
        
        // 동일한 클래스인지 확인
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        // 형 변환 후 필드 비교
        MyClass other = (MyClass) obj;
        return this.id == other.id && (this.name == null ? other.name == null : this.name.equals(other.name));
    }

    @Override
    public int hashCode() {
        // equals를 재정의하면 hashCode도 재정의해야 함
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + (name == null ? 0 : name.hashCode());
        return result;
    }
}

```
## 마무리

- Item 11 equals를 재정의하려거든 hashCode도 재정의하라
- 너무 어렵게 쓰지 마라 - 단순하게 작성해라
- 가능하면) IDE 나 AutoValue/Lombok 등을 사용해라


