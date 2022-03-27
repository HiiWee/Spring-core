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


## [회원 관리 예제(백엔드) : 비즈니스 요구 사항 정리]
* 데이터 : 회원ID, 이름
* 기능 : 회원 등록, 조회
* 아직 데이터 저장소가 선정되지 않음(가상의 시나리오)
<br><br>

**일반적인 웹 애플리케이션 계층 구조**    
* 컨트롤러 : 웹 MVC의 컨트롤러 역할
* 서비스 : 핵심 비즈니스 로직 구현 (비즈니스 도메인 객체를 가지고 핵심 비즈니스 로직 구현)
* 리포지토리 : 데이터베이스에 접근, 도메인 객체를 DB에 저장하고 관리
* 도메인 : 비즈니스 도메인 객체, 예) 회원, 주문, 쿠폰 등등 주로 데이터베이스에 저장하고 관리됨

<br><br>
**클래스 의존관계**   
* 아직 데이터 저장소가 선정되지 않아서, 우선 인터페이스로 구현 클래스를 변경할 수 있도록 설계
* 데이터 저장소는 RDB, NoSQL등 다양한 저장소를 고민중인 상황으로 가정
* 개발을 진행하기 위해서 초기 개발 단계에선 구현체로 가벼운 메모리 기반의 데이터 저장소 사용


## [회원 도메인과 리포지토리 만들기]
* 자바8 문법 공부 시급하다!!

## [회원 리포지토리 테스트 케이스 작성]
* 개발 기능을 실행하여 테스트시 main 메소드를 통해 실행하거나, 웹 어플리케이션의 컨트롤러를 통해 실행함   
  이는 준비 및 실행에 걸리는 시간이 크고, 반복 실행과 여러 테스트를 실행하기 어렵다.
* JUnit 프레임워크를 이용하면 위와 같은 문제를 해결할 수 있는 테스트를 할 수 있다.
<br><br>


* 각각의 테스트는 작성한 순서가 보장이 되지 않음
  * 따라서 순서에 의존하여 설계하면 안된다.
  * 순서에 의존하지 않고 각 테스트 메소드의 범위만 영향을 주기 위한 방법이 있다.
* 이번 리포지토리 테스트의 경우 repository에 다른 테스트의 결과물 객체들이 저장되어 있었음
* 따라서 하나의 테스트가 끝나면 저장소를 비워주어야 한다.
<br><br>
* **@AfterEach** : 한번에 여러 테스트를 실행하면 메모리 DB에 직전 테스트의 결과가 남을 수 있다.   
이렇게 되면 다음 이전 테스트 때문에 다음 테스트가 실패할 가능성이 있음   
@AfterEach를 사용하면 각 테스트가 종료될 때마다 이 기능을 실행함 

> 테스트는 각각 독립적으로 실행되어야 하며, 테스트 순서에 의존관계가 있는 것은 좋은 테스트가 아니다.

## [회원 서비스 개발]
* 회원 서비스는 회원 리포지토리, 도메인을 활용해 실제 비즈니스 로직을 작성하는 것이다.

* 서비스 클래스는 비즈니스에 가까운 용어를 사용해야 한다.
  * 즉 비즈니스에 의존적으로 설계함
  > 반면에 저장소는 단순히 데이터를 넣고 빼는 작업을 하므로 좀 더 개발적인 용어를 사용함


## [회원 서비스 테스트]
* 테스트는 과감하게 한글명을 가진 테스트 메소드로 만들어도 무관함
  * 빌드 될 때 테스트 코드는 실제 코드에 포함되지 않음


* 테스트는 given, when, then 3가지로 나누어서 작성하는것이 좋음
  * given : 뭔가가 주어졌고
  * when : 이것을 실행했을때
  * then : 이러한 결과가 나와야함
  > 기본적으로 위와 같은 형식을 사용하면서 필요 혹은 상황에 따라 변형하는것을 권장함


* 멤버 서비스 테스트에서 사용되는 멤버 리포지토리는 2개의 객체를 생성해 사용하는 것 보다는 한개의 객체를   
이용하는것이 좋다 이를 위해 자체적으로 저장소 객체를 생성하는 것 보다 생성자를 통한 의존관계 주입이 바람직함   
(`Dependency Injection`)

