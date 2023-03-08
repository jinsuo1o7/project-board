package com.project.board.dto;

import com.project.board.domain.Article;


/**
 * A DTO for the {@link Article} entity
 */
public record ArticleUpdateDto(String title, String content, String hashtag) {
    public static ArticleUpdateDto of(String title, String content, String hashtag) {
        return new ArticleUpdateDto(title, content, hashtag);
    }
}