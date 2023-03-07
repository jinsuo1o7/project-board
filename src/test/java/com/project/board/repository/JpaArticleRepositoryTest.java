package com.project.board.repository;

import com.project.board.config.JpaConfig;
import com.project.board.domain.Article;
import com.project.board.domain.ArticleComment;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@DisplayName("jpa test")
@Import(JpaConfig.class)
@DataJpaTest
class JpaArticleRepositoryTest {
    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpaArticleRepositoryTest(@Autowired ArticleRepository articleRepository,
                                    @Autowired ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }
    @DisplayName("select test")
    @Test
    void givenTestData_whenSelecting_thenWorksFine() {
        // Given

        // When
        long articleTotal = articleRepository.count();
        long articleCommentTotal = articleCommentRepository.count();

        // Then
        assertThat(articleTotal).isEqualTo(200);
        assertThat(articleCommentTotal).isEqualTo(700);
    }

    @DisplayName("insert test")
    @Test
    void givenTestData_whenInserting_thenWorksFine() {
        // Given
        long prevArticleTotal = articleRepository.count();
        long prevArticleCommentTotal = articleCommentRepository.count();

        // When
        Article newArticle = Article.of("new title", "new content", "new hash tag");
        articleRepository.save(newArticle);
        ArticleComment newContent = ArticleComment.of(newArticle, "new content");
        articleCommentRepository.save(newContent);

        // Then
        assertThat(articleRepository.count()).isEqualTo(prevArticleTotal + 1);
        assertThat(articleCommentRepository.count()).isEqualTo(prevArticleCommentTotal + 1);
    }


    @DisplayName("update test")
    @Test
    void givenTestData_whenUpdating_thenWorksFine() {
        // Given
        Article article = articleRepository.findById(1L).orElseThrow();
        String updateHash = "update hash tag";
        article.setHashtag(updateHash);

        // When
        Article savedArticle = articleRepository.saveAndFlush(article);

        // Then
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updateHash);
    }

    @DisplayName("delete test")
    @Test
    void givenTestData_whenDeleting_thenWorksFine() {
        // Given
        Article article = articleRepository.findById(1L).orElseThrow();
        int articleCommentSize = article.getArticleComments().size();
        long prevArticleTotal = articleRepository.count();
        long prevCommentTotal = articleCommentRepository.count();

        // When
        articleRepository.delete(article);

        // Then
        assertThat(articleRepository.count()).isEqualTo(prevArticleTotal - 1);
        assertThat(articleCommentRepository.count()).isEqualTo(prevCommentTotal - articleCommentSize);
    }

}