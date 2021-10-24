package jy.study.jpa.jpql.domain.value;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
@AllArgsConstructor
@EqualsAndHashCode
public class Address {
    private String city;
    private String street;
    private String zipcode;
}
