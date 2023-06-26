package org.animalwatch;

import io.javalin.Javalin;
import io.javalin.plugin.bundled.CorsPluginConfig;
import org.animalwatch.controller.CatController;
import org.animalwatch.controller.ImageUploadTokenController;

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

        app.get("/api/imageUploadToken", imageUploadTokenController::getToken);

        app.get("/api/cats", catController::getAllCats);

        app.delete("/api/cats", catController::removeAllCats);
    }
}