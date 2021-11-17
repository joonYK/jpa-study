package jy.study.jpa.querydsl;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import jy.study.jpa.domain.Product;
import jy.study.jpa.domain.QProduct;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Map;

public class GroupExec {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch10");
        EntityManager em = emf.createEntityManager();

        groupBy(em);
    }

    private static void groupBy(EntityManager em) {
        JPAQuery<Product> query = new JPAQuery<>(em);
        QProduct qProduct = QProduct.product;

        List<Tuple> list = query.from(qProduct)
                .select(qProduct.price, qProduct.stockAmount.count())
                .groupBy(qProduct.price)
                .having(qProduct.price.gt(1000))
                .fetch();

        System.out.println(list);
    }
}
