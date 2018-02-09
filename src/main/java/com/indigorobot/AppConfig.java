package com.indigorobot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    //@Value("${mysql.imagemeta.table}")
    public static String imageMetaTable = "image_meta";
}
