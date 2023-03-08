package com.project.board.dto;

import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.project.board.domain.ArticleComment} entity
 */
public record ArticleCommentDto(LocalDateTime createdAt, LocalDateTime modifiedAt, String createdBy, String modifiedBy,
                                String content) {
    public static ArticleCommentDto of(LocalDateTime createdAt, LocalDateTime modifiedAt, String createdBy, String modifiedBy, String content) {
        return new ArticleCommentDto(createdAt, modifiedAt, createdBy, modifiedBy, content);
    }
}