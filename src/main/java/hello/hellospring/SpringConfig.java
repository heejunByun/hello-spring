package hello.hellospring;

import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;
    private EntityManager em;

//    @Autowired
//    public SpringConfig(DataSource dataSource, EntityManager em) {
//        this.dataSource = dataSource;
//        this.em = em;
//    }

    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository()); //@Autowired
    }

    //객체지향적인 설계 , 다양성의 편리함 (JDBC를 Serivce단 변경 없이 Conifg만으로 변경할 수 있다.)

    //MemoryRepository
/*
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
s    }
*/

    //JdbcRepository
/*

    @Bean
    public MemberRepository memberRepository() {
        return new JdbcMemberRepository(dataSource);
    }
*/

    //JdbcTemplateMemberRepository
/*
    @Bean
    public MemberRepository memberRepository() {
        return new JdbcTemplateMemberRepository(dataSource);
    }
*/

    //JPA
    @Bean
    public MemberRepository memberRepository() {
        return new JpaMemberRepository(em);
    }


    //Spring  JPA
/*
    private final MemberRepository memberRepository;
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }
*/

}
