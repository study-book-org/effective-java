---
title: ndy
---
# 아이템 8. finalizer와 cleaner 사용을 피하라


> [!QUOTE] 참고 자료
> - https://olrlobt.tistory.com/76

> [!NOTE] 요약
> Finalizer 는 예측할수 없고, 위험하고, 보통 필요없다.
> Cleaners 는 Finalizer 보다는 덜 위험 하지만 마찬가지로 예측할 수 없고, 보통 필요없다.


Java에서는 객체의 메모리 관리를 위해 `finalizer`와 `cleaner`를 제공합니다. 각각에 대해 설명해드리겠습니다.

## What is...
### Finalizer?

**Finalizer**는 Java에서 객체가 더 이상 사용되지 않을 때, 즉 가비지 컬렉션(Garbage Collection)이 되기 전에 호출되는 메서드입니다. `finalize()` 메서드는 `java.lang.Object` 클래스에 정의되어 있으며, 사용자가 이 메서드를 오버라이드할 수 있습니다.

#### 특징:

1. **목적**: 객체가 가비지 컬렉션되기 전에 자원을 해제하거나 정리하는 데 사용됩니다.
2. **사용법**:
```java
@Override
protected void finalize() throws Throwable {
    try {
        // 자원 해제 코드
    } finally {
        super.finalize();
    }
}
```

3. **단점**:

- 예측 불가능성: `finalize()` 메서드가 언제 호출될지 정확히 알 수 없습니다.
- 성능 저하: `finalize()` 메서드는 가비지 컬렉션의 성능을 저하시킬 수 있습니다.
- 신뢰성: `finalize()`가 호출되지 않을 수도 있습니다.

### Cleaner?

Java 9부터는 `Cleaner` 클래스를 사용하여 더 안전하고 효율적으로 자원을 정리할 수 있습니다. `Cleaner`는 `java.lang.ref.Cleaner` 패키지에 속해 있습니다.

#### 특징:

1. **목적**: `Cleaner`는 객체가 가비지 컬렉션될 때 호출되는 정리 작업을 등록할 수 있게 해줍니다.
2. **사용법**:
```
public class Resource implements AutoCloseable {
    private static final Cleaner cleaner = Cleaner.create();
    private final Cleaner.Cleanable cleanable;
    private final State state;

    private static class State implements Runnable {
        // 자원 상태 및 정리 작업 정의
        @Override
        public void run() {
            // 자원 해제 코드
        }
    }

    public Resource() {
        this.state = new State();
        this.cleanable = cleaner.register(this, state);
    }

    @Override
    public void close() {
        cleanable.clean();
    }
}

```
**장점**:

- 명시적 정리: `Cleaner`는 명시적으로 객체가 정리되는 시점을 관리할 수 있습니다.
- 예측 가능성: `Cleaner`는 보다 예측 가능하게 자원을 해제할 수 있습니다.
- 성능: `Cleaner`는 `finalize()`보다 성능이 우수합니다.
