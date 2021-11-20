package jy.study.jpa.querydsl;

import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAUpdateClause;
import jy.study.jpa.domain.QMember;
import jy.study.jpa.domain.QProduct;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class BatchQueryExec {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ch10");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        System.out.println("수정 배치 쿼리");
        updateBatchQuery(em);
        System.out.println();

        tx.commit();

        tx.begin();

        System.out.println("삭제 배치 쿼리");
        deleteBatchQuery(em);
        System.out.println();

        tx.commit();
    }

    private static void updateBatchQuery(EntityManager em) {
        QMember member = QMember.member;
        JPAUpdateClause updateClause = new JPAUpdateClause(em, member);
        long count = updateClause.where(member.id.eq("member1"))
                .set(member.username, "유저1 - 수정")
                .execute();

        System.out.println(count);
    }

    private static void deleteBatchQuery(EntityManager em) {
        QProduct product = QProduct.product;
        JPADeleteClause deleteClause = new JPADeleteClause(em, product);
        long count = deleteClause.where(product.name.eq("상품20")).execute();

        System.out.println(count);
    }
}
