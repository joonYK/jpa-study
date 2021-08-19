package jy.study.jpa.eagerLoading;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("eagerLoading");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            insert(em);
            tx.commit();

            em.clear();

            tx.begin();
            find(em);
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    static void insert(EntityManager em) {
        Team team = new Team();
        team.setName("팀");
        em.persist(team);

        Member member = new Member();
        member.setId("member");
        member.setName("회원");
        member.setTeam(team);
        em.persist(member);
    }

    static void find(EntityManager em) {
        Member member = em.find(Member.class, "member");
        System.out.println(member.getTeam().getName());
    }
}
