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

import static jy.study.jpa.querydsl.entity.QMember.member;
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

    @Test
    public void querydslPredicateExecutorTest() {
        /*
         * 클라이언트가 Querydsl 구현 기술에 의존하게 되는 단점이 존재.
         * repository 계층 밖에서 predicate 를 생성하고 넘겨줘야 함.
         */
        Iterable<Member> result = memberRepository.findAll(
                member.age.between(10, 40)
                        .and(member.username.eq("member1"))
        );
        for (Member member1 : result) {
            System.out.println("member1 = " + member1);
        }

    }

    @Test
    public void search2Test() {
        MemberSearchCondition condition = new MemberSearchCondition();
        condition.setTeamName("teamA");

        List<MemberTeamDto> result = memberRepository.search(condition);

        assertThat(result).extracting("username").containsExactly("member1", "member2");
    }

    @Test
    public void searchPage2Test() {
        MemberSearchCondition condition = new MemberSearchCondition();
        PageRequest pageRequest = PageRequest.of(0, 3);

        Page<MemberTeamDto> result = memberRepository.searchPage(condition, pageRequest);

        assertThat(result.getSize()).isEqualTo(3);
        assertThat(result.getContent()).extracting("username").containsExactly("member1", "member2", "member3");
        assertThat(result.getTotalElements()).isEqualTo(4);
    }
}
