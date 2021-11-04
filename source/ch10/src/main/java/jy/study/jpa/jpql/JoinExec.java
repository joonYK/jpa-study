package jy.study.jpa.jpql;

import jy.study.jpa.domain.Member;
import jy.study.jpa.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class JoinExec {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch10");
        EntityManager em = emf.createEntityManager();

        System.out.println("내부 조인");
        innerJoin(em);
        System.out.println();

        System.out.println("내부 조인 - 엔티티 두 개 조회");
        innerJoinTwoEntitySelect(em);
        System.out.println();

        System.out.println("외부 조인");
        leftOuterJoin(em);
        System.out.println();

        System.out.println("컬렉션 조인");
        collectionJoin(em);
        System.out.println();

        System.out.println("JOIN ON");
        joinOn(em);
        System.out.println();
    }


    private static void innerJoin(EntityManager em) {
        String teamName = "team1";
        String query = "SELECT m FROM Member m" +
                " INNER JOIN m.team t WHERE t.name = :teamName";

        List<Member> members = em.createQuery(query, Member.class)
                .setParameter("teamName", teamName)
                .getResultList();

        for (Member member : members) {
            System.out.println("username = " + member.getUsername() + ", " + "teamname = " + member.getTeam().getName());
        }
    }

    private static void innerJoinTwoEntitySelect(EntityManager em) {
        String query = "SELECT m, t FROM Member m JOIN m.team t";

        List<Object[]> result = em.createQuery(query).getResultList();

        for (Object[] row : result) {
            Member member = (Member) row[0];
            Team team = (Team) row[1];

            System.out.println(member + ", team_id="+member.getTeam().getId());
            System.out.println(team);
        }
    }

    private static void leftOuterJoin(EntityManager em) {
        String query = "SELECT m FROM Member m LEFT OUTER JOIN m.team t";
        List<Member> members = em.createQuery(query, Member.class).getResultList();

        for (Member member : members) {
            System.out.println(member);
        }
    }

    private static void collectionJoin(EntityManager em) {
        String query = "SELECT t, m FROM Team t LEFT JOIN t.members m";
        List<Object[]> result = em.createQuery(query).getResultList();

        for (Object[] row : result) {
            Team team = (Team) row[0];
            Member member = (Member) row[1];

            System.out.println(team);
            System.out.println(member);
        }
    }

    private static void joinOn(EntityManager em) {
        String query = "SELECT m,t FROM Member m LEFT JOIN m.team t on t.name = 'team1'";
        List<Object[]> result = em.createQuery(query).getResultList();

        for (Object[] row : result) {
            Member member = (Member) row[0];
            Team team = (Team) row[1];

            System.out.println(member);
            if (member.getTeam() != null)
                System.out.println(team);
        }
    }

}
