package com.project.board.dto;

import com.querydsl.core.annotations.QueryProjection;

import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.project.board.domain.Article} entity
 */
public record ArticleDto(
        LocalDateTime createdAt,
        String createdBy,
        String title,
        String content,
        String hashtag
) {

    @QueryProjection
    public ArticleDto(LocalDateTime createdAt, String createdBy, String title, String content, String hashtag) {
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    public static ArticleDto of(LocalDateTime createdAt, String createdBy, String title, String content, String hashtag) {
        return new ArticleDto(createdAt, createdBy, title, content, hashtag);
    }
}