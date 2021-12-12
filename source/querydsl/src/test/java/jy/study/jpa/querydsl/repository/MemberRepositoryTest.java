package jy.study.jpa.querydsl.repository;

import jy.study.jpa.querydsl.BaseQuerydslTest;
import jy.study.jpa.querydsl.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberRepositoryTest extends BaseQuerydslTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void basicTest() {
        Member member = new Member("member5", 50);
        memberRepository.save(member);

        Member findMember = memberRepository.findById(member.getId()).get();
        assertThat(findMember).isEqualTo(member);

        List<Member> findAllMembers = memberRepository.findAll();
        assertThat(findAllMembers).contains(member);

        List<Member> findByUsernameMember = memberRepository.findByUsername("member5");
        assertThat(findByUsernameMember).contains(member);
    }
}
