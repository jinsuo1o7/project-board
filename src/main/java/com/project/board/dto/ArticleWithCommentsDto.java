package com.project.board.dto;

import com.project.board.domain.Article;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A DTO for the {@link Article} entity
 */
public record ArticleWithCommentsDto(
        Long id,
        MemberDto memberDto,
        List<ArticleCommentDto> articleCommentDtos,
        String title,
        String content,
        String hashtag,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String ModifiedBy
) {

    public static ArticleWithCommentsDto of(Long id, MemberDto memberDto, List<ArticleCommentDto> articleCommentDtos,String title, String content, String hashtag, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String ModifiedBy) {
        return new ArticleWithCommentsDto(id, memberDto, articleCommentDtos, title, content, hashtag, createdAt, createdBy, modifiedAt, ModifiedBy);
    }

    public static ArticleWithCommentsDto toDto(Article entity) {
        return new ArticleWithCommentsDto(
                entity.getId(),
                MemberDto.toDto(entity.getMember()),
                entity.getArticleComments().stream()
                        .map(ArticleCommentDto::toDto)
                        .collect(Collectors.toList()),
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