package org.animalwatch.controller;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.animalwatch.dao.UserDao;
import org.animalwatch.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserDao userDao;

    public UserController() {
        userDao = new UserDao();
    }

    public void getUserByOpenId(Context ctx) {
        String openId = ctx.pathParam("openId");
        User user = userDao.getUserByOpenId(openId);
        if (user == null) {
            ctx.status(HttpStatus.NOT_FOUND);
            logger.info("User NOT FOUND");
        } else {
            ctx.json(user);
            logger.info("GET Users Success");
        }
    }

    public void createUser(Context ctx) {
        User user = ctx.bodyAsClass(User.class);
        userDao.createUser(user);
        ctx.status(HttpStatus.NO_CONTENT);
    }
}
