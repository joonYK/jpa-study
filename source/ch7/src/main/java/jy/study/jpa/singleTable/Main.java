package jy.study.jpa.singleTable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("inheritance-singleTable");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        insert(em);
        tx.commit();

        emf.close();
    }

    static void insert(EntityManager em) {
        Item item = new Item();
        item.setName("상품");
        item.setPrice(1000);
        em.persist(item);

        Album album = new Album();
        album.setName("앨범");
        album.setPrice(1000);
        album.setArtist("아티스트");
        em.persist(album);

        Movie movie = new Movie();
        movie.setName("영화");
        movie.setDirector("감독");
        movie.setActor("배우");
        movie.setPrice(2000);
        em.persist(movie);

        Book book = new Book();
        book.setName("책");
        book.setPrice(3000);
        book.setAuthor("작가");
        book.setIsbn("341-231-42312341-123-1");
        em.persist(book);
    }
}
