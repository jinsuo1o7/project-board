package com.project.board.service;

import com.project.board.domain.Article;
import com.project.board.domain.Member;
import com.project.board.domain.type.SearchType;
import com.project.board.dto.ArticleDto;
import com.project.board.dto.ArticleUpdateDto;
import com.project.board.dto.ArticleWithCommentsDto;
import com.project.board.dto.MemberDto;
import com.project.board.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.time.LocalDateTime;
import java.util.Optional;

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
        Pageable pageable = Pageable.ofSize(20);
        given(articleRepository.findAll(pageable)).willReturn(Page.empty());

        // When
        Page<ArticleDto> articles = sut.searchArticles(null, null, pageable);

        // Then
        assertThat(articles).isEmpty();
        then(articleRepository).should().findAll(pageable);
    }

    @DisplayName("post article, create article")
    @Test
    void givenArticlePostWhenCreatingArticle_thenSaveArticle() {
        // Given
        SearchType searchType = SearchType.TITLE;
        String searchText = "title";
        Pageable pageable = Pageable.ofSize(20);
        given(articleRepository.findByTitleContaining(searchText, pageable)).willReturn(Page.empty());

        // When
        Page<ArticleDto> articles = sut.searchArticles(searchType, searchText, pageable);

        // Then
        assertThat(articles).isEmpty();
        then(articleRepository).should().findByTitleContaining(searchText, pageable);
    }
    @DisplayName("edit article, update article")
    @Test
    void givenArticleEditWhenChangingArticle_thenUpdateArticle() {
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
        Long id = 1L;
        Article article = Article.of(Member.of("", "", "", "", ""), "title", "content", "hashtag");
        given(articleRepository.findById(id)).willReturn(Optional.of(article));

        // When
        ArticleWithCommentsDto dto = sut.getArticle(id);

        // Then
        assertThat(dto)
                .hasFieldOrPropertyWithValue("title", article.getTitle())
                .hasFieldOrPropertyWithValue("content", article.getContent());
        then(articleRepository).should().findById(id);
    }
}