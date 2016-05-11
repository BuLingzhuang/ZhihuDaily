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
     * image_source : NASA Goddard Photo and Video / CC BY
     * title : 十年，圆寂水星的信使，带给你不再是黑点的水星
     * image : http://pic2.zhimg.com/e84cb8bd9b2f8d10c4e2f2fa8ae90ba1.jpg
     * share_url : http://daily.zhihu.com/story/8280225
     * js : []
     * ga_prefix : 051109
     * images : ["http://pic3.zhimg.com/e8a0c90b90e0cfcafabb6828360379fe.jpg"]
     * type : 0
     * id : 8280225
     * css : ["http://news-at.zhihu.com/css/news_qa.auto.css?v=4b3e3"]
     */

    private String body;
    private String image_source;
    private String title;
    private String image;
    private String share_url;
    private String ga_prefix;
    private int type;
    private int id;
    private List<?> js;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<?> getJs() {
        return js;
    }

    public void setJs(List<?> js) {
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
