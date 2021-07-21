package jy.study.jpa.oneToMany.oneWay;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("oneToMany-oneWay");
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
        Member member1 = new Member();
        member1.setUsername("회원1");
        Member member2 = new Member();
        member2.setUsername("회원2");

        Team team = new Team();
        team.setName("팀");
        team.getMembers().add(member1);
        team.getMembers().add(member2);

        em.persist(member1);
        em.persist(member2);
        em.persist(team);
    }
}
