package com.project.board.dto;

import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.project.board.domain.ArticleComment} entity
 */
public record ArticleCommentResponse(
        Long id,
        String content,
        LocalDateTime createdAt,
        String email,
        String nickname
) implements Serializable {
    public static ArticleCommentResponse of(Long id, String content, LocalDateTime createdAt, String email, String nickname) {
        return new ArticleCommentResponse(id, content, createdAt, email, nickname);
    }

    public static ArticleCommentResponse toDto(ArticleCommentDto dto) {
        String name = dto.member().nickname();
        if (StringUtils.hasText(name)) {
            name = dto.member().accountId();
        }

        return new ArticleCommentResponse(
                dto.id(),
                dto.content(),
                dto.createdAt(),
                dto.member().email(),
                name
        );
    }
}