package org.animalwatch.controller;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.animalwatch.dao.CatsDao;
import org.animalwatch.model.Cat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CatController {
    private final CatsDao catsDao;
    private final Logger logger = LoggerFactory.getLogger(CatController.class);

    public CatController() {
        this.catsDao = new CatsDao();
    }

    public void getAllCats(Context ctx) {
        catsDao.getAllCats();
        ctx.json(catsDao.getAllCats());
        logger.info("Get All Cats");
    }

    public void createCat(Context ctx) {
        Cat cat = ctx.bodyAsClass(Cat.class);
        catsDao.insertCat(cat);
        ctx.status(HttpStatus.CREATED);
        logger.info("Create a Cat");
    }

    public void removeAllCats(Context ctx) {
        catsDao.removeAllCats();
        ctx.status(HttpStatus.NO_CONTENT);
        logger.info("Remove All Cats");
    }
}
