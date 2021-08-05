package jy.study.jpa.noIdentification;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("no-identification");
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

        Child child = new Child();
        child.setName("자식");
        child.setParent(parent);
        em.persist(child);

        GrandChild grandChild = new GrandChild();
        grandChild.setName("손자");
        grandChild.setChild(child);
        em.persist(grandChild);
    }
}
