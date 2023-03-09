package com.project.board.service;

import com.project.board.domain.Article;
import com.project.board.domain.type.SearchType;
import com.project.board.dto.ArticleDto;
import com.project.board.dto.ArticleWithCommentsDto;
import com.project.board.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;


@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public Page<ArticleDto> searchArticles(SearchType type, String searchText, Pageable pageable) {
        if (!StringUtils.hasText(searchText)) {
            return articleRepository.findAll(pageable).map(ArticleDto::toDto);
        }

        return switch (type) {
            case TITLE -> articleRepository.findByTitleContaining(searchText, pageable).map(ArticleDto::toDto);
            case CONTENT -> articleRepository.findByContentContaining(searchText, pageable).map(ArticleDto::toDto);
            case ID -> articleRepository.findByMember_AccountId(searchText, pageable).map(ArticleDto::toDto);
            case NICKNAME -> articleRepository.findByMember_Nickname(searchText, pageable).map(ArticleDto::toDto);
            case HASHTAG -> articleRepository.findByHashtag("#" + searchText, pageable).map(ArticleDto::toDto);
        };
    }

    public ArticleWithCommentsDto getArticle(Long id) {
        return articleRepository.findById(id)
                .map(ArticleWithCommentsDto::toDto)
                .orElseThrow(() -> new EntityNotFoundException("article not found - articleId: " + id));
    }

    @Transactional
    public void saveArticle(ArticleDto dto) {
        articleRepository.save(dto.toEntity());
    }

    @Transactional
    public void updateArticle(ArticleDto dto) {
        try {

            Article article = articleRepository.getReferenceById(dto.id());
            if (StringUtils.hasText(dto.title()))
                article.setTitle(dto.title());
            if (StringUtils.hasText(dto.content()))
                article.setContent(dto.content());
            article.setHashtag(dto.hashtag());
        } catch (EntityNotFoundException e) {
            log.warn("Article not found, failed to update dto: {}", dto);
        }
    }

    @Transactional
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }
}
