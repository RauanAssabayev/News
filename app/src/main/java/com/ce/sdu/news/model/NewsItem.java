package com.ce.sdu.news.model;

/**
 * Created by rauan on 09.02.2017.
 */

public class NewsItem {
    private String title;
    private String desc;
    private String resId;
    private String pubDate;

    public NewsItem(String title, String desc, String resId, String pubDate) {
        this.title = title;
        this.desc = desc;
        this.resId = resId;
        this.pubDate = pubDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }




}
