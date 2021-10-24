package jy.study.jpa.jpql.exec;

import jy.study.jpa.jpql.domain.Member;
import jy.study.jpa.jpql.domain.Product;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

public class Projection {

    @Getter
    static class UserDTO {
        private String username;
        private int age;

        public UserDTO(String username, int age) {
            this.username = username;
            this.age = age;
        }
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpql");
        EntityManager em = emf.createEntityManager();

        System.out.println("여러 프로젝션");
        multiProjection(em);

        System.out.println("여러 프로젝션 엔티티 타입");
        multiProjectionEntity(em);

        System.out.println("NEW 명령어");
        newCommand(em);
    }

    //여러 프로젝션
    private static void multiProjection(EntityManager em) {
        Query query = em.createQuery("SELECT m.username, m.age FROM Member m");
        List<Object[]> resultList = query.getResultList();

        for (Object[] row : resultList) {
            String username = (String) row[0];
            Integer age = (Integer) row[1];
            System.out.println("username = " + username);
            System.out.println("age = " + age);
        }
    }

    //여러 프로젝션 엔티티 타입
    private static void multiProjectionEntity(EntityManager em) {
        List<Object[]> resultList = em.createQuery("SELECT o.member, o.product, o.orderAmount FROM ORDERS o")
                .getResultList();

        for (Object[] row : resultList) {
            Member member = (Member) row[0];
            Product product = (Product) row[1];
            int orderAmount = (Integer) row[2];
            System.out.println("member = " + member);
            System.out.println("product = " + product);
            System.out.println("orderAmount = " + orderAmount);
        }
    }

    private static void newCommand(EntityManager em) {
        TypedQuery<UserDTO> query = em.createQuery(
                "SELECT new jy.study.jpa.jpql.exec.Projection$UserDTO(m.username, m.age) FROM Member m", UserDTO.class);

        List<UserDTO> resultList = query.getResultList();
        for (UserDTO userDTO : resultList) {
            System.out.println(userDTO.getUsername());
            System.out.println(userDTO.getAge());
        }
    }
}
