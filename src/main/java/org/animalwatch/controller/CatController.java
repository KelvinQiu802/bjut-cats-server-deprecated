package org.animalwatch.controller;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.animalwatch.dao.CatsDao;
import org.animalwatch.model.Cat;

public class CatController {
    private final CatsDao catsDao;

    public CatController() {
        this.catsDao = new CatsDao();
    }

    public void getAllCats(Context ctx) {
        catsDao.getAllCats();
        ctx.json(catsDao.getAllCats());
    }

    public void createCat(Context ctx) {
        Cat cat = ctx.bodyAsClass(Cat.class);
        catsDao.insertCat(cat);
        ctx.status(HttpStatus.CREATED);
    }

    public void removeAllCats(Context ctx) {
        catsDao.removeAllCats();
        ctx.status(HttpStatus.NO_CONTENT);
    }
}
