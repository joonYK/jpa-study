package jy.study.jpa.jpql;

import jy.study.jpa.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class ParamBindingExec {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch10");
        EntityManager em = emf.createEntityManager();

        System.out.println("이름 기준 파라미터");
        name(em);

        System.out.println("위치 기준 파라미터");
        position(em);
    }

    //이름 기준 파라미터
    private static void name(EntityManager em) {
        TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m where m.username LIKE :username||'%'", Member.class);
        query.setParameter("username", "유저");
        
        List<Member> resultList = query.getResultList();
        for (Member member : resultList)
            System.out.println("member = " + member);

        System.out.println();
        System.out.println();
        
        //메소드 체인 방식
        List<Member> members = em.createQuery("SELECT m FROM Member m where m.username = :username", Member.class)
                .setParameter("username", "유저1")
                .getResultList();

        for (Member member : members)
            System.out.println("member = " + member);
    }

    //위치 기준 파라미터
    private static void position(EntityManager em) {
        TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m where m.username LIKE ?1||'%'", Member.class);
        query.setParameter(1, "유저");

        List<Member> resultList = query.getResultList();
        for (Member member : resultList)
            System.out.println("member = " + member);
    }
}
