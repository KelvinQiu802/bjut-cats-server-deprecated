package org.animalwatch;

import io.javalin.Javalin;
import org.animalwatch.controller.ImageUploadTokenController;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(/*config*/)
                .start(7070);

        ImageUploadTokenController imageUploadTokenController = new ImageUploadTokenController();
        app.get("/imageUploadToken", imageUploadTokenController::getToken);
    }
}