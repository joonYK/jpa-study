package jy.study.jpa.example.value;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Address {
    private String city;
    private String street;
    private String zipcode;
}
