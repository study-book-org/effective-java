---
title: ndy
---
# Item23: Prefer class hierarchies to tagged classes

### tagged class 

개인적으로 가장 안 좋아하는 패턴 중 하나.

tagged class 는 아니고 tagged struct (?) 이다.
```thrift
struct SomeFile {
  // 파일의 타입 - "pdf" or "eml" ...
  1:    required string fileType
  2:    required string fileHash
  3:    required string fileSize

  // PDF 타입의 파일에만 관련된 필드
  100 : optional string pdfField1
  101 : optional string pdfField2

  // EML 타입의 파일에만 관련된 필드
  200 : optional string emlField1
}
```

책에서 이야기하는 단점

- Cluttered with boilerplate
- Memory footprint is increased
- Readability is further harmed


책에서 이야기 하는 대안

- class hierarchy. 공통 필드를 abstract class 로 추출하고 각 태그에 해당하는 하위 타입을 정의한다.


장점

- code is simple and clear, containing none of the boilerplate
- eliminates the possibility of a runtime failure due to a missing switch case
- reflect natural hierarchical relationships among types


### tagged class 가 조금더 정당화 되는것 같은 케이스

- IDL - Interface Definition Language
	- 도메인 모델을 이야기하는 논리와 
	  외부 인터페이스를 위한 DTO 모델을 이야기 할때 논리가 조금은 다르게 적용될 수 있지 않을까?

- 모든 요청을 받아서 처리할 수 있는 Gateway 같은 것을 만들고 싶을때?
	- 그 요청 타입을 tagged 하게 정의 해야한다.
	- client 가 분기를 치게 할 수 없으므로

- 팀이 사용중인 thrift 라는 IDL 의 특성
	- 구조체 (타입) 의 optional 필드 추가에 따른 deserialize 를 지원한다.

```thrift
service FileGatewayService {
	void call(1: SomeFile someFile)
}

struct SomeFile {
  // 파일의 타입 - "pdf" or "eml" ...
  1:    required string fileType
  2:    required string fileHash
  3:    required string fileSize

  // PDF 타입의 파일에만 관련된 필드
  100 : optional string pdfField1
  101 : optional string pdfField2

  // EML 타입의 파일에만 관련된 필드
  200 : optional string emlField1
}
```

```scala
class ServiceA {
	val GatewayService gateway 

	def someMethod {
		val paramB = ...
		gateway.call(paramB)
	}
}
```

- 이때 GatewayService 의 개발자는 ServiceA 의 코드와 관계없이 optional 필드를 추가해도 된다.
- 파일 타입에 pdf/eml 이 아닌 apk 라는 파일 타입이 추가가 되어도 client 의 코드에는 영향이 가지 않는다.
	- 그리고 필드의 추가도 쉽다.


만약 tagged struct 가 아닌 잘게 구조화된 형태의 아래와 같은 구조였다면 Gateway 라는 이름을 붙일 수 없다. client 에서 각 파일 타입에 대한 구분이 이루어 져야한다.

필드가 아닌 구조체 (클래스)를 추가해야하고 FileService 에 새로운 rpc (메서드) 가 정의되어야 하고 
무엇보다 이를 사용하는 측에서 코드의 변경이 없어도 된다.

```thrift
service FileService {
	void callPdf(1: SomePdf somePdf)
	void callEml(1: SomeEml someEml)
	// void callApk(1: SomeApk someApk)
}

struct BaseFile {
  2:    required string hash
  3:    required string size	
}

struct SomePdf {
  1: required BaseFile file

  // PDF 타입의 파일에만 관련된 필드
  100 : optional string pdfField1
  101 : optional string pdfField2
}

struct SomeEml {
  1: required BaseFile file

  // EML 타입의 파일에만 관련된 필드
  200 : optional string emlField1
}

/*
struct SomeApk {
  1: required BaseFile file

  // APK 타입의 파일에만 관련된 필드
  300 : optional string apkField1
}
*/
```

사실 저런 Gateway 성 외부 Interface 자체가 좋은 방식인지는 의문이다.


---

```java
abstract class BaseIdEntity {
    abstract int id();
}

class Circle extends BaseIdEntity {
    final double radius;

    Circle(double radius) { this.radius = radius; }
}

class Rectangle extends BaseIdEntity {
    final double length;
    final double width;

    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
}
```