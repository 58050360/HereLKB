package com.skyzhaneul.herelkb;

public class CategoryItem {

    public String name, imageLink, detail, imageLink2, locateAddress, locateTime,locateTel,imageLink3,imageLink4,category,locateLatitude,locateLong;

    public CategoryItem(String name, String imageLink, String detail, String imageLink2, String locateAddress, String locateTime, String locateTel, String imageLink3, String imageLink4, String category, String locateLatitude, String locateLong) {
        this.name = name;
        this.imageLink = imageLink;
        this.detail = detail;
        this.imageLink2 = imageLink2;
        this.locateAddress = locateAddress;
        this.locateTime = locateTime;
        this.locateTel = locateTel;
        this.imageLink3 = imageLink3;
        this.imageLink4 = imageLink4;
        this.category = category;
        this.locateLatitude = locateLatitude;
        this.locateLong = locateLong;
    }

    public CategoryItem() {
    }
    public String getLocateLatitude() {
        return locateLatitude;
    }

    public void setLocateLatitude(String locateLatitude) {
        this.locateLatitude = locateLatitude;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocateTel() {
        return locateTel;
    }

    public void setLocateTel(String locateTel) {
        this.locateTel = locateTel;
    }

    public String getImageLink3() {
        return imageLink3;
    }

    public void setImageLink3(String imageLink3) {
        this.imageLink3 = imageLink3;
    }

    public String getImageLink4() {
        return imageLink4;
    }

    public void setImageLink4(String imageLink4) {
        this.imageLink4 = imageLink4;
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

    public String getLocateLong() {
        return locateLong;
    }

    public void setLocateLong(String locateLong) {
        this.locateLong = locateLong;
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
