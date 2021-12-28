package jy.study.jpa.nativeSQL;

import jy.study.jpa.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

public class NativeSqlExec {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch10");
        EntityManager em = emf.createEntityManager();

        getEntity(em);
        getValues(em);
        useResultMapping(em);
    }

    //엔티티 조회
    private static void getEntity(EntityManager em) {
        String sql =
                "SELECT ID, AGE, USERNAME, TEAM_ID " +
                "FROM MEMBER WHERE AGE > ?";

        Query query = em.createNativeQuery(sql, Member.class)
                .setParameter(1, 10);

        List<Member> resultList = query.getResultList();

        for (Member member : resultList) {
            System.out.println("member = " + member);
        }
    }

    //스칼라 값들 조회
    private static void getValues(EntityManager em) {
        String sql =
                "SELECT ID, AGE, USERNAME, TEAM_ID " +
                "FROM MEMBER WHERE AGE > ?";

        Query nativeQuery = em.createNativeQuery(sql)
                .setParameter(1, 15);

        List<Object[]> resultList = nativeQuery.getResultList();
        for (Object[] row : resultList) {
            System.out.println("id = " + row[0]);
            System.out.println("age = " + row[1]);
            System.out.println("name = " + row[2]);
            System.out.println("team_id = " + row[3]);
        }
    }

    private static void useResultMapping(EntityManager em) {
        String sql =
                "SELECT M.ID, AGE, USERNAME, TEAM_ID, I.ORDER_COUNT " +
                "FROM MEMBER M " +
                "LEFT JOIN " +
                "   (SELECT IM.ID, COUNT(*) AS ORDER_COUNT " +
                "   FROM ORDERS O, MEMBER IM " +
                "   WHERE O.MEMBER_ID = IM.ID" +
                "   GROUP BY IM.ID) I " +
                "ON M.ID = I.ID";

        Query nativeQuery = em.createNativeQuery(sql, "memberWithOrderCount");

        List<Object[]> resultList = nativeQuery.getResultList();
        for (Object[] row : resultList) {
            Member member = (Member) row[0];
            BigInteger orderCount = (BigInteger) row[1];

            System.out.println("member = " + member);
            System.out.println("orderCount = " + orderCount);
        }
    }



}
