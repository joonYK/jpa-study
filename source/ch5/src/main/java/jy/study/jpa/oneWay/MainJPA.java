package jy.study.jpa.oneWay;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class MainJPA {

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
            search(em);

            //수정
            update(em);
            em.flush();

            //삭제
            delete(em);

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
        Member member1 = new Member("member1", "회원1");
        Member member2 = new Member("member2", "회원2");
        Team team1 = new Team("team1", "팀1");

        em.persist(member1);
        em.persist(member2);
        em.persist(team1);

        member1.setTeam(team1);
        member2.setTeam(team1);
    }

    static void search(EntityManager em) {
        Member member = em.find(Member.class,"member1");
        //객체 그래프 탐색
        Team team = member.getTeam();
        System.out.println("객체 그래프 탐색 : " + team);

        //JPQL
        String jpql = "select m from Member m join m.team t where t.name=:teamName";

        List<Member> resultList = em.createQuery(jpql, Member.class)
                .setParameter("teamName", "팀1")
                .getResultList();

        System.out.println("[query] " + resultList);
    }

    static void update(EntityManager em) {
        Team team2 = new Team("team2", "팀2");
        em.persist(team2);

        Member member = em.find(Member.class, "member1");
        member.setTeam(team2);
    }

    static void delete(EntityManager em) {
        Member member1 = em.find(Member.class, "member1");
        Team team = member1.getTeam();
        member1.setTeam(null);

        //엔티티를 삭제하려면 연관관계를 끊어야 함.
        em.remove(team);
    }


}
