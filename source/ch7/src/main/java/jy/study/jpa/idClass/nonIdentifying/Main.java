package jy.study.jpa.idClass.nonIdentifying;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("idClass-nonIdentifying");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        insert(em);
        tx.commit();
        em.clear();

        tx.begin();
        find(em);
        tx.commit();

        emf.close();
    }

    static void insert(EntityManager em) {
        Parent parent = new Parent();
        parent.setId1("pid1");
        parent.setId2("pid2");
        parent.setName("name");
        em.persist(parent);

        Child child = new Child();
        child.setId("cid");
        child.setParent(parent);
        em.persist(child);
    }

    static void find(EntityManager em) {
        ParentId parentId = new ParentId("pid1", "pid2");
        Parent parent = em.find(Parent.class, parentId);

        System.out.println(parent.getId1());
        System.out.println(parent.getId2());
        System.out.println(parent.getName());

        Child child = em.find(Child.class, "cid");
        System.out.println(child.getId());
        System.out.println(child.getParent().getId1());
        System.out.println(child.getParent().getId2());
        System.out.println(child.getParent().getName());
    }
}
