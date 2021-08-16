package jy.study.jpa.example.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

    protected Date createdDate;
    protected Date lastModifiedDate;

    public BaseEntity() {
        this.createdDate = new Date();
    }
}
