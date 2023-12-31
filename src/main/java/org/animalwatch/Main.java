package org.animalwatch;

import io.javalin.Javalin;
import io.javalin.plugin.bundled.CorsPluginConfig;
import org.animalwatch.controller.*;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.plugins.enableCors(cors -> {
                cors.add(CorsPluginConfig::anyHost);
            });
        });

        app.start(7070);

        ImageUploadTokenController imageUploadTokenController = new ImageUploadTokenController();
        CatController catController = new CatController();
        WxLoginController wxLoginController = new WxLoginController();
        UserController userController = new UserController();
        CatImageController catImageController = new CatImageController();
        ImageLikeController imageLikeController = new ImageLikeController();
        ArticleController articleController = new ArticleController();

        app.get("/api/imageUploadToken", imageUploadTokenController::getToken);

        app.get("/api/jscode2session", wxLoginController::login);

        app.get("/api/cats", catController::getAllCats);

        app.post("/api/cats", catController::createCat);

        app.delete("/api/cats", catController::removeAllCats);

        app.get("/api/users/{openId}", userController::getUserByOpenId);

        app.post("/api/users", userController::createUser);

        app.post("/api/images", catImageController::addImage);

        app.get("/api/images/{state}", catImageController::getImagesByState);

        app.put("/api/images", catImageController::updateImage);

        app.post("/api/likes", imageLikeController::addLike);

        app.get("/api/likes", imageLikeController::getLikesBy);

        app.get("/api/articles", articleController::getAllArticles);
    }
}