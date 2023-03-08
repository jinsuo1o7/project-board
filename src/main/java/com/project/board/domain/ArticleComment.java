package com.project.board.domain;

import com.project.board.domain.base.BaseEntity;
import com.project.board.domain.base.BaseTimeEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ArticleComment extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "article_comment_id")
    private Long id;

    @Setter
    @Column(nullable = false, length = 500)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    @ToString.Exclude
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public void setArticle(Article article) {
        this.article = article;
    }

    private ArticleComment(Member member, Article article, String content) {
        this.member = member;
        this.article = article;
        this.content = content;
    }

    public static ArticleComment of(Member member, Article article, String content) {
        return new ArticleComment(member, article, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleComment that)) return false;
        return that.getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
