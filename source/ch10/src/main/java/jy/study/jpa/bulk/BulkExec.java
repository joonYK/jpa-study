package jy.study.jpa.bulk;

import jy.study.jpa.domain.Order;
import jy.study.jpa.domain.Product;

import javax.persistence.*;
import java.util.List;

public class BulkExec {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch10");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        updateBulk(em);
        tx.commit();

        tx.begin();
        deleteBulk(em);
        tx.commit();
    }

    private static void updateBulk(EntityManager em) {
        List<Product> resultList = em.createQuery("select p from Product p", Product.class).getResultList();
        for (Product product : resultList)
            System.out.println("product = " + product);

        String qlString =
                "update Product p " +
                "set p.price = p.price * 1.1 " +
                "where p.stockAmount < :stockAmount";

        int resultCount = em.createQuery(qlString)
                .setParameter("stockAmount", 10)
                .executeUpdate();

        System.out.println("updateCount = " + resultCount);

        em.clear();

        resultList = em.createQuery("select p from Product p", Product.class).getResultList();
        for (Product product : resultList)
            System.out.println("product = " + product);
    }

    private static void deleteBulk(EntityManager em) {
        List<Order> resultList = em.createQuery("select o from jy.study.jpa.domain.Order o", Order.class).getResultList();
        for (Order order : resultList)
            System.out.println("order = " + order);

        String qlString =
                "delete from jy.study.jpa.domain.Order o " +
                "where o.member.id = :memberId";

        int resultCount = em.createQuery(qlString)
                .setParameter("memberId", "member1")
                .executeUpdate();

        System.out.println("deleteCount = " + resultCount);

        em.clear();

        resultList = em.createQuery("select o from jy.study.jpa.domain.Order o", Order.class).getResultList();
        for (Order order : resultList)
            System.out.println("order = " + order);
    }
}
