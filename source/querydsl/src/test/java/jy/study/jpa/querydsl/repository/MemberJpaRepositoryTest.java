package jy.study.jpa.querydsl.repository;

import jy.study.jpa.querydsl.BaseQuerydslTest;
import jy.study.jpa.querydsl.dto.MemberSearchCondition;
import jy.study.jpa.querydsl.dto.MemberTeamDto;
import jy.study.jpa.querydsl.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemberJpaRepositoryTest extends BaseQuerydslTest {

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    public void basicTest() {
        Member member = new Member("member5", 50);
        memberJpaRepository.save(member);

        Member findMember = memberJpaRepository.findById(member.getId()).get();
        assertThat(findMember).isEqualTo(member);

//        List<Member> findAllMembers = memberJpaRepository.findAll();
        List<Member> findAllMembers = memberJpaRepository.findAll_Querydsl();
        assertThat(findAllMembers).contains(member);

//        List<Member> findByUsernameMember = memberJpaRepository.findByUsername("member5");
        List<Member> findByUsernameMember = memberJpaRepository.findByUsername_Querydsl("member5");
        assertThat(findByUsernameMember).contains(member);
    }

    @Test
    public void searchTest_BooleanBuilder() {
        MemberSearchCondition condition = new MemberSearchCondition();
        condition.setAgeGoe(35);
        condition.setAgeLoe(40);
        condition.setTeamName("teamB");

        List<MemberTeamDto> result = memberJpaRepository.searchByBuilder(condition);

        assertThat(result).extracting("username").containsExactly("member4");
    }

    @Test
    public void searchTest_whereParam() {
        MemberSearchCondition condition = new MemberSearchCondition();
        condition.setAgeGoe(35);
        condition.setAgeLoe(40);
        condition.setTeamName("teamB");

        List<MemberTeamDto> result = memberJpaRepository.search(condition);

        assertThat(result).extracting("username").containsExactly("member4");
    }

}