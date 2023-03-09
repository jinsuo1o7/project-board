package com.project.board.dto;

import com.project.board.domain.Article;

import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.project.board.domain.Article} entity
 */
public record ArticleDto(
        Long id,
        MemberDto memberDto,
        String title,
        String content,
        String hashtag,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String ModifiedBy
) {

    public static ArticleDto of(Long id, MemberDto memberDto, String title, String content, String hashtag, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String ModifiedBy) {
        return new ArticleDto(id, memberDto, title, content, hashtag, createdAt, createdBy, modifiedAt, ModifiedBy);
    }

    public static ArticleDto toDto(Article entity) {
        return new ArticleDto(
                entity.getId(),
                MemberDto.toDto(entity.getMember()),
                entity.getTitle(),
                entity.getContent(),
                entity.getHashtag(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy());
    }

    public Article toEntity() {
        return Article.of(memberDto.toEntity(), title, content, hashtag);
    }
}