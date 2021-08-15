package jy.study.jpa.joinTable.manyToMany;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("joinTable-manyToMany");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        insert(em);
        tx.commit();

        emf.close();
    }

    static void insert(EntityManager em) {
        Child child1 = new Child();
        child1.setName("자식1");
        em.persist(child1);

        Child child2 = new Child();
        child2.setName("자식2");
        em.persist(child2);

        Parent parent = new Parent();
        parent.setName("부모");
        parent.addChild(child1);
        parent.addChild(child2);
        em.persist(parent);
    }
}
