package jy.study.jpa.querydsl.repository;

import jy.study.jpa.querydsl.dto.MemberSearchCondition;
import jy.study.jpa.querydsl.dto.MemberTeamDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberRepositoryCustom {
    List<MemberTeamDto> search(MemberSearchCondition condition);
    List<MemberTeamDto> search2(MemberSearchCondition condition);
    Page<MemberTeamDto> searchPage(MemberSearchCondition condition, Pageable pageable);
    Page<MemberTeamDto> searchPage2(MemberSearchCondition condition, Pageable pageable);
}
