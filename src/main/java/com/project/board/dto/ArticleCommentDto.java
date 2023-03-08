package com.project.board.dto;

import com.project.board.domain.ArticleComment;

import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.project.board.domain.ArticleComment} entity
 */
public record ArticleCommentDto(Long id, ArticleDto article,MemberDto member,LocalDateTime createdAt, LocalDateTime modifiedAt, String createdBy, String modifiedBy,
                                String content) {
    public static ArticleCommentDto of(Long id, ArticleDto article,MemberDto member, LocalDateTime createdAt, LocalDateTime modifiedAt, String createdBy, String modifiedBy, String content) {
        return new ArticleCommentDto(id, article, member, createdAt, modifiedAt, createdBy, modifiedBy, content);
    }

    public static ArticleCommentDto toDto(ArticleComment entity) {
        return ArticleCommentDto.of(entity.getId(),
                ArticleDto.toDto(entity.getArticle()),
                MemberDto.toDto(entity.getMember()),
                entity.getCreatedAt(),
                entity.getModifiedAt(),
                entity.getCreatedBy(),
                entity.getModifiedBy(),
                entity.getContent());
    }
    public ArticleComment toEntity() {
        return ArticleComment.of(member.toEntity(), article.toEntity(), content);
    }
}