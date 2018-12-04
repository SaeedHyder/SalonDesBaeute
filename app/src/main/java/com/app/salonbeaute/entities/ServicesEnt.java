package com.app.salonbeaute.entities;

public class ServicesEnt {

    String title;
    int image;
    int border;

    public ServicesEnt(String title, int image, int border) {
        this.title = title;
        this.image = image;
        this.border = border;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getBorder() {
        return border;
    }

    public void setBorder(int border) {
        this.border = border;
    }
}
