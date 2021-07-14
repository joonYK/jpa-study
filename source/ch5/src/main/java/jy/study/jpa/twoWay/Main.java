package jy.study.jpa.twoWay;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-study");

        //엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        //트랜잭션 획득
        EntityTransaction tx = em.getTransaction();

        try {
            //트랜잭션 시작
            tx.begin();

            //등록
            insert(em);
            em.flush();

            //조회
            searchByTeam(em);

            //트랜잭션 커밋
            tx.commit();
        } catch (Exception e) {
            //트랜잭션 롤백
            tx.rollback();
        } finally {
            //엔티티 매니저 종료
            em.close();
        }
        //엔티티 매니저 팩토리 종료
        emf.close();

    }

    static void insert(EntityManager em) {
        Member2 member1 = new Member2("member1", "회원1");
        Member2 member2 = new Member2("member2", "회원2");
        Team2 team1 = new Team2("team1", "팀1");

        em.persist(member1);
        em.persist(member2);
        em.persist(team1);

        member1.setTeam(team1);
        member2.setTeam(team1);
        team1.getMembers().add(member1);
        team1.getMembers().add(member2);
    }

    static void searchByTeam(EntityManager em) {
        Team2 team = em.find(Team2.class, "team1");
        List<Member2> members = team.getMembers();

        System.out.println("회원 리스트 : " + members);
    }


}
