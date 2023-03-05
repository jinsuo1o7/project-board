package com.project.board.domain;

import com.project.board.domain.base.BaseTimeEntity;

import java.util.ArrayList;
import java.util.List;

public class ArticleComment extends BaseTimeEntity {
    private Long id;
    private String content;

    private List<Article> articles = new ArrayList<>();
}
