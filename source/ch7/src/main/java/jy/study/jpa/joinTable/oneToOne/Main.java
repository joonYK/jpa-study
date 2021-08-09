package jy.study.jpa.joinTable.oneToOne;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("joinTable-oneToOne");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        insert(em);
        tx.commit();

        emf.close();
    }

    static void insert(EntityManager em) {
        Child child = new Child();
        child.setName("자식");
        em.persist(child);

        Parent parent = new Parent();
        parent.setName("부모");
        parent.setChild(child);
        em.persist(parent);

    }
}
