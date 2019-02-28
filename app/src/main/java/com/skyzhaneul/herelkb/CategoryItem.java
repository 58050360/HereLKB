package com.skyzhaneul.herelkb;

public class CategoryItem {

    public String name,imageLink;

    public CategoryItem(String name, String imageLink) {
        this.name = name;
        this.imageLink = imageLink;
    }

    public CategoryItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
