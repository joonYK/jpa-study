package jy.study.jpa.noIdentification.multi.idClass;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ParentId implements Serializable {

    private String id1; //Parent.id1 매핑
    private String id2; //Parent.id2 매핑

}
