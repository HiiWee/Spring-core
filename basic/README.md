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


## [API]
* Spring 웹 개발에서 이야기 하는 API 방식에 대해서 알아보자
  * 정적 컨텐츠 방식을 제외하면 MVC방식에서
    1. View를 찾아서 Template 엔진을 통해서 화면 렌더링 후 HTML을 웹 브라우저에 넘겨주는 방식이 있고
    2. API를 이용하는 방식이 있다.
    > 정적 컨텐츠를 제외하면 위 두가지를 알고 있으면 된다.

  ```java
      @GetMapping("hello-string")
      @ResponseBody   // html에 나오는 body태그가 아닌 http에서 body부분에 데이터를 직접 넣겠다는 의미
      public String hellpString(@RequestParam("name") String name) {
          return "hello " + name;
      }
  ```
  > 이전에 템플릿 엔진은 view라는 템플릿이 있는 상황에서 조작하는 방식이지만, api는 데이터를 그대로 보낸다.   
  > 또한 @ResponseBody는 ViewResolver를 사용하지 않는다.   
  > 위와같이 문자로 하는 방식은 거의 쓸 일이없지만 아래 방식은 많이 사용된다.

* Json 형식으로 출력하기
  * json : JSON은 속성-값 쌍 또는 "키-값 쌍"으로 이루어진 데이터 오브젝트를 전달하기 위해   
    인간이 읽을 수 있는 텍스트를 사용하는 개방형 표준 포맷이다. <위키백과>
  * 과거에는 xml 방식으로 많이 사용됐지만, 무겁다는 단점이 존재 하지만 Json은 더 간단하고 직관적임
  * 따라서 Spring도 객체를 반환하고 @ResponseBody를 달면 default로 Json 타입으로 변환된다.(**Json쓰자**)
  
  > getter and setter는 자바 빈 규약에 속한다. 필드는 private이므로 이용하기 위해선 getter and setter를 이용   
  > 혹은 프로퍼티 접근방식이라고도 한다.

  ```java
  @GetMapping("hello-api")
  @ResponseBody
  public Hello helloApi(@RequestParam("name") String name) {
      Hello hello = new Hello();
      hello.setName(name);
      return hello;
  }

  /// static class는 클래스안에서 클래스를 또 사용할 수 있다.
  static class Hello {
      private String name;

      public String getName() {
          return name;
      }

      public void setName(String name) {
          this.name = name;
      }
  }
  ```
  * **ResponseBody 사용원리**
    * localhost:8080/hello-api?name=spring!!를 톰켓내장서버가 이를 스프링에게 넘김
    * Spring은 hello-api 매핑된 메소드를 찾고 @ResponseBody도 찾음
    * Http 응답에 그대로 데이터를 넘기는 방식으로 동작함(HTTP의 BODY에 문자내용을 직접 반환)
    * 하지만 문자가 아니라 객체임 (문자는 그대로 반환)
    * 스프링은 객체를 default로 Json방식으로 데이터를 만들어서 Http응답에 반환한다. return(name:spring!!)
      * 디테일하게 @ResponseBody를 보면 ViewResolver 대산 HttpMessageConverter가 동작한다.
      * 만약 단순 문자라면 StringConverter가 객체라면 JsonConverter가 동작한다.
    * JsonConverter는 Json타입으로 변경후 나를 요청한 웹 브라우저에게 보내준다.
      > 기본 문자처리 : StringHttpMessageConverter   
        기본 객체처리 : MappingJackson2HttpMessageConverter(객체를 Json으로 바꿔주는 라이브러리)   
        외에도 byte처리 등등 여러 HttpMessageConverter가 기본으로 등록되어 있다.   <br><br>
        **[참고]:** 클라이언트의 HTTP Accept 해더(요청시 특정 포멧으로 받을 수 있게 선택가능)와   
        서버의 컨트롤러 반환 타입 정보 둘을 조합해서 `HttpMessageConverter`가 선택된다.


* **[정리]**
  * `정적 컨텐츠` : 파일을 그대로 내려준다. 
  * `MVC와 템플릿 엔진` : 템플릿 엔진을 MVC방식으로 쪼개고, View를 템플릿 엔진으로 html을 프로그래밍
  이후 고객에게 렌더링된 html을 전달한다.  
  * `API` : 일반적으로 말하는 api방식은 @ResponseBody를 사용하고 객체를 반환(return 객체)하는 것이다.   
    `MappingJackson2HttpMessageConverter`를 이용해 Json으로 변경해 반환함   
    view없이 바로 HTTP response에 값을 넣어서 반환한다.

























