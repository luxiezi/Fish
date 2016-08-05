package com.example.fo.bean;

/**
 * Created by lu on 2016/7/7.
 */
public class RateInfo {
    private String Name;//货币名称
    private String BuyingRate;//现汇买入价
    private String BuyingPrice;//现钞买入价
    private String SellingRate;//现汇卖出价
    private String SellingPrice;//现钞卖出价
    private String DiscountPrice;//折算价
    private String PublishTime;//发布时间，格式：yyyy-MM-dd HH:mm:ss

    public RateInfo(){

    }

    public RateInfo(String name, String buyingRate, String buyingPrice, String sellingRate, String sellingPrice, String discountPrice, String publishTime) {
        Name = name;
        BuyingRate = buyingRate;
        BuyingPrice = buyingPrice;
        SellingRate = sellingRate;
        SellingPrice = sellingPrice;
        DiscountPrice = discountPrice;
        PublishTime = publishTime;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBuyingRate() {
        return BuyingRate;
    }

    public void setBuyingRate(String buyingRate) {
        BuyingRate = buyingRate;
    }

    public String getBuyingPrice() {
        return BuyingPrice;
    }

    public void setBuyingPrice(String buyingPrice) {
        BuyingPrice = buyingPrice;
    }

    public String getSellingRate() {
        return SellingRate;
    }

    public void setSellingRate(String sellingRate) {
        SellingRate = sellingRate;
    }

    public String getSellingPrice() {
        return SellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        SellingPrice = sellingPrice;
    }

    public String getDiscountPrice() {
        return DiscountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        DiscountPrice = discountPrice;
    }

    public String getPublishTime() {
        return PublishTime;
    }

    public void setPublishTime(String publishTime) {
        PublishTime = publishTime;
    }

    @Override
    public String toString() {
        return "货币名称："+getName()+",现汇买入价："+getBuyingRate()+",现钞买入价："+getBuyingPrice()
                +",现汇卖出价："+getSellingRate()+";现钞卖出价："+getSellingPrice()+",折算价："+getDiscountPrice()
                +",发布时间："+getPublishTime();
    }
}
