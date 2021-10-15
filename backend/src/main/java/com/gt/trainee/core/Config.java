package com.gt.trainee.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class Config {
    public static String secret;
    public static String dateFormatTimetable;
    public static String dateFormat;


    @Autowired
    public Config(Environment env) {
        secret = env.getProperty("security.oauth2.client.client-secret");
        dateFormat = env.getProperty("application.date.format");
        dateFormatTimetable = env.getProperty("application.dateFormat.timetable");
    }
}
