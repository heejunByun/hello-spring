package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //@Test는 순서 상관없이 동적으로 실행된다. @AfterEach는 @Test가 끝날 때마다 한번씩 돈다
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("SpringBoot");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
        //Assertions.assertEquals(member, result);
        //System.out.println("(result == member) = " + (result == member));
    }

    @Test
    public void findById() {

        Member member1 = new Member();
        member1.setName("Spring01");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring02");
        repository.save(member2);

        Member result = repository.findByName("Spring01").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {

        Member member1 = new Member();
        member1.setName("Spring01");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring02");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