**[중복_회원_예외() 테스트]**   
* 테스트는 정상적은 flow를 검증하는 것도 중요하지만 회원 서비스의 join에서 예외가 발생되는 부분과 같이   
  실제로 예외적인 상황이 발생했을때 예외가 발생하는지 확인하는 테스트도 중요하다.


## [컴포넌트 스캔과 자동 의존관계 설정]
* 화면을 붙이고 싶음(컨트롤러, 뷰 템플릿 필요)
  * 회원가입 및 결과 html로 뿌리기
* 그러기 위해선 멤버 컨트롤러 필요 --> 멤버 서비스를 통해 회원가입을 하고 데이터를 조회할 수 있어야함
* 회원 컨트롤러가 회원 서비스와 회원 리포지토리를 사용할 수 있게 의존관계를 준비하자.
<br><br>


**[스프링 빈을 등록하는 2가지 방법]**   
  * 컴포넌트 스캔과 자동 의존관계
  * 자바 코드로 직접 스프링 빈 등록하기
<br><br>

**[컴포넌트 스캔과 자동 의존관계 설정]**
* `@Component` 애노테이션이 있으면 스프링 빈으로 자동 등록됨
* `@Controller`컨트롤러가 스프링 빈으로 자동 등록된 이유도 @Controller 내부에 @Component 애노테이션이 존재하기 때문
* `@Component`를 포함하는 다음 애노테이션도 스프링 빈으로 자동 등록된다.
  * `@Controller`, `@Service`, `@Repository`
<br><br>

* 그렇다면 hello.spring밖 패키지에서 @Service를 붙인다고 컴포넌트 스캔의 대상이 될까?
  * 스캔 대상이 아님 기본적으로 _SpringApplication.java가 포함된 패키지의 하위 패키지만 스캔 대상임
  * 후에 다른 설정으로 변경하면 스캔범위를 변경할 수 있다.
<br><br>
* 스프링은 스프링 컨테이너에 스프링 빈을 등록할 때 싱글톤으로 등록한다. (같은 스프링 빈 == 같은 인스턴스)
* 또한 생성자가 1개만 있으면 @Autowired를 생략 가능하다.


## [자바 코드로 직접 스프링 빈 등록하기]
* 회원 서비스와 회원 리포지토리의 @Service, @Repository, @Autowired 애노테이션을 제거한다.

* 이후 SpringConfig.java 파일을 생성하여 스프링 컨테이너에 스프링 빈을 수동으로 등록한다.
  * **MemoryRepository를 다른 저장소로 변경할 것이므로 일단 자바 코드로 스프링 빈을 설정함**

> XML방식은 거의 사용하지 않음   
> 
> **[참고]:** DI에는 필드 주입, setter 주입, 생성자 주입이 있다. 하지만 의존관계가 실행중에    
> 동적으로 변하는 경우는 거의 없으므로 생성자 주입을 권장함   
> 
> **[참고]:** 실무에선 주로 정형화된 컨트롤러, 서비스, 리포지토리는 컴포넌트 스캔을 이용한다.   
> 그리고 정형화 되지 않거나, 상황에 따라 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈으로 등록한다.   
> `현재 저장소는 어떤것을 이용할지 정해지지 않았음 : 따라서 설정을 통한 스프링 빈 등록을 이용한다.
> 컴포넌트 스캔은 여러 파일의 애노테이션을 수정하고 변경해야 하지만 설정파일은 설정만 변경하면 됨`
> 
> 
> **[주의]:** `@Autowired`를 통한 DI는 `helloController`, `MemberService`등과 같이 스프링이 관리하는 객체   
> 에서만 동작한다. 스프링 빈으로 등록하지 않고 내가 직접 생성한 객체에서는 동작하지 않는다.   
> `ex)` SpringConfig가 전부 주석처리 되어있으면 기존의 회원 서비스, 저장소는 스프링에 등록되지 않음 --> @Autowired 동작하지 않음   
> `ex)` 또한 직접 new로 객체를 생성해서 이용하는 것도 스프링 컨테이너에 등록되지 않으므로 @Autowired가 동작하지 않음


