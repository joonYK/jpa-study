package jy.study.jpa.domain.value;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Address {
    private String city;
    private String street;
    private String zipcode;
}
