# Spring-basic
Study Spring basic

----------------------
## [View 환경설정]

* 컨트롤러에서 리턴 값으로 문자를 반환하면 기본적으로 뷰 리졸버가 화면을 찾아서 처리한다.   
  Spring boot는 기본 위치가 resources:templates/ (viewName) + .html임
  > 참고: spring-boot-devtools 라이브러리를 추가하면 html파일을 컴파일만 해주면 서버 재시작 없이   
  > View 파일 변경이 가능하다. (IntelliJ 컴파일 방법 : 메뉴 build -> Recompile)

  
## [빌드하고 실행하기]
* 윈도우는 정식 OpenJDK는 시스템 자바 버젼 변경이 잘 안됨 오라클에서 직접 다운받는것이 좋음
* 빌드 및 실행하기 잘 알아두기