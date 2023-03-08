package com.project.board.service;

import com.project.board.domain.type.SearchType;
import com.project.board.dto.ArticleDto;
import com.project.board.dto.ArticleUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
@Service
public class ArticleService {

    public Page<ArticleDto> searchArticles(SearchType type, String keyword) {
        return Page.empty();
    }

    public ArticleDto searchArticle(Long id) {
        return null;
    }

    @Transactional
    public void saveArticle(ArticleDto dto) {

    }

    public void updateArticle(Long id, ArticleUpdateDto dto) {
    }

    public void deleteArticle(Long id) {

    }
}
