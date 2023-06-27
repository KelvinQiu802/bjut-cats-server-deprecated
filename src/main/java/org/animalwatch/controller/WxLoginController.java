package org.animalwatch.controller;

import io.javalin.http.Context;
import okhttp3.*;
import org.animalwatch.Config.WxConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class WxLoginController {
    private final OkHttpClient client;
    private final Logger logger = LoggerFactory.getLogger(WxLoginController.class);

    public WxLoginController() {
        client = new OkHttpClient();
    }

    public void login(Context ctx) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://api.weixin.qq.com/sns/jscode2session \n" +
                "\n").newBuilder();
        urlBuilder.addQueryParameter("appid", WxConfig.appid);
        urlBuilder.addQueryParameter("secret", WxConfig.secret);
        urlBuilder.addQueryParameter("js_code", ctx.queryParam("js_code"));
        urlBuilder.addQueryParameter("grant_type", "authorization_code");

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        try (
                Response response = call.execute();
        ) {
            ctx.result(response.body().string());
            logger.info("WX Login Success");
        } catch (IOException e) {
            logger.error("WX Login Fail", e);
        }
    }
}
