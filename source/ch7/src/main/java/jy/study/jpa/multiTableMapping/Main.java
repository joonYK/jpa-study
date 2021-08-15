package jy.study.jpa.multiTableMapping;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("multiTableMapping");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        insert(em);
        tx.commit();

        emf.close();
    }

    static void insert(EntityManager em) {
        Board board = new Board();
        board.setTitle("게시판");
        board.setContent("게시판 내용");
        em.persist(board);
    }
}