## [자바 코드로 직접 스프링 빈 등록하기 : 회원 웹 기능 - 홈 화면 추가]

**회원 웹 기능 - 홈 화면 추가**   
* templates/home.html과 HomeController를 만든다. (mapping은 /로 제일 첫 시작 주소를 받음)
  * 여기서 의문점 기본 localhost:8080주소는 정적 페이지(static/*)을 반환한다고 했는데 반환하지 않음
    * 요청이 오면 먼저 스프링 컨테이너안에서 관련 컨트롤러를 찾고, 그 다음에 static파일을 찾으므로   
      / 주소가 매핑된 컨트롤러인 HomeController를 찾아서 반환하므로 정적 페이지는 보여주지 않는다.

## [회원 웹 기능 - 등록]
* url을 그냥 엔터로 치는것은 GET 방식이므로 @GetMapping에 연결됨
  * 주로 데이터를 등록할때는 POST, 조회시에는 GET방식을 이용한다.
<br><br>

* createMemberForm.html을 추가한다.
  * post 방식으로 /members/new라는 url로 결과를 보낸다.
  * 이때 input태그에 사용자가 입력하고 등록한 값도 key : "name" value = 사용자 입력값 으로 같이 보낸다.
  * `자바 빈 프로퍼티 규약`에 따라 name="name" 은 `setName`이라는 메소드를 찾게된다.
<br><br>
* `DTO 객체`인 `MemberForm`을 생성한다. 
  * 실무에서는 컨트롤러에 넘어오는 정보가 Member가 필요한 데이터 이상으로 많은 데이터들이 들어온다.   
    예를 들어서 회원 정보 뿐만 아니라 약관 정보도 들어오고, 화면을 처리하기 위한 추가 정보들도 들어온다.   
    또는 Member에 필요한 정보들이 화면이 아니라 데이터베이스에 더 있을 수도 있기 때문에 `MemberForm과 Member를 분리`
  * 스프링은 MemberForm에 name이라는 필드에 setName을 이용해   
    name필드에 받아온 key : "name"의 `value값`을 저장한다.
<br><br>
* MemberController에서 값 받아서 회원 등록하기
  * 회원명을 받아서 객체에 담았으니 인자로 MemberForm 객체를 받아와서 이름을 가져온다(`getName`)
  * 이후 Member객체에 이름을 세팅하고 의존관계 주입을 받은 MemberService 객체에 `join` 한다.


## [회원 웹 기능 - 조회]
* 회원 목록을 클릭했을때 조회 동작을 만들어보자
<br><br>

* MemberController에 list 컨트롤러를 만든다. (@GetMapping("/members"))
  * memberService에 저장되어있는 모든 회원들을 List타입으로 받아서 Model객체를 이용해 addAttribute한다.
  * 이후 memberList.html로 전송한다.
<br><br>
* memberList.html 만들기
  * Thymeleaf문법이 사용되는데 먼저 List에 담긴 객체를 EL태그로 받아오고 for Each문을 돌듯이   
    각각의 Member 객체를 받는다.
  * 이후 하나의 객체마다 id와 이름값을 EL태그를 이용해 줄력한다. 이 때 Spring은 getter를 이용해 값을 받아온다.
<br><br>
> 이렇게 만들어진 결과를 보면 먼저 회원의 정보가 메모리 리포지토리에 저장되어 있으므로 스프링을 재시작하면   
> 모두 사라지게 된다. 따라서 데이터를 저장하기 위해 데이터베이스를 연동해야 할 필요성이 있다.


## [스프링 DB 접근 기술 : H2 데이터베이스 설치]
* 지금까지는 MemoryMemberRepository를 사용하는데, 스프링이 재시작하면 기존에 등록된 회원들의 정보가 사라졌다.
  * 서버가 내려간다고 회원정보가 사라지면 안되므로 데이터베이스를 이용해야 한다.
  * 개발이나 테스트 용도로 가볍고 편리한 DB, 웹 화면을 제공하는 H2데이터베이스를 설치해보자
* 데이터베이스 설치 후 DB까지 생성한 다음 접속할때는 TCP로 접속한다.
  >jdbc:h2:~/test에서 jdbc:h2:tcp://localhost/~/test로   
    직접 파일에 접근하게 될 때 하나의 파일은 여러 애플리케이션이 동시에 수정하지 못하도록 락이 걸림   
    TCP를 통해 DB를 접근하면 해당 문제를 DB서버가 중간에서 해결해준다.   
    (이는 H2 DB의 특성임)
<br><br>
* H2 DB를 설치하고 실행한 후 아래와 같이 member 테이블을 생성한다.
  ```sql
  drop table if exists member CASCADE;
  create table member
  (
  id bigint generated by default as identity,
  name varchar(255),
  primary key (id)
  );
  ```
  > `generated by default as identity`는 Member의 id에 값을 세팅하지 않고 insert하면 DB가 들어왔을때 자동으로   
  > id값을 채워준다.

* 또환 프로젝트에 sql디렉토리를 같이 만들고 내부에 위에서 작성한 ddl, dml, dcl등 데이터를 다루는 sql쿼리를   
  모아놓는다. --> git과 같이 버전관리를 할 때 파악하기 용이함


## [순수 JDBC]
* 애플리케이션과 DB를 연동해 기존처럼 메모리 저장이 아닌 DB에 데이터를 저장하고 꺼내는 고전 방식의   
  JDBC를 이용해보자
<br><br>
* 자바는 기본적으로 DB와 붙기 위해선 JDBC 드라이버가 꼭 필요하다(이를 가지고 서로 연동함)
  * 또 DB와 붙을때 데이터베이스가 제공하는 클라이언트도 같이 필요(com.h2database:h2)
* 그리고 `application.properties`에 JDBC url, 드라이버 클래스, DB아이디를 설정해야함
<br><br>

**DataSource 이용하기**   
* 위에서 설정한 application.properties정보를 가지고 `스프링 부트는 DataSource 객체를 생성`한다.   
  DataSource는 데이터베이스 커넥션을 획득할 때 사용하는 객체다. 
<br><br>

**JdbcMemberRepository 생성**   
* 고전의 방식으로 생성된 Jdbc 리포지토리
* 여기서 특징이라면 `RETURN_GENERATED_KEYS`의 사용이다.
  * id값은 DB가 자동생성해주는데 이 값을 알아야 멤버에 setId를 할 수 있다.
  * 따라서 자동으로 생성되어진 id를 가져오는 쿼리를 추가한다. (con.prepareStatement(sql, 이곳에))
  * 이후 pstmt.getGenerateKeys()로 RETURN_GENERATED_KEYS를 매칭해 `DB가 자동생성한 id값을 꺼내온다.`

* Connetion을 이용해 연결을 생성할 때 스프링 프레임워크의 `DataSourceUtils`를 이용하자
  * 이를통해 getConnection()을 하게되면 연결을 호출할 때마다 일일이 새로 생성하는 것이 아니라   
    `동일한 DB Connection을 유지` 시켜준다.(**스프링 프레임워크 이용시 꼭 이렇게 사용하자**)
  * `close`를 할때도 `DataSourceUtils.releaseConnection()`을 이용해 닫아준다. (**동일 DB 커넥션 유지 시켜줌**)
<br><br>

**JdbcMemberRepository를 통한 확장과 SpringConfig에서만 이뤄지는 변경**   
* 스프링 부트는 데이터베이스 커넥션 정보를 바탕으로   
  DataSource객체를 스프링 컨테이너가 미리 생성해 bean으로 관리하므로 @Autowired로 자동 DI 해준다.   
  이후 기존의 MemoryMemberRepository를 JdbcMemberRepository로 변경만 해주면서 DI로 받은 DataSource도 넘겨준다.
* 이렇게 되면 `어떤 코드도 변경하지 않고 JdbcMemberRepository라는 클래스를 만들고(인터페이스를 구현해 확장)   
  스프링이 제공하는 설정 파일만 변경하여 저장소를 변경할 수 있다.`
<br><br>

**스프링을 왜 쓸까?**   
* 위와 같은 상황에서 소위 `다형성`을 활용할 수 있으므로(인터페이스를 두고 구현체를 바꿔끼기 할 수 있음)
* 스프링은 이런 상황에서 편리하게 할 수 있도록 스프링 컨테이너가 지원해줌(`Dependency Injection`)
  > 스프링을 사용하지 않는다면 Service 단에서의 코드 변경이 이루어져야 하지만, 스프링을 이용하면서 부터는   
    기존에 코드는 하나도 손대지 않고 오직 `애플리케이션을 설정하는 코드`(어셈블리)만 변경하면   
    실제 애플리케이션에 관련된 코드는 하나도 `변경 할 필요가 없고` 이를 굉장히 편리하게 쓸수있게 해주는것이   
    `스프링의 큰 장점`이다.

<br><br>
**[정리]**   
* 개방-폐쇄 원칙(OCP, Open-Closed Principle)을 지킨다
  * 확장에는 열려있고, 수정, 변경에는 닫혀있다.   
  (객체지향에서 말하는 다형성 개념을 잘 활용하면 기능을 완전히 변경해도 애플리케이션 전체를 수정 할 필요가 없다.)
* 스프링의 DI(Dependency Injection)을 사용하면 **기존 코드를 전혀 손대지 않고, 설정만으로 구체클래스를 변경** 할 수 있다.
* 회원을 등록하고 DB에 결과가 잘 입력되는지 확인하자.
* 데이터를 DB에 저장하므로 스프링 서버를 다시 실행해도 데이터가 안전하게 저장됨
* 객체지향의 진짜 매력은 인터페이스에서 구현체를 바꾸면서도 기존코드를 변경하지 않고 바꿀 수 있는것!!


## [스프링 통합 테스트]
* 새로만든 리포지토리는 DB까지 연결이 된다. 따라서 DB와 스프링을 같이 올려서 진행하는 통합테스트에 대해 알아보자

* 기존의 MemberServiceTest와 동일한 MemberServiceIntegrationTest를 생성하고 수정해보자
<br><br>

**[MemberServiceIntegrationTest]**   
* 테스트에서 이용되는 구현체(빈)들은 SpringConfig에서 올라옴
* 기존에 @BeforeEach, @AfterEach는 지워준다 --> DB에 직접 데이터를 넣고 빼는 통합테스트 이므로 메모리를 지워   
  주는 작업은 필요하지 않음, @AfterEach같은 경우는 @Transactional를 이용한다.
* 테스트 케이스는 다른곳에서 사용하지 않기 때문에 field injection도 괜찮다.
<br><br>
* `@SpringBootTest` : 스프링 컨테이너와 테스트를 함께 실행한다.
* `@Transactioanl` : 테스트 케이스에 있으면, 테스트 시작전에 트랜잭션을 시작하고, 테스트 완료 후   
  항상 롤백한다. 이렇게 하면 DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지 않는다.   
  만약 서비스에 붙는다면 롤백작업은 하지않고 각 트랜잭션 단위들의 독립성을 유지한다.
<br><br>

**[정리]**
* 그렇다면 스프링이 없는 순수한 자바코드로 이루어진 테스트(단위 테스트)는 안해도 되는것 아닐까?
  * **순수한 단위테스트가 훨씬 좋은 테스트일 확률이 높다.**
  * 단위로 쪼개서 테스트를 잘 할 수 있게하고, 스프링 컨테이너 없이 테스트를 할 수 있도록 훈련을 꼭 해야한다.
  * 스프링 컨테이너를 어쩔 수 없이 올려야 하는 상황이면 테스트 설계가 잘못됐을 확률이 높다.


## [스프링 JdbcTemplate]
* 순수 Jdbc와 동일한 환경설정을 하면 됨
* 스프링 JdbcTemplate과 MyBatis 같은 라이브러리는 JDBC API에서 본 반복 코드를 대부분 제거해준다.   
  하지만 SQL은 직접 작성해야 한다.

* JdbcTemplate은 템플릿 메소드 패턴을 내부에서 많이 이용함

* `rowMapper`
  * 쿼리를 실행 후 객체에 담아 보낼때 객체 생성에 대한 부분을 담당한다.



# [JPA]
* 이전에 JdbcTemplate은 jdbc에 비해 반복적인 코드가 많이 줄어듬, 하지만 SQL은 개발자가 결국 직접 작성해야한다.
* JPA를 사용하면 SQL 쿼리도 JPA가 자동으로 처리를 해주며 개발 생산성을 크게 높여준다.
<br><br>
* JPA는 기존의 반복 코드는 물론이고, 기본적인 SQL도 JPA가 직접 만들어서 실행해준다.
* JPA를 사용하면, SQL과 데이터 중심의 설계에서 객체 중심의 설계로 패러다임 전환을 할 수 있다.
* JPA를 사용하면 개발 생산성을 크게 높일 수 있다.
<br><br>

**스프링 부트에 JPA 설정 추가**
* 관련 라이브러리는 implementation 'org.springframework.boot:spring-boot-starter-data-jpa'를 추가해준다.
* 이후 application.properties에서 아래 2개의 설정을 추가한다.   
  `spring.jpa.show-sql=true` : JPA가 생성하는 SQL을 출력하게 함   
  `spring.jpa.hibernate.ddl-auto=none` : JPA는 테이블을 자동으로 생성하는 기능을 제공한다. none은 해당기능 끔   
  create를 사용하면 엔티티정보를 바탕으로 테이블도 직접 생성해줌
<br><br>

**JPA 사용하기 : Entity mapping**   
* 먼저 Member 엔티티에 매핑을 해줘야 한다. (ORM) : `@Entity`
  * Member의 id는 pk이고, pk를 db에서 값을 생성하는 것을 `아이덴티티 전략`이라 한다.
    * `@Id` `@GeneratedValue(strategy = GenerationType.IDENTITY)`

<br><br>
**JPA 사용하기 : 리포지토리**   
* JPA는 `EntityManager`라는 것으로 모든것이 동작한다.
* data-jpa라이브러리를 받으면(build.gradle) 스프링 부트가 자동으로 EntityManager를 생성해준다.
* 만들면서 현재 데이터베이스와 연결도 하고 앞서 application.properties에서 설정한 정보까지 이용해 EntityManager가 생성된다.
* 또한 DataSource같은 부분도 `내부적`으로 들고있으므로 DB통신 부분은 내부적으로 처리해준다.
* 따라서 만든것을 Injection받으면 된다.
* 기본적으로 저장 조회 업데이트 삭제 같이 pk 기반의 것들은 sql을 작성할 필요없음 : **자동생성됨**
* pk기반이 아닌 findByName, findAll같은 경우는 `JPQL`을 작성해줘야한다.

<br><br>
**JPA 사용하기 : 서비스 계층**   
* JPA를 쓰려면 항상 주의해야 할 것이 서비스 계층에 `@Transactional`이 필요하다.
  * `org.springframework.transaction.annotation.Transactional` 를 사용하자.
* 서비스 계층 전부 필요하진 않고, 데이터를 저장, 변경하는 부분에만 적용해도 된다.
* 예를들어 join을 보면 JPA는 join시 모든 데이터 변경이 트랜잭션 안에서 실행되야 하므로 어노테이션을 붙여준다.
* 스프링은 해당 클래스의 메서드를 실행할 때 트랜잭션을 시작하고, 메서드가 정상 종료되면 트랜잭션을 커밋한다.   
  만약 런타임 예외가 발생하면 롤백한다.
* **JPA를 통한 모든 데이터 변경은 트랜잭션 안에서 실행해야 한다.(추가, 변경 등)**

<br><br>
> JPA기술을 스프링에서 한번 감싸서 제공하는 기술이 있다. : 스프링 데이터 JPA    
> 이것을 이용하면 기존에 JPQL을 짠 findByName, findAll도 JPQL을 작성하지 않아도된다.   





















