package jy.study.jpa.identification.multi.embeddedId;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("identification-multi-embeddedId");
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
        parent.setId("pid");
        parent.setName("parentName");
        em.persist(parent);

        ChildId childId = new ChildId();
        childId.setParentId(parent.getId());
        childId.setId("cid");

        Child child = new Child();
        child.setParent(parent);
        child.setId(childId);
        child.setName("childName");
        em.persist(child);

        GrandChildId grandChildId = new GrandChildId();
        grandChildId.setChildId(childId);
        grandChildId.setId("gcid");

        GrandChild grandChild = new GrandChild();
        grandChild.setChild(child);
        grandChild.setId(grandChildId);
        grandChild.setName("grandChildName");
        em.persist(grandChild);
    }

    static void find(EntityManager em) {
        Parent parent = em.find(Parent.class, "pid");
        System.out.println(parent.getId());
        System.out.println(parent.getName());
        System.out.println();

        ChildId childId = new ChildId();
        childId.setParentId("pid");
        childId.setId("cid");
        Child child = em.find(Child.class, childId);
        System.out.println(child.getParent().getId());
        System.out.println(child.getId().getId());
        System.out.println(child.getName());
        System.out.println();

        GrandChildId grandChildId = new GrandChildId();
        grandChildId.setChildId(childId);
        grandChildId.setId("gcid");
        GrandChild grandChild = em.find(GrandChild.class, grandChildId);
        System.out.println(grandChild.getChild().getParent().getId());
        System.out.println(grandChild.getChild().getId().getId());
        System.out.println(grandChild.getId().getId());
        System.out.println(grandChild.getName());
    }
}
