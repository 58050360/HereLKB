package com.skyzhaneul.herelkb;

public class PrList {

    String pr_Id,pr_Name,pr_Category,pr_Address,pr_Open,pr_Tel,pr_Detail,pr_Latitude,pr_Longtitude,pr_Imagelogo,
            pr_Image1,pr_Image2,pr_Image3,pr_Status,pr_user;

    public PrList(String pr_Id, String pr_Name, String pr_Category,String pr_Address,String pr_Open,String pr_Tel,String pr_Detail,String pr_Latitude,
                  String pr_Longtitude,String pr_Status,String pr_Imagelogo,String pr_user,String pr_Image1,String pr_Image2,String pr_Image3) {
        this.pr_Id = pr_Id;
        this.pr_Name = pr_Name;
        this.pr_Category = pr_Category;
        this.pr_Address = pr_Address;
        this.pr_Open = pr_Open;
        this.pr_Tel = pr_Tel;
        this.pr_Detail = pr_Detail;
        this.pr_Latitude = pr_Latitude;
        this.pr_Longtitude = pr_Longtitude;
        this.pr_Status = pr_Status;
        this.pr_Imagelogo = pr_Imagelogo;
        this.pr_user = pr_user;
        this.pr_Image1 = pr_Image1;
        this.pr_Image2 = pr_Image2;
        this.pr_Image3 = pr_Image3;
    }

    public String getPr_Status() {
        return pr_Status;
    }

    public String getPr_user() {
        return pr_user;
    }

    public String getPr_Id() {
        return pr_Id;
    }

    public String getPr_Name() {
        return pr_Name;
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

    public String getPr_Imagelogo() {
        return pr_Imagelogo;
    }

    public String getPr_Image1() {
        return pr_Image1;
    }

    public String getPr_Image2() {
        return pr_Image2;
    }

    public String getPr_Image3() {
        return pr_Image3;
    }

    public String getPr_Address() {
        return pr_Address;
    }
}
