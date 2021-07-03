package jy.study.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMainCh4 {
    public static void main(String[] args) {
        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-study");

        //엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        //트랜잭션 획득
        EntityTransaction tx = em.getTransaction();

        try {
            //트랜잭션 시작
            tx.begin();

            //트랜잭션 커밋
            tx.commit();
        } catch (Exception e) {
            //트랜잭션 롤백
            tx.rollback();
        } finally {
            //엔티티 매니저 종료
            em.close();
        }
        //엔티티 매니저 팩토리 종료
        emf.close();
    }
}
