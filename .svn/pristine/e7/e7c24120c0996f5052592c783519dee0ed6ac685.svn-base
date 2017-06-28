package me.iwf.photopicker.entity;

import android.text.TextUtils;

import java.io.Serializable;

public class Photo implements Serializable {

    private int id;
    private String path;
    private int width;
    private int height;

    public Photo(int id, String path) {
        this.id = id;
        this.path = path;
    }

    public Photo() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Photo)) return false;
        Photo photo = (Photo) o;
        return TextUtils.equals(photo.getPath(), getPath());
    }

    @Override
    public int hashCode() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
