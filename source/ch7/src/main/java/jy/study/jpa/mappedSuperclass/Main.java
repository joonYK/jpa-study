package jy.study.jpa.mappedSuperclass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mappedSuperclass");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        insert(em);
        tx.commit();

        emf.close();
    }

    static void insert(EntityManager em) {
        Member member = new Member();
        member.setName("회원");
        member.setEmail("tset@test.com");
        em.persist(member);

        Seller seller = new Seller();
        seller.setName("판매자");
        seller.setShopName("쇼핑몰");
        em.persist(seller);
    }
}
