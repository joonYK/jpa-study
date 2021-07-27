package jy.study.jpa.mappedSuperclass;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Member extends BaseEntity {
    private String email;
}
