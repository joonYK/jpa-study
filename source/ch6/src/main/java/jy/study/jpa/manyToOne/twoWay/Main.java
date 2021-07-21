package jy.study.jpa.manyToOne.twoWay;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manyToOne");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            insert(em);
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    static void insert(EntityManager em) {
        Team2 team = new Team2();
        team.setName("팀");
        em.persist(team);

        Member2 member = new Member2();
        member.setUsername("회원");
        member.setTeam(team);
        em.persist(member);
    }
}
