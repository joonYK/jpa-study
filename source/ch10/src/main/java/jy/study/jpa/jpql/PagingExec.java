package jy.study.jpa.jpql;

import jy.study.jpa.domain.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class PagingExec {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch10");
        EntityManager em = emf.createEntityManager();

        paging(em);

    }

    private static void paging(EntityManager em) {
        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p ORDER BY p.name DESC", Product.class);
        query.setFirstResult(0);
        query.setMaxResults(10);
        List<Product> resultList = query.getResultList();

        for (Product product : resultList) {
            System.out.println(product);
        }
    }
}
