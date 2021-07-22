package jy.study.jpa.manyToMany.oneWay;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manyToMany-oneWay");
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
        product.setName("상품");
        em.persist(product);

        Member member = new Member();
        member.setId("member");
        member.setUsername("회원");
        member.getProducts().add(product);
        em.persist(member);
    }

    static void find(EntityManager em) {
        Member member = em.find(Member.class, "member");
        List<Product> products = member.getProducts();
        for (Product product : products) {
            System.out.println(product.getName());
        }
    }
}
