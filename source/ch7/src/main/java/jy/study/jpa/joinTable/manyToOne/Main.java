package jy.study.jpa.joinTable.manyToOne;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("joinTable-manyToOne");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        insert(em);
        tx.commit();

        emf.close();
    }

    static void insert(EntityManager em) {
        Parent parent = new Parent();
        parent.setName("부모");
        em.persist(parent);

        Child child1 = new Child();
        child1.setName("자식1");
        child1.setParent(parent);
        em.persist(child1);

        Child child2 = new Child();
        child2.setName("자식2");
        child2.setParent(parent);
        em.persist(child2);
    }
}
