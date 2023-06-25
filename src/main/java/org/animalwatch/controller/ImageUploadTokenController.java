package org.animalwatch.controller;

import com.qiniu.util.Auth;
import io.javalin.http.Context;
import org.animalwatch.Config.BucketConfig;
import org.animalwatch.model.ImageUploadToken;

public class ImageUploadTokenController {
    private String accessKey = BucketConfig.accessKey;
    private String secretKey = BucketConfig.secretKey;
    private String bucket = BucketConfig.bucket;

    public void getToken(Context ctx) {
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        ctx.json(new ImageUploadToken(upToken));
    }
}
