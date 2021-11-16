package jy.study.jpa.querydsl;

import com.querydsl.jpa.impl.JPAQuery;
import jy.study.jpa.domain.Product;
import jy.study.jpa.domain.QProduct;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class SearchTermsQueryExec {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch10");
        EntityManager em = emf.createEntityManager();

        System.out.println("기본 쿼리 기능");
        basicQuery(em);
    }

    private static void basicQuery(EntityManager em) {
        JPAQuery<Product> query = new JPAQuery<>(em);
        QProduct product = QProduct.product;
        List<Product> list = query.from(product)
                .where(product.name.eq("상품10").and(product.price.gt(20000)))
                .fetch();

        System.out.println(list);
    }
}
