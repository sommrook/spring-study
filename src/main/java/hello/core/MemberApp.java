package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
        // type은 MemberService 클래스인데, 기능 구현은 MemberServiceImpl을 따라감
        // MemberServiceImpl에서는 Repository를 참조하는데, type은 MemberRepository이지만 기능 구현은 MemmoryMemberRepository에 되어있음
        // 즉, type으로 설정된 MemberService와 MemberRepository는 인터페이스임
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // name에 method이름을 넣고, 두번째 파라미터에는 타입을 넣어줌
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }

}
