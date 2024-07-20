---
title: ndy
---
# Item 5: Prefer DI to hardwiring resources

## DI Motivation & Introduction

많은 클래스는 하나이상의 다른 리소스에 의존합니다. 

앞선 아이템에서 살펴본 `Static utility classes` 와 `Singltons` 는 이런 상황에 적절하지 않습니다. 의존은 바뀔 가능성이 있습니다. 따라서, 변경에 유연해야 합니다.

이를 해결하는 아주 간단한 디자인 패턴이 DI 입니다.

```java
// Dependency injection provides flexibility and testability 
public class SpellChecker { 
	private final Lexicon dictionary; 
	
	public SpellChecker(Lexicon dictionary) { // (1)
		this.dictionary = Objects.requireNonNull(dictionary); 
	} 
	
	public boolean isValid(String word) { ... } 
	public List suggestions(String typo) { ... } }
```

1. SpellChecker 는 Lexicon 의 구체 구현에 관심이 없다.

## DI Framework

Dependency 가 많아지고 복잡해 질 수록 이를 관리하는것은 매우 까다롭습니다.

애플리케이션 코드 내부의 컴포넌트 간 의존성 관점에서 관리가 어려운 이유는 다음과 같습니다:

1. **복잡한 상호 의존성**:
    
    - 컴포넌트 간의 상호 의존성이 복잡해지면, 하나의 컴포넌트를 변경할 때 연쇄적으로 다른 컴포넌트에도 영향을 미칠 수 있습니다. 이는 시스템 전체의 안정성을 저하시킵니다.
2. **변경의 전파**:
    
    - 하나의 컴포넌트가 변경될 때, 이 컴포넌트에 의존하는 다른 컴포넌트들도 변경되어야 할 수 있습니다. 이는 유지보수와 개발 속도를 저하시키고, 코드 변경의 영향을 예측하기 어렵게 만듭니다.
3. **테스트의 복잡성**:
    
    - 의존성이 많아질수록 개별 컴포넌트를 독립적으로 테스트하기 어려워집니다. 서로 의존하는 컴포넌트들을 모두 포함한 통합 테스트가 필요하게 되어 테스트의 복잡성과 비용이 증가합니다.

이를 해결하는 `dependency injection framework` 가 등장하였습니다. 
- Dagger/ Guice/ Spring

