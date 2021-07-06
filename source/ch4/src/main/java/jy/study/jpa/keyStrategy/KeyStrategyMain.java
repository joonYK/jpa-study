package jy.study.jpa.keyStrategy;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class KeyStrategyMain {

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

            logic(em);

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

    private static void logic(EntityManager em) {
        Identity identity = new Identity();
        em.persist(identity);
        System.out.println("identity.id = " + identity.getId());

        Sequence sequence = new Sequence();
        em.persist(sequence);
        System.out.println("sequence.id = " + sequence.getId());

        TableSt table = new TableSt();
        em.persist(table);
        System.out.println("table.id = " + table.getId());
    }
}
