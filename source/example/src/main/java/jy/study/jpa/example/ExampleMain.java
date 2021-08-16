package jy.study.jpa.example;

import jy.study.jpa.example.entity.*;
import jy.study.jpa.example.entity.item.Album;
import jy.study.jpa.example.entity.item.Book;
import jy.study.jpa.example.entity.item.Item;
import jy.study.jpa.example.entity.item.Movie;
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
        Album album1 = new Album("앨범1", 1000, 10, "아티스트1", "ETC");
        Album album2 = new Album("앨범2", 1200, 6, "아티스트2", "ETC");
        Book book1 = new Book("책1", 2000, 15, "저자", "ISBN");
        Movie movie1 = new Movie("영화1", 5000, 3, "감독", "배우");
        em.persist(album1);
        em.persist(album2);
        em.persist(book1);
        em.persist(movie1);

        //카테고리
        Category categoryAlbums = new Category();
        categoryAlbums.setName("앨범");
        categoryAlbums.addItem(album1);
        categoryAlbums.addItem(album2);

        Category categoryBooks = new Category();
        categoryBooks.setName("책");
        categoryBooks.addItem(book1);

        Category categoryMovies = new Category();
        categoryMovies.setName("영화");
        categoryMovies.addItem(movie1);

        em.persist(categoryAlbums);
        em.persist(categoryBooks);
        em.persist(categoryMovies);
    }

    static void order(EntityManager em) {
        //회원 조회
        Member member = em.find(Member.class, "memberID");

        //카테고리(앨범) 리스트
        TypedQuery<Category> albumCategoryQuery = em.createQuery("select cate from Category cate where cate.name = :cateName", Category.class);
        albumCategoryQuery.setParameter("cateName", "앨범");
        Category categoryAlbums = albumCategoryQuery.getSingleResult();

        //앨범 상품 리스트
        List<Item> items = categoryAlbums.getItems();

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
