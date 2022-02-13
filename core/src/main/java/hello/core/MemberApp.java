package hello.core;

import hello.core.member.entity.Grade;
import hello.core.member.entity.Member;
import hello.core.member.service.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
        // AppConfig를 이용해 어떤 저장소를 사용할지 결정된 서비스객체를 주입받음
//        MemberService memberService = appConfig.memberService();

        // AppConfig 버전에서 Spring을 사용하는 버전으로 변경하기
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
