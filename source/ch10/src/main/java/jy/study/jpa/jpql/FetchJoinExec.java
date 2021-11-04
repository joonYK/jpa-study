package jy.study.jpa.jpql;

import jy.study.jpa.domain.Member;
import jy.study.jpa.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class FetchJoinExec {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch10");
        EntityManager em = emf.createEntityManager();

        System.out.println("내부 조인");
        fetchJoin(em);
        System.out.println();

        System.out.println("컬렉션 페치 조인");
        collectionFetchJoin(em);
        System.out.println();

        System.out.println("페치 조인과 DISTINCT");
        distinct(em);
        System.out.println();
    }


    private static void fetchJoin(EntityManager em) {
        String query = "SELECT m FROM Member m join fetch m.team";
        List<Member> members = em.createQuery(query, Member.class).getResultList();

        for (Member member : members) {
            System.out.println("username = " + member.getUsername() + ", " + "teamname = " + member.getTeam().getName());
        }
    }

    private static void collectionFetchJoin(EntityManager em) {
        String query = "SELECT t FROM Team t JOIN FETCH t.members WHERE t.name = 'team1'";
        List<Team> teams = em.createQuery(query, Team.class).getResultList();

        for (Team team : teams) {
            System.out.println("teamname = " + team.getName() + ", team = " + team);

            for (Member member : team.getMembers()) {
                System.out.println("->username = " + member.getUsername() +", member = " + member);
            }
        }
    }

    private static void distinct(EntityManager em) {
        String query = "SELECT DISTINCT t FROM Team t JOIN FETCH t.members";
        List<Team> teams = em.createQuery(query, Team.class).getResultList();

        for (Team team : teams) {
            System.out.println("teamname = " + team.getName() + ", team = " + team);

            for (Member member : team.getMembers()) {
                System.out.println("->username = " + member.getUsername() +", member = " + member);
            }
        }
    }
}
