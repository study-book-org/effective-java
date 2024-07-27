---
title: ndy
---
# 아이템 9. try-finally보다는 try-with-resources를 사용하라

> [!NOTE] 요약
> 정리해주어야 하는 자원이 있다면. 항상 try-finally 대신 try-with-resources 를 사용해라.
> 짧고, 간결하며, 더 유용한 예외가 발생한다.

try-with-resources는 Java 7에 추가되었습니다. Java 7부터 도입된 이 기능은 `java.lang.AutoCloseable` 인터페이스를 구현하는 리소스를 자동으로 닫아줘서 리소스 누수를 방지하고, 코드의 가독성을 높여줍니다. try-with-resources를 사용하면 코드에서 명시적으로 close 메서드를 호출할 필요 없이 try 블록이 종료되면 자동으로 리소스가 닫히게 됩니다.

AS-IS

```java
// try-finally - No longer the best way to close resources!
static String firstLineOfFile(String path) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(path));
    try {
        return br.readLine();
    } finally {
        br.close();
    }
}

// try-finally is ugly when used with more than one resource!
static void copy(String src, String dst) throws IOException {
    InputStream in = new FileInputStream(src);
    try {
        OutputStream out = new FileOutputStream(dst);
        try {
            byte[] buf = new byte[BUFFER_SIZE];
            int n;
            while ((n = in.read(buf)) >= 0)
                out.write(buf, 0, n);
        } finally {
            out.close();
        }
    } finally {
        in.close();
    }
}

```


TO-BE
```java
// try-with-resources - the best way to close resources!
static String firstLineOfFile(String path) throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
        return br.readLine();
    }
}

// try-with-resources on multiple resources - short and sweet
static void copy(String src, String dst) throws IOException {
    try (InputStream in = new FileInputStream(src);
         OutputStream out = new FileOutputStream(dst)) {
        byte[] buf = new byte[BUFFER_SIZE];
        int n;
        while ((n = in.read(buf)) >= 0)
            out.write(buf, 0, n);
    }
}

```
