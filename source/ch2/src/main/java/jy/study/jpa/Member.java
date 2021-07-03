package jy.study.jpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
//이 클래스를 테이블과 매핑한다고 JPA 에게 알려줌. 엔티티 클래스라고 한다.
@Entity
//매핑할 테이블 정보. 이름 생략하면 클래스 이름을 테이블 이름으로 매핑.
@Table(name = "MEMBER")
public class Member {
    //기본키
    @Id
    //컬럼 매핑
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String username;
    
    //컬럼 매핑 정보가 없으면 필드명을 컬럼명으로 매핑
    private Integer age;
}
