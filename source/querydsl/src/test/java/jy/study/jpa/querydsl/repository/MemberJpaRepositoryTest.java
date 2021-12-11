package jy.study.jpa.querydsl.repository;

import jy.study.jpa.querydsl.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    public void basicTest() {
        Member member = new Member("member1", 10);
        memberJpaRepository.save(member);

        Member findMember = memberJpaRepository.findById(member.getId()).get();
        assertThat(findMember).isEqualTo(member);

//        List<Member> findAllMembers = memberJpaRepository.findAll();
        List<Member> findAllMembers = memberJpaRepository.findAll_Querydsl();
        assertThat(findAllMembers).containsExactly(member);

//        List<Member> findByUsernameMember = memberJpaRepository.findByUsername("member1");
        List<Member> findByUsernameMember = memberJpaRepository.findByUsername_Querydsl("member1");
        assertThat(findByUsernameMember).containsExactly(member);
    }

}