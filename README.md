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
2. 응답 데이터를 처리하는 로직을 별도의 클래스로 분리한다 (HttpResponse).
- RequestHandler 클래스를 보면 응답 데이터 처리를 위한 많은 중복이 있다. 이 중복을 제거해 본다.
- 응답 헤더 정보를 Map<String, String> 으로 관리한다.
- 응답을 보낼 때 HTML, CSS, 자바스크립트 파일을 직업 읽어 응답으로 보내는 메소드는 forward(), 다른 URL로 리다이렉트하는 메소드는 sendRedirect() 메소드를 나누어 구현한다.
