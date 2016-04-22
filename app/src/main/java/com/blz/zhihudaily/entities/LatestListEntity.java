package com.blz.zhihudaily.entities;

import java.util.List;

/**
 * Created by 卜令壮
 * on 2016/3/28
 * E-mail q137617549@qq.com
 */
public class LatestListEntity {

    private String date;
    private List<StoriesEntity> stories;
    private List<StoryEntity> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesEntity> getStories() {
        return stories;
    }

    public void setStories(List<StoriesEntity> stories) {
        this.stories = stories;
    }

    public List<StoryEntity> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<StoryEntity> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesEntity {
        private String type;
        private String id;
        private String ga_prefix;
        private String title;
        private List<String> images;

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

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

}
