package org.animalwatch.controller;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.animalwatch.dao.CatsDao;

public class CatController {
    private final CatsDao catsDao;

    public CatController() {
        this.catsDao = new CatsDao();
    }

    public void getAllCats(Context ctx) {
        ctx.json(catsDao.getAllCats());
    }

    public void removeAllCats(Context ctx) {
        catsDao.removeAllCats();
        ctx.status(HttpStatus.NO_CONTENT);
    }
}
