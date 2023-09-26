package org.animalwatch.controller;

import io.javalin.http.Context;
import org.animalwatch.dao.ArticleDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArticleController {
    private final Logger logger = LoggerFactory.getLogger(ArticleController.class);
    private final ArticleDao articleDao;

    public ArticleController() {
        this.articleDao = new ArticleDao();
    }

    public void getAllArticles(Context ctx) {
        ctx.json(articleDao.getAllArticles());
    }
}
