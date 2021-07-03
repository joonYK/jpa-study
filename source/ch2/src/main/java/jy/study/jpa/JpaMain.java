package jy.study.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
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
            //비즈니스 로직 실행
            logic(em);
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

    private static void logic(EntityManager em) {
        String id = "id1";
        Member member = new Member();
        member.setId(id);
        member.setUsername("준엽");
        member.setAge(31);

        //등록
        em.persist(member);

        //수정
        member.setAge(20);

        //한 건 조회
        Member findMember = em.find(Member.class, id);
        System.out.println("findMember=" + findMember.getUsername() + ", age=" + findMember.getAge());

        //목록 조회
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        System.out.println("members.size=" + members.size());

        //삭제
        em.remove(members);
    }
}
