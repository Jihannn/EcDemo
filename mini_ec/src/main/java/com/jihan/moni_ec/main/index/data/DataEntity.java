package com.jihan.moni_ec.main.index.data;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * @author Jihan
 * @date 2019/8/27
 */
public class DataEntity{

    public static String BANNER = "banner";
    public static String ARTICLE = "article";


    private String bannerImagePath;
    private String bannerUrl;

    private String bannerOrArticle;

    private String author;
    private String title;
    private int superChapterId;
    private String superChapterName;
    private int chapterId;
    private String chapterName;
    private String link;
    private String niceDate;

    public String getBannerOrArticle() {
        return bannerOrArticle;
    }

    public DataEntity setBannerOrArticle(String bannerOrArticle) {
        this.bannerOrArticle = bannerOrArticle;
        return this;
    }

    public String getBannerImagePath() {
        return bannerImagePath;
    }

    public DataEntity setBannerImagePath(String bannerImagePath) {
        this.bannerImagePath = bannerImagePath;
        return this;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public DataEntity setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public DataEntity setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public DataEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getSuperChapterId() {
        return superChapterId;
    }

    public DataEntity setSuperChapterId(int superChapterId) {
        this.superChapterId = superChapterId;
        return this;
    }

    public String getSuperChapterName() {
        return superChapterName;
    }

    public DataEntity setSuperChapterName(String superChapterName) {
        this.superChapterName = superChapterName;
        return this;
    }

    public int getChapterId() {
        return chapterId;
    }

    public DataEntity setChapterId(int chapterId) {
        this.chapterId = chapterId;
        return this;
    }

    public String getChapterName() {
        return chapterName;
    }

    public DataEntity setChapterName(String chapterName) {
        this.chapterName = chapterName;
        return this;
    }

    public String getLink() {
        return link;
    }

    public DataEntity setLink(String link) {
        this.link = link;
        return this;
    }

    public String getNiceDate() {
        return niceDate;
    }

    public DataEntity setNiceDate(String niceDate) {
        this.niceDate = niceDate;
        return this;
    }
}
