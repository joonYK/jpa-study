package jy.study.jpa.querydsl;

import com.querydsl.core.QueryModifiers;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import jy.study.jpa.domain.Product;
import jy.study.jpa.domain.QProduct;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class PagingAndOrder {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch10");
        EntityManager em = emf.createEntityManager();

        System.out.println("페이징과 정렬");
        pagingAndOrder(em);
        System.out.println();

        System.out.println("페이징과 정렬 QueryModifiers 사용");
        queryModifiers(em);
        System.out.println();

        System.out.println("페이징과 정렬 fetchResults() 사용");
        fetchResults(em);
    }

    private static void pagingAndOrder(EntityManager em) {
        JPAQuery<Product> query = new JPAQuery<>(em);
        QProduct product = QProduct.product;

        List<Product> list = query.from(product)
                .where(product.price.gt(2000))
                .orderBy(product.price.desc(), product.stockAmount.asc())
                .offset(10).limit(20)
                .fetch();

        for (Product product1 : list) {
            System.out.println(product1);
        }
    }

    private static void queryModifiers(EntityManager em) {
        QueryModifiers queryModifiers = new QueryModifiers(20L, 10L); //limit, offset

        JPAQuery<Product> query = new JPAQuery<>(em);
        QProduct qProduct = QProduct.product;

        List<Product> list = query.from(qProduct)
                .restrict(queryModifiers)
                .fetch();

        for (Product product : list) {
            System.out.println(product);
        }
    }

    private static void fetchResults(EntityManager em) {
        JPAQuery<Product> query = new JPAQuery<>(em);
        QProduct qProduct = QProduct.product;

        QueryResults<Product> results = query.from(qProduct)
                .where(qProduct.price.gt(2000))
                .offset(10).limit(20)
                .fetchResults();

        long total = results.getTotal(); //검색된 전체 데이터 수
        long limit = results.getLimit();
        long offset = results.getOffset();
        List<Product> list = results.getResults(); //조회된 데이터

        System.out.println("total = " + total);
        System.out.println("limit = " + limit);
        System.out.println("offset = " + offset);
        System.out.println("조회된 list ▼");
        for (Product product : list) {
            System.out.println(product);
        }
    }
}
