package com.admin.projectadmintor.bean;

/**
 * Created by admin on 2017/12/13.
 */

/**
 * 设置一个picture的实体类
 *
 * 用来向适配器里里面添加数据用的
 *
 * 设置一个string类型的标题
 * 和int的图片
 *
 * */
public class Picture {
    private String title;
    private int imageId;

    public Picture() {
        super();
    }

    public Picture(String title, int imageId) {
        super();
        this.title = title;
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}