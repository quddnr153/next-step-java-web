# next-step-java-web
자바 웹 프로그래밍 Next Step

Study plan
- 각 Chapter 별로 branch를 만들어 코드 공유

* Chapter 3 - [Web app version - 1.0](https://github.com/quddnr153/next-step-java-web/tree/chapter-3-basic-web-app)
* Chapter 4 - [Web app version - 1.1](https://github.com/quddnr153/next-step-java-web/tree/chapter-4)

## Requirements
1. 요청 데이터를 처리하는 로직을 별도의 클래스로 분리한다 (HttpRequest).
- 클라이언트 요청 데이터를 담고 있는 InputStream 을 생성자로 받아 HTTP 메소드, URL, 헤더, 본문을 분리하는 작업을 한다.
- 헤더는 Map<String, String> 에 저장해 관리하고 getHeader("필드 이름") 메소드를 통해 접근 가능하도록 구현한다.
- GET 과 POST 메소드에 따라 전달되는 인자를 Map<String, String> 에 저장해 관리하고 getParameter("인자 이름") 메소드를 통해 접근 가능하도록 구현한다.