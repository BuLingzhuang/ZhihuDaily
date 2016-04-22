package com.example;

import java.io.IOException;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyClass {
    public static void main(String[] args) {
        Schema schema = new Schema(1, "com.blz.zhihudaily.entities");
        schema.setDefaultJavaPackageDao("com.blz.zhihudaily.dao");

        //首页列表
        Entity story = schema.addEntity("StoryEntity");
        story.addStringProperty("image");
        story.addStringProperty("type");
        story.addStringProperty("id").primaryKey();
        story.addStringProperty("ga_prefix");
        story.addStringProperty("title");

        //故事详情
        Entity storyDetail = schema.addEntity("StoryDetailEntity");
        storyDetail.addStringProperty("body");
        storyDetail.addStringProperty("image_source");
        storyDetail.addStringProperty("title");
        storyDetail.addStringProperty("image");
        storyDetail.addStringProperty("share_url");
        storyDetail.addStringProperty("js");
        storyDetail.addStringProperty("ga_prefix");
        storyDetail.addStringProperty("section_thumbnail");
        storyDetail.addStringProperty("type");
        storyDetail.addStringProperty("id").primaryKey();
        storyDetail.addStringProperty("css");

        //预览页图片
        Entity splashScreen = schema.addEntity("SplashScreenEntity");
        splashScreen.addStringProperty("text");
        splashScreen.addStringProperty("img");

        try {
            new DaoGenerator().generateAll(schema, "lib/java-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
