package com.skyzhaneul.herelkb;

public class PrList {

    String pr_Name,pr_Category,pr_Address,pr_Open,pr_Tel,pr_Detail,pr_Latitude,pr_Longtitude,pr_Status,pr_user,pr_date;

    public PrList(String pr_Name, String pr_Category,String pr_Address,String pr_Open,String pr_Tel,String pr_Detail,String pr_Latitude,
                  String pr_Longtitude,String pr_Status,String pr_user,String pr_date) {
        this.pr_Name = pr_Name;
        this.pr_Category = pr_Category;
        this.pr_Address = pr_Address;
        this.pr_Open = pr_Open;
        this.pr_Tel = pr_Tel;
        this.pr_Detail = pr_Detail;
        this.pr_Latitude = pr_Latitude;
        this.pr_Longtitude = pr_Longtitude;
        this.pr_Status = pr_Status;
        this.pr_date = pr_date;
        this.pr_user = pr_user;

    }
    public PrList() {
    }
    public String getPr_Status() {
        return pr_Status;
    }


    public String getPr_Name() {
        return pr_Name;
    }

    public String getPr_user() {
        return pr_user;
    }

    public String getPr_date() {
        return pr_date;
    }

    public String getPr_Category() {
        return pr_Category;
    }

    public String getPr_Open() {
        return pr_Open;
    }

    public String getPr_Tel() {
        return pr_Tel;
    }

    public String getPr_Detail() {
        return pr_Detail;
    }

    public String getPr_Latitude() {
        return pr_Latitude;
    }

    public String getPr_Longtitude() {
        return pr_Longtitude;
    }

    public void setPr_Name(String pr_Name) {
        this.pr_Name = pr_Name;
    }

    public void setPr_Category(String pr_Category) {
        this.pr_Category = pr_Category;
    }

    public void setPr_Address(String pr_Address) {
        this.pr_Address = pr_Address;
    }

    public void setPr_Open(String pr_Open) {
        this.pr_Open = pr_Open;
    }

    public void setPr_Tel(String pr_Tel) {
        this.pr_Tel = pr_Tel;
    }

    public void setPr_Detail(String pr_Detail) {
        this.pr_Detail = pr_Detail;
    }

    public void setPr_Latitude(String pr_Latitude) {
        this.pr_Latitude = pr_Latitude;
    }

    public void setPr_Longtitude(String pr_Longtitude) {
        this.pr_Longtitude = pr_Longtitude;
    }

    public void setPr_Status(String pr_Status) {
        this.pr_Status = pr_Status;
    }

    public void setPr_user(String pr_user) {
        this.pr_user = pr_user;
    }

    public void setPr_date(String pr_date) {
        this.pr_date = pr_date;
    }

    public String getPr_Address() {
        return pr_Address;
    }


}
