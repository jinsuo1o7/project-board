package com.project.board.dto;

import com.project.board.domain.ArticleComment;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * A DTO for the {@link ArticleComment} entity
 */
public record ArticleCommentResponse(
        Long id,
        String content,
        String email,
        String nickname,
        String userId,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        String createdBy,
        String modifiedBy
) {
    public static ArticleCommentResponse of(Long id, String content, String email, String nickname, String userId, LocalDateTime createdAt, LocalDateTime modifiedAt, String createdBy, String modifiedBy) {
        return new ArticleCommentResponse(id, content, email, nickname, userId, createdAt, modifiedAt, createdBy, modifiedBy);
    }

    public static ArticleCommentResponse toDto(ArticleCommentDto dto) {
        String nickname = dto.member().nickname();
        if (!StringUtils.hasText(nickname)) {
            nickname = dto.member().accountId();
        }

        return ArticleCommentResponse.of(
                dto.id(),
                dto.content(),
                dto.member().email(),
                nickname,
                dto.member().accountId(),
                dto.createdAt(),
                dto.modifiedAt(),
                dto.createdBy(),
                dto.modifiedBy()
        );
    }
}