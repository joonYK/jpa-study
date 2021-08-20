package jy.study.jpa.collectionWrapper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("collectionWrapper");
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
        Member member = new Member();
        member.setId("member");
        em.persist(member);

        Product product = new Product();
        em.persist(product);

        Order order1 = new Order();
        order1.setMember(member);
        order1.setProduct(product);
        em.persist(order1);

        Order order2 = new Order();
        order2.setMember(member);
        order2.setProduct(product);
        em.persist(order2);
    }

    static void find(EntityManager em) {
        Member member = em.find(Member.class, "member");
        System.out.println(member.getOrders().getClass().getName()); //org.hibernate.collection.internal.PersistentBag
        System.out.println(member.getOrders().get(0).getId());
    }
}
