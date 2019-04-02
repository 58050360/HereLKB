package com.skyzhaneul.herelkb;

public class CategoryItem {

    public String name, imageLink, detail, imageLink2, locateAddress, locateTime;

    public CategoryItem(String name, String imageLink, String detail, String imageLink2, String locateAddress, String locateTime) {
        this.name = name;
        this.imageLink = imageLink;
        this.detail = detail;
        this.imageLink2 = imageLink2;
        this.locateAddress = locateAddress;
        this.locateTime = locateTime;
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

    public String getLocateAddress() {
        return locateAddress;
    }

    public void setLocateAddress(String locateAddress) {
        this.locateAddress = locateAddress;
    }

    public String getLocateTime() {
        return locateTime;
    }

    public void setLocateTime(String locateTime) {
        this.locateTime = locateTime;
    }
}
