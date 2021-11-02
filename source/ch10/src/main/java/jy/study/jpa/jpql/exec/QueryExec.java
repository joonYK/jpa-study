package jy.study.jpa.jpql.exec;

import jy.study.jpa.jpql.domain.Member;

import javax.persistence.*;
import java.util.List;

public class QueryExec {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpql");
        EntityManager em = emf.createEntityManager();

        typedQuery(em);
        query(em);
    }

    private static void typedQuery(EntityManager em) {
        TypedQuery<Member> query = em.createQuery("SELECT m FROM Member m", Member.class);

        List<Member> resultList = query.getResultList();
        for (Member member : resultList) {
            System.out.println("member = " + member);
        }
    }

    private static void query(EntityManager em) {
        javax.persistence.Query query = em.createQuery("SELECT m.username, m.age FROM Member m");
        List resultList = query.getResultList();

        for (Object o : resultList) {
            Object[] result = (Object[]) o;
            System.out.println("username = " + result[0]);
            System.out.println("age = " + result[1]);
        }
    }

}
