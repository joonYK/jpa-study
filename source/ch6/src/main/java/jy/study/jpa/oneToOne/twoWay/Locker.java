package jy.study.jpa.oneToOne.twoWay;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Locker {

    @Id
    @GeneratedValue
    @Column(name = "LOKCER_ID")
    private Long id;

    private String name;

    @OneToOne(mappedBy = "locker")
    private Member member;

    public void setMember(Member member) {
        this.member = member;

        if (member.getLocker() != this)
            member.setLocker(this);
    }
}
