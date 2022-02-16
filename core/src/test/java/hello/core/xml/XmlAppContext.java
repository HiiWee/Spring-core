package hello.core.xml;

import hello.core.member.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.assertj.core.api.Assertions.*;

class XmlAppContext {

    @Test
    void xmlAppContext() {
        // appConfig.xml을 classPath에 있는 resources/를 자동으로 읽어와서 bean을 등록한다.
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
