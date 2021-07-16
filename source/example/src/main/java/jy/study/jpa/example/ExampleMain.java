package jy.study.jpa.example;

import jy.study.jpa.example.entity.Item;
import jy.study.jpa.example.entity.Member;
import jy.study.jpa.example.entity.Order;
import jy.study.jpa.example.entity.OrderItem;
import jy.study.jpa.example.type.OrderStatus;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ExampleMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-study-example");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            logic(em);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    public static void logic(EntityManager em) {
        Member member = new Member("준엽", "city", "street", "12314");
        em.persist(member);
        em.flush();

        Order order = new Order();
        order.setMember(member);
        order.setStatus(OrderStatus.ORDER);
        em.persist(order);
        em.flush();

        /*
        Item item = new Item("goods", 1000, 10);
        em.persist(item);
        em.flush();

        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrder(order);
        orderItem.setOrderPrice(item.getPrice());
        orderItem.setCount(1);
        em.persist(orderItem);*/
    }

}
