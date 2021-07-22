package jy.study.jpa.oneToOne.twoWay;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String username;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    public void setLocker(Locker locker) {
        this.locker = locker;

        if (locker.getMember() != this)
            locker.setMember(this);
    }
}
