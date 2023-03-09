package com.project.board.dto;

import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.project.board.domain.ArticleComment} entity
 */
public record ArticleResponse(
        Long id,
        String title,
        String content,
        String hashtag,
        LocalDateTime createdAt,

        String email,
        String nickname
) {
    public static ArticleResponse of(Long id, String title, String content, String hashtag, LocalDateTime createdAt, String email, String nickname) {
        return new ArticleResponse(id, title, content, hashtag, createdAt, email, nickname);
    }

    public static ArticleResponse toDto(ArticleDto dto) {
        String name = dto.memberDto().nickname();
        if (StringUtils.hasText(name)) {
            name = dto.memberDto().accountId();
        }

        return new ArticleResponse(
                dto.id(),
                dto.title(),
                dto.content(),
                dto.hashtag(),
                dto.createdAt(),
                dto.memberDto().email(),
                name
        );
    }
}