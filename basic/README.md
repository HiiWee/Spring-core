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


## [정적 컨텐츠]
* 정적 컨텐츠 : 웰컴 페이지와 같은 파일을 그대로 웹 브라우저에 내려줌
* MVC와 템플릿 엔진 : 가장 많이 하는 방식, 템플릿 엔진(JSP, PHP)를 이용해 서버에서 프로그래밍해 HTML을 동적으로   
  변경해 웹 브라우저에 내려준다. MVC패턴을 많이 이용함
* API : 만약 안드로이드, 아이폰 클라이언트를 개발할 경우 서버에선 JSON 데이터 구조 포멧으로 클라이언트에게 전달함
이것이 요즘  API 방식이다. (Vue.js, React등을 사용할 때 api로 데이터만 내려주면 화면은 클라이언트가 알아서 그리고 정리함)   
혹은 서버끼리 통신할 경우 HTML이 필요없으므로 api 방식을 많이 이용한다.`

* 오늘은 정적 컨텐츠를 알아보자
  * 스프링 부트는 정적 컨텐츠를 기본적으로 지원해준다. (main/resources/static/*)
    * 폴더내 hello-static.html이 존재하면 서버 실행 후 http://localhost:8080/hello-static.html에 접속하면   
      볼 수 있다. 원하는 파일을 넣으면 되지만 이곳에는 어떤 프로그래밍을 할 수 는 없고 그대로 반환된다.



## [MVC와 템플릿 엔진]

* MVC : Model, View, Controller
  * 과거에는 Controller와 View가 분리되어 있지 않았음 --> Model 1 방식(JSP)
  * 현재는 View와 Controller를 분리함
    * 관심사를 분리해야함(역할과 책임)
    * 따라서 View는 화면을 그리는것에 모든 역량을 집중해야 한다.
    * Controller나 Model관련 있는 부분은 비즈니스 로직이나 내부적인걸 처리하는것에 집중해야한다.
    * 그리고 Model에 View에 필요한 데이터를 담아 View로 넘겨주는 패턴을 많이 사용한다.

* 타입리프의 장점
  * 파일을 우측클릭해 copy path시 absolute path를 복사하면 html을 서버없이 바로 열어봐도 html파일을 볼 수 있음.

* @RequestParam
  * required가 기본으로 true임 따라서 url에 값을 넣지 않고 mapping된 url만 조회하면 오류 발생


```java
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }
```
> 먼저 localhost:8080/hello-mvc?name=spring!! 으로 입력시 Spring boot 내장 톰켓 서버를 거친다.   
> 내장 톰켓 서버에선 스프링에게 hello-mvc를 던져주고 스프링은 매핑정보를 확인해 위 메소드를 호출한다.   
> 이후 @RequestParam으로 받은 name의 값(spring!!)을 Model에 담고 return 값 "hello-template"을 스프링에게 반환   
> 스프링의 viewResolver에서 return값과 동일한 templates/hello-template.html을 찾아서 Thymeleaf 템플릿 엔진에게 처리를 맡김   
> Thymeleaf 템플릿 엔진은 이를 받아서 렌더링 하고 변환한 html을 웹 브라우저에 반환해준다. (정적 컨텐츠와의 차이)










































