package jy.study.jpa.idClass.identifying;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("idClass-identifying");
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
        parent.setName("parentName");
        em.persist(parent);

        Child child = new Child();
        child.setParent(parent);
        child.setChildId("cid1");
        child.setName("childName");
        em.persist(child);

        GrandChild grandChild = new GrandChild();
        grandChild.setChild(child);
        grandChild.setId("gcid1");
        grandChild.setName("grandChildName");
        em.persist(grandChild);

    }

    static void find(EntityManager em) {
        Parent parent = em.find(Parent.class, "pid1");

        System.out.println(parent.getId1());
        System.out.println(parent.getName());
        System.out.println();

        ChildId childId = new ChildId();
        childId.setParent(parent.getId1());
        childId.setChildId("cid1");
        Child child = em.find(Child.class, childId);
        System.out.println(child.getParent().getId1());
        System.out.println(child.getChildId());
        System.out.println(child.getName());
        System.out.println();

        GrandChildId grandChildId = new GrandChildId();
        grandChildId.setChild(childId);
        grandChildId.setId("gcid1");
        GrandChild grandChild = em.find(GrandChild.class, grandChildId);
        System.out.println(grandChild.getChild().getParent().getId1());
        System.out.println(grandChild.getChild().getChildId());
        System.out.println(grandChild.getId());
        System.out.println(grandChild.getName());

    }
}
