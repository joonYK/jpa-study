package jy.study.jpa.example;

import jy.study.jpa.example.entity.*;
import jy.study.jpa.example.type.DeliveryStatus;
import jy.study.jpa.example.type.OrderStatus;

import javax.persistence.*;
import java.util.List;

public class ExampleMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-study-example");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        init(em);
        tx.commit();

        em.clear();

        tx.begin();
        order(em);
        tx.commit();

        emf.close();
    }

    static void init(EntityManager em) {
        //회원
        Member member = new Member("memberID", "준엽", "서울", "종로1가", "123451");
        em.persist(member);

        //상품
        Item item1 = new Item("상품1", 1000, 10);
        Item item2 = new Item("상품2", 2000, 15);
        Item item3 = new Item("상품3", 5000, 3);
        em.persist(item1);
        em.persist(item2);
        em.persist(item3);

        //카테고리
        Category category1 = new Category();
        category1.setName("분류1");
        category1.addItem(item1);
        category1.addItem(item2);
        Category category2 = new Category();
        category1.setName("분류2");
        category2.addItem(item3);
        em.persist(category1);
        em.persist(category2);
    }

    static void order(EntityManager em) {
        //회원 조회
        Member member = em.find(Member.class, "memberID");

        //카테고리 리스트
        TypedQuery<Category> categoryQuery = em.createQuery("select cate from Category cate", Category.class);
        List<Category> categories = categoryQuery.getResultList();
        
        //분류1의 상품 리스트
        List<Item> items = categories.get(0).getItems();

        //배송
        Delivery delivery = new Delivery();
        delivery.setCity(member.getCity());
        delivery.setStreet(member.getStreet());
        delivery.setZipcode(member.getZipcode());
        delivery.setStatus(DeliveryStatus.READY);
        em.persist(delivery);

        //주문
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        order.setStatus(OrderStatus.ORDER);
        em.persist(order);

        //주문상품
        for (Item item : items) {
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setOrder(order);
            orderItem.setOrderPrice(item.getPrice());
            orderItem.setCount(1);
            em.persist(orderItem);
        }
    }

}
