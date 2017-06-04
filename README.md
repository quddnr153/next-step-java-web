# next-step-java-web
자바 웹 프로그래밍 Next Step

Study plan
- 각 Chapter 별로 branch를 만들어 코드 공유

* Chapter 3 - [Web app version - 1.0](https://github.com/quddnr153/next-step-java-web/tree/chapter-3-web=app)
* Chapter 4 - [Web app version - 1.1](https://github.com/quddnr153/next-step-java-web/tree/chapter-4)

## Requirements
1. index.html 응답하기
- http://localhost:8080/index.html 로 접속했을 때 webapp 디렉토리의 index.html 파일을 읽어 클라이언트에 응답한다.
2. GET 방식으로 회원가입하기
- "회원가입" 메뉴를 클릭하면 http://localhost:8080/user/form.html 으로 이동하면서 회원가입할 수 있다.
3. POST 방식으로 회원가입하기
- http://localhost:8080/user/form.html 파일의 form 태그 method 를 get 에서 post 로 수정한 후 회원가입이 정상적으로 동작하도록 구현한다.
4. 302 status code 적용
- "회원가입"을 완료하면 /index.html 페이지로 이동하고 싶다. 현재는 URL이 /user/create 로 유지되는 상태이기 때문에 응답으로 전달할 파일이 없다. 따라서 회원가입을 완료한 후 /index.html 페이지로 이동한다. 브라우저의 URL 도 /user/create 가 아니라 /index.html 로 변경해야 한다.
5. 로그인하기
- "로그인" 메뉴를 클릭하면 http://localhost:8080/user/login.html 으로 이동해 로그인 할 수 있다. 로그인이 성공하면 /index.html 로 이동하고, 로그인이 실패하면 /user/login_failed.html 로 이동해야 한다.
- 앞에서 회원가입한 사용자로 로그인할 수 있어야 한다. 로그인이 성공하면 쿠키를 활용해 로그인 상태를 유지할 수 있어야 한다. 로그인이 성공할 경우 요청 헤더의 Cookie 헤더 값이 logined=true, 로그인이 실패하면 Cookie 헤더 값이 logined=false 로 전달되어야 한다.
6. 사용자 목록 출력
- 접근하고 있는 사용자가 "로그인" 상태일 경우 (Cookie 값이 logined=true) http://localhost:8080/user/list 로 접근했을 때 사용자 목록을 출력한다. 만약 로그인하지 않은 상태라면 로그인 페이지 (login.html) 로 이동한다.
7. CSS 지원하기
- 지금까지 구현한 소스코드는 CSS 파일을 지원하지 못하고 있다. CSS 파일을 지원하도록 구현한다.