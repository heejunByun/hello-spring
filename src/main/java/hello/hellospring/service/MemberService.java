package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Transactional //JPA사용 시 Service단에 필수 어노테이션
public class MemberService { //Ctrl + Shift + T : Test Package 자동생성

    private final MemberRepository memberRepository;

    //Constructor
    //외부에서 MemberService에 선언된 MemberRepository 인스턴스를 사용할 수 있도록 생성자 생성
    public MemberService(MemberRepository memberRepository) { //외부에서 memberRepository를 주입
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        //중복 방지
        validateDuplicateMember(member); //중복회훤 검증
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * findById
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //함수생성단축키 : Shift+Ctrl+Alt+T - Refactor This
    private void validateDuplicateMember(Member member) {
        /**
         *        Optional<Member> result = memberRepository.findByName(member.getName()); //Ctrl + Alt + v
         *         result.ifPresent(m -> { //ifPresent : 값이 있으면 진행
         *             throw new IllegalStateException("이미 존재하는 회원입니다.");
         *         });
         */
        memberRepository.findByName(member.getName()).ifPresent(m -> { //ifPresent : 값이 있으면 진행
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }
}
