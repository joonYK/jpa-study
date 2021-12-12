package jy.study.jpa.querydsl.repository;

import jy.study.jpa.querydsl.BaseQuerydslTest;
import jy.study.jpa.querydsl.dto.MemberSearchCondition;
import jy.study.jpa.querydsl.dto.MemberTeamDto;
import jy.study.jpa.querydsl.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

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

    @Test
    public void searchTest_whereParam() {
        MemberSearchCondition condition = new MemberSearchCondition();
        condition.setAgeGoe(35);
        condition.setAgeLoe(40);
        condition.setTeamName("teamB");

        List<MemberTeamDto> result = memberRepository.search(condition);

        assertThat(result).extracting("username").containsExactly("member4");
    }

    @Test
    public void searchPageTest() {
        MemberSearchCondition condition = new MemberSearchCondition();
        PageRequest pageRequest = PageRequest.of(0, 3);

        Page<MemberTeamDto> result = memberRepository.searchPage(condition, pageRequest);

        assertThat(result.getSize()).isEqualTo(3);
        assertThat(result.getContent()).extracting("username").containsExactly("member1", "member2", "member3");
        assertThat(result.getTotalElements()).isEqualTo(4);
    }


}
