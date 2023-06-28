package org.animalwatch.controller;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.animalwatch.dao.CatImageDao;
import org.animalwatch.model.CatImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CatImageController {
    private final Logger logger = LoggerFactory.getLogger(CatImageController.class);
    private final CatImageDao catImageDao;

    public CatImageController() {
        catImageDao = new CatImageDao();
    }

    public void addImage(Context ctx) {
        CatImage image = ctx.bodyAsClass(CatImage.class);
        catImageDao.addImage(image);
        ctx.status(HttpStatus.NO_CONTENT);
        logger.info("Add a Cat Image");
    }

    public void getImagesByState(Context ctx) {
        String state = ctx.pathParam("state");
        ctx.json(catImageDao.getImagesByState(state));
        logger.info("GET images by state");
    }

    public void updateImage(Context ctx) {
        CatImage image = ctx.bodyAsClass(CatImage.class);
        catImageDao.updateImage(image);
        ctx.status(HttpStatus.NO_CONTENT);
        logger.info("Update image data");
    }
}
