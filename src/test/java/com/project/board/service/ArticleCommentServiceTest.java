package com.project.board.service;

import com.project.board.domain.Article;
import com.project.board.dto.ArticleCommentDto;
import com.project.board.repository.ArticleCommentRepository;
import com.project.board.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@DisplayName("biz logic - comments")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {
    @InjectMocks private ArticleCommentService sut;
    @Mock
    private ArticleRepository articleRepository;
    @Mock
    private ArticleCommentRepository articleCommentRepository;

    @DisplayName("searching article id return comments list")
    @Test
    void givenSearchArticleIdWhenGetComments_thenReturnCommentsList() {
        // Given
        Long id = 1L;
        given(articleRepository.findById(id)).willReturn(
                Optional.of(Article.of("title", "content", "#java")));

        // When
        List<ArticleCommentDto> comments = sut.searchArticleComment(id);

        // Then
        assertThat(comments).isNotNull();
        then(articleRepository).should().findById(id);
    }
}