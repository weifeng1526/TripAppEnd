package com.ron.product_server;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
// @ApplicationPath 標註REST資源的起始網址
@ApplicationPath("rest")
public class MyApplication extends ResourceConfig {
    public MyApplication() {
    	// packages 指定專案 package
        packages("com.ron.product_server");
        // 註冊要使用multipart功能
        register(org.glassfish.jersey.media.multipart.MultiPartFeature.class);
    }
}