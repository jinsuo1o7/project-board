package com.project.board;

import com.project.board.domain.Article;
import com.project.board.domain.ArticleComment;
import com.project.board.repository.ArticleCommentRepository;
import com.project.board.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;
//    @PostConstruct
    public void init() {
        initService.initDb();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final ArticleRepository articleRepository;
        private final ArticleCommentRepository articleCommentRepository;

        public void initDb() {
//            Article article = Article.of("title", "content", "hashtag");
//            articleRepository.save(article);
//            ArticleComment articleComment = ArticleComment.of(article, "content");
//            articleCommentRepository.save(articleComment);
        }
    }
}
