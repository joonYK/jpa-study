package jy.study.jpa.manyToMany.twoWay.junctionEntity.newKey;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("manyToMany-twoWay-junctionEntity-newKey");
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
        Member member = new Member();
        member.setId("member");
        member.setUsername("회원");
        em.persist(member);

        Product product = new Product();
        product.setId("product");
        product.setName("상품");
        em.persist(product);

        ProductOrder order = new ProductOrder();
        order.setMember(member);
        order.setProduct(product);
        order.setOrderAmount(15);
        em.persist(order);
    }

    static void find(EntityManager em) {
        ProductOrder order = em.find(ProductOrder.class, 1L);

        Member member = order.getMember();
        Product product = order.getProduct();

        System.out.println(member.getUsername());
        System.out.println(product.getName());
        System.out.println(order.getOrderAmount());

    }
}
