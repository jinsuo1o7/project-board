package com.project.board.dto;

import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * A DTO for the {@link com.project.board.domain.ArticleComment} entity
 */
public record ArticleWithCommentResponse(
        Long id,
        String title,
        String content,
        String hashtag,
        LocalDateTime createdAt,

        String email,
        String nickname,
        List<ArticleCommentDto> articleCommentsResponse
) {
    public static ArticleWithCommentResponse of(Long id, String title, String content, String hashtag, LocalDateTime createdAt, String email, String nickname, List<ArticleCommentDto> articleCommentsResponse
    ) {
        return new ArticleWithCommentResponse(id, title, content, hashtag, createdAt, email, nickname, articleCommentsResponse);
    }

    public static ArticleWithCommentResponse toRes(ArticleWithCommentsDto dto) {
        String name = dto.memberDto().nickname();
        if (StringUtils.hasText(name)) {
            name = dto.memberDto().accountId();
        }

        return ArticleWithCommentResponse.of(
                dto.id(),
                dto.title(),
                dto.content(),
                dto.hashtag(),
                dto.createdAt(),
                dto.memberDto().email(),
                name,
                dto.articleCommentDtos()
        );
    }

}