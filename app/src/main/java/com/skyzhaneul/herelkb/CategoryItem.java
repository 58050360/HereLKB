package com.skyzhaneul.herelkb;

public class CategoryItem {

    public String name,imageLink,detail,imageLink2;

    public CategoryItem(String name, String imageLink,String detail,String imageLink2) {
        this.name = name;
        this.imageLink = imageLink;
        this.detail = detail;
        this.imageLink2 = imageLink2;
    }


    public CategoryItem() {
    }

    public String getImageLink2() {
        return imageLink2;
    }

    public void setImageLink2(String imageLink2) {
        this.imageLink2 = imageLink2;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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
