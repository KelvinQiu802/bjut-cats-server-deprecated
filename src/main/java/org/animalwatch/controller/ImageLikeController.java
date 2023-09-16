package org.animalwatch.controller;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.animalwatch.dao.ImageLikeDao;
import org.animalwatch.model.ImageLike;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageLikeController {
    private final Logger logger = LoggerFactory.getLogger(ImageLikeController.class);
    private final ImageLikeDao imageLikeDao;

    public ImageLikeController() {
        imageLikeDao = new ImageLikeDao();
    }

    public void addLike(Context ctx) {
        ImageLike imageLike = ctx.bodyAsClass(ImageLike.class);
        imageLikeDao.addLike(imageLike);
        ctx.status(HttpStatus.NO_CONTENT);
        logger.info("ADD an LIKE");
    }

    public void getLikesBy(Context ctx) {
        String by = ctx.queryParam("by");
        String value = ctx.queryParam("value");
        if (by.equals("openId")) {
            ctx.json(imageLikeDao.getLikesByOpenId(value));
            logger.info("Get by OpenId");
        } else if (by.equals("imageUrl")) {
            ctx.json(imageLikeDao.getLikesByImageUrl(value));
            logger.info("Get by imageUrl");
        } else {
            ctx.status(HttpStatus.BAD_REQUEST);
            logger.error("GET Likes: Invalid Query Param");
        }
    }
}
