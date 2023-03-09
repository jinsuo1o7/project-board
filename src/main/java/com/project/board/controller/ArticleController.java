package com.project.board.controller;

import com.project.board.domain.type.SearchType;
import com.project.board.dto.ArticleResponse;
import com.project.board.dto.ArticleWithCommentResponse;
import com.project.board.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.data.domain.Sort.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;
    @GetMapping
    public String articles(
            @RequestParam(required = false) SearchType searchType,
            @RequestParam(required = false) String searchText,
            @PageableDefault(size = 10, sort = "createdAt", direction = Direction.DESC) Pageable pageable,
            ModelMap map) {
        map.addAttribute("articles", articleService.searchArticles(searchType, searchText, pageable).map(ArticleResponse::toDto));
        return "articles/index";
    }

    @GetMapping("/{articleId}")
    public String articleDetail(@PathVariable Long articleId, ModelMap map) {
        ArticleWithCommentResponse article = ArticleWithCommentResponse.toRes(articleService.getArticle(articleId));

        map.addAttribute("article", article);
        map.addAttribute("articleComment", article.articleCommentsResponse());
        return "articles/detail";
    }
}