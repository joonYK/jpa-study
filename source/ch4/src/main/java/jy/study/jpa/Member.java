package jy.study.jpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "MEMBER", uniqueConstraints = {
        @UniqueConstraint(
                name = "NAME_AGE_UNIQUE",
                columnNames = {"NAME", "AGE"}
        )
})
public class Member {
 
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private String id;

    // 제약조건 : 회원 이름은 필수로 입력, 10자를 초과하면 안 됨.
    @Column(name = "NAME", nullable = false, length = 10)
    private String username;
    
    private Integer age;
    
    //자바의 enum을 사용해서 회원 타입 구분
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    //자바의 날짜 타입은 @Temporal을 사용
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    //필드 길이 제한이 없기 때문에 @Lob을 사용하면 VARCHAR 타입이 아닌 CLOB, BLOB 타입을 매핑
    @Lob
    private String description;
    
    
}
