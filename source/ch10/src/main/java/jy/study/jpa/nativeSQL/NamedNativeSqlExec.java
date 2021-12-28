package jy.study.jpa.nativeSQL;

import jy.study.jpa.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.math.BigInteger;
import java.util.List;

public class NamedNativeSqlExec {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch10");
        EntityManager em = emf.createEntityManager();

        getEntity(em);
        useResultMapping(em);

    }

    private static void getEntity(EntityManager em) {
        TypedQuery<Member> nativeQuery = em.createNamedQuery("Member.memberSQL", Member.class)
                .setParameter(1, 10);

        List<Member> resultList = nativeQuery.getResultList();

        for (Member member : resultList) {
            System.out.println("member = " + member);
        }
    }

    private static void useResultMapping(EntityManager em) {
        List<Object[]> resultList = em.createNamedQuery("Member.memberWithOrderCount")
                .getResultList();

        for (Object[] row : resultList) {
            Member member = (Member) row[0];
            BigInteger orderCount = (BigInteger) row[1];

            System.out.println("member = " + member);
            System.out.println("orderCount = " + orderCount);
        }
    }
}
