package com.blz.zhihudaily.entities;

import java.util.List;

/**
 * Created by BuLingzhuang
 * on 2016/4/19
 * E-mail bulingzhuang@foxmail.com
 */
public class StoryDetailEntityDownload {

    /**
     * body : 123
     * image_source : Fate / Zero
     * title : 为什么游戏角色总是被分成力量型、智慧型、灵活型？
     * image : http://pic1.zhimg.com/82cabc71b4dd01a07db9a45faded9c88.jpg
     * share_url : http://daily.zhihu.com/story/8181894
     * js : []
     * ga_prefix : 041914
     * images : ["http://pic4.zhimg.com/171de135d64ffd71b6a7eaae83a70b9f.jpg"]
     * type : 0
     * id : 8181894
     * css : ["http://news-at.zhihu.com/css/news_qa.auto.css?v=4b3e3"]
     */

    private String body;
    private String image_source;
    private String title;
    private String image;
    private String share_url;
    private String ga_prefix;
    private String type;
    private String id;
    private List<String> js;
    private List<String> images;
    private List<String> css;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getJs() {
        return js;
    }

    public void setJs(List<String> js) {
        this.js = js;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }
}
