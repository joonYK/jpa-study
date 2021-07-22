package jy.study.jpa.oneToOne.twoWay;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("oneToOne-twoWay");
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
        Locker locker = new Locker();
        locker.setName("사물함");
        em.persist(locker);

        Member member = new Member();
        member.setUsername("회원");
        member.setLocker(locker);
        em.persist(member);
    }
}
