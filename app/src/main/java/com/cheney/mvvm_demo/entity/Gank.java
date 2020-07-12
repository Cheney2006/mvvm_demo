package com.cheney.mvvm_demo.entity;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.cheney.mvvm_demo.db.StringArrayConverter;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.List;

/**
 * db数据结构, 这里演示用，如果需要对数据转换后存储，可以写在这里
 * 推荐直接在pojo上进行标注
 */
@Entity
@TypeConverters(StringArrayConverter.class) // 将image拍平写入db
public class Gank {

    public static final int FULI = 0;
    public static final int ANDROID = 1000;
    public static final int FRONT_END = 2000;
    public static final int VIDEO = 3000;

    public static final int GROUP_FULI = -1;
    public static final int GROUP_ANDROID = -2;
    public static final int GROUP_FRONT_END = -3;
    public static final int GROUP_VIDEO = -4;

    @Ignore
    private boolean viewed = false; // 标记是否浏览过

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int typeId;
    @SerializedName("_id")
    private String gankId;

    private String author;
    private String category;
    private String content;
    private String createdAt;
    private String desc;
    private String email;
    private int index;
    private boolean isOriginal;
    private String license;
    private int likeCounts;
    private String markdown;
    private String originalAuthor;
    private String publishedAt;
    private int stars;
    private int status;
    private String title;
    private String type;
    private String updatedAt;
    private String url;
    private int views;
    private String[] images;
    private String[] likes;
    private String[] tags;


    @Ignore
    public Gank(int typeId) {
        this.typeId = typeId;
    }

    public Gank(int id, int typeId, String gankId, String createdAt,String title, String desc, String[] images, String publishedAt, String originalAuthor, String type, String url, int status, String author, String markdown) {

        super();
        this.id = id;
        this.typeId = typeId;
        this.gankId = gankId;
        this.createdAt = createdAt;
        this.title = title;
        this.desc = desc;
        this.images = images;
        this.publishedAt = publishedAt;
        this.originalAuthor = originalAuthor;
        this.type = type;
        this.url = url;
        this.status = status;
        this.author = author;
        this.markdown = markdown;
    }

    /**
     * 返回对应的图
     */
    public String getImageUrl(int index) {
        return (images!=null&&images.length > index) ? images[index] : "";
    }


    public boolean isViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getGankId() {
        return gankId;
    }

    public void setGankId(String gankId) {
        this.gankId = gankId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isOriginal() {
        return isOriginal;
    }

    public void setOriginal(boolean original) {
        isOriginal = original;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public int getLikeCounts() {
        return likeCounts;
    }

    public void setLikeCounts(int likeCounts) {
        this.likeCounts = likeCounts;
    }

    public String getMarkdown() {
        return markdown;
    }

    public void setMarkdown(String markdown) {
        this.markdown = markdown;
    }

    public String getOriginalAuthor() {
        return originalAuthor;
    }

    public void setOriginalAuthor(String originalAuthor) {
        this.originalAuthor = originalAuthor;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public String[] getLikes() {
        return likes;
    }

    public void setLikes(String[] likes) {
        this.likes = likes;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }
}
