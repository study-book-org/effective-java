---
title: echo
---

# try-finally 보다는 try-with-resources를 사용하라
자바 라이브러리에서는 close 메서드를 호출해 직접 닫아줘야하는 자원이 많다.
InputStream, OutputStream, java.sql.Connection 등이 좋은 예다.

전통적으로는 이를 해제하기 위해서 try-finally를 사용했지만, 자원이 2개 이상이 되면 지저분해진다.

이를 해결하기 위해서 try-with-resources를 사용하면 된다. 사용하기 위해서는 AutoClosable을 구현해야한다.

