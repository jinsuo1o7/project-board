package com.project.board.domain;

import com.project.board.domain.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper = true)
@Getter
@Table(
        indexes = {
                @Index(columnList = "email", unique = true),
                @Index(columnList = "createdAt"),
                @Index(columnList = "createdBy"),
        }
)
@Entity
public class Member extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(length = 50)
    private String accountId;

    @Setter
    @Column(nullable = false)
    private String accountPw;

    @Setter
    @Column(length = 100)
    private String email;

    @Setter
    @Column(length = 100)
    private String nickname;
    @Setter
    private String memo;

    private Member(String accountId, String accountPw, String email, String nickname, String memo) {
        this.accountId = accountId;
        this.accountPw = accountPw;
        this.email = email;
        this.nickname = nickname;
        this.memo = memo;
    }

    public static Member of(String accountId, String accountPw, String email, String nickname, String memo) {
        return new Member(accountId, accountPw, email, nickname, memo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member member)) return false;
        return getAccountId().equals(member.getAccountId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccountId());
    }
}
