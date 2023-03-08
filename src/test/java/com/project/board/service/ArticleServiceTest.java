package com.project.board.service;

import com.project.board.domain.Article;
import com.project.board.domain.type.SearchType;
import com.project.board.dto.ArticleDto;
import com.project.board.dto.ArticleUpdateDto;
import com.project.board.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;


import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

@DisplayName("biz logic - article")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {
    @InjectMocks private ArticleService sut;
    @Mock
    private ArticleRepository articleRepository;
    @DisplayName("searching return article list")
    @Test
    void givenSearchParamWhenSearchingArticles_thenReturnArticleList() {
        // Given

        // When
        Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "search word"); // title, content, id, 넥네임, 해시태그

        // Then
        assertThat(articles).isNotNull();
    }

    @DisplayName("post article, create article")
    @Test
    void givenArticlePostWhenCreatingArticle_thenSaveArticle() {
        // Given
        ArticleDto dto = ArticleDto.of(LocalDateTime.now(), "jinsuo", "title", "content", "hashtag");
        given(articleRepository.save(any(Article.class))).willReturn(null);

        // When
        sut.saveArticle(dto);

        // Then
        then(articleRepository).should().save(any(Article.class));
    }
    @DisplayName("edit article, update article")
    @Test
    void givenArticleEditWhenChangingArticle_thenUpdateArticle() {
        // Given
        ArticleUpdateDto dto = ArticleUpdateDto.of("title", "content", "hashtag");
        given(articleRepository.save(any(Article.class))).willReturn(null);

        // When
        sut.updateArticle(1L,dto);

        // Then
        then(articleRepository).should().save(any(Article.class));
    }
    @DisplayName("delete article, remove article")
    @Test
    void givenArticleIdWhenDeletingArticle_thenRemoveArticle() {
        // Given
        willDoNothing().given(articleRepository).delete(any(Article.class));

        // When
        sut.deleteArticle(1L);

        // Then
        then(articleRepository).should().delete(any(Article.class));
    }
    @DisplayName("searching return article detail")
    @Test
    void givenArticleIdWhenSearchingArticleDetail_thenReturnArticleDetail() {
        // Given

        // When
        ArticleDto article = sut.searchArticle(1L);

        // Then
        assertThat(article).isNotNull();
    }
}