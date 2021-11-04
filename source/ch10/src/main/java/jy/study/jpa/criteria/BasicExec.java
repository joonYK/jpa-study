package jy.study.jpa.criteria;

import jy.study.jpa.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class BasicExec {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch10");
        EntityManager em = emf.createEntityManager();

        System.out.println("간단 쿼리");
        start(em);
        System.out.println();

        System.out.println("검색 조건 추가");
        addSearchConditions(em);
        System.out.println();

        System.out.println("숫자 타입 검색");
        numberTypeSearch(em);
        System.out.println();
    }

    private static void start(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Member> cq = cb.createQuery(Member.class);

        Root<Member> m = cq.from(Member.class);
        cq.select(m);

        TypedQuery<Member> query = em.createQuery(cq);
        List<Member> members = query.getResultList();

        System.out.println(members);
    }

    private static void addSearchConditions(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Member> cq = cb.createQuery(Member.class);

        Root<Member> m = cq.from(Member.class);

        Predicate usernameEqual = cb.equal(m.get("username"), "유저1");
        javax.persistence.criteria.Order ageDesc = cb.desc(m.get("age"));

        cq.select(m)
                .where(usernameEqual)
                .orderBy(ageDesc);

        List<Member> members = em.createQuery(cq).getResultList();

        System.out.println(members);
    }

    private static void numberTypeSearch(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Member> cq = cb.createQuery(Member.class);

        Root<Member> m = cq.from(Member.class);

        Predicate ageGt = cb.greaterThan(m.<Integer>get("age"), 15);

        cq.select(m)
                .where(ageGt)
                .orderBy(cb.desc(m.get("age")));


        List<Member> members = em.createQuery(cq).getResultList();

        System.out.println(members);
    }
}
