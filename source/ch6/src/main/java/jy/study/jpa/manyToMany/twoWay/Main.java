package jy.study.jpa.manyToMany.twoWay;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manyToMany-twoWay");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        insert(em);
        tx.commit();
        em.clear();

        find(em);

        emf.close();
    }

    static void insert(EntityManager em) {
        Product product = new Product();
        product.setId("product1");
        product.setName("상품");
        em.persist(product);

        Member member = new Member();
        member.setId("member1");
        member.setUsername("회원");
        member.getProducts().add(product);
        em.persist(member);
    }

    static void find(EntityManager em) {
        Product product = em.find(Product.class, "product1");
        List<Member> members = product.getMembers();
        for (Member member : members) {
            System.out.println(member.getUsername());
        }
    }
}
