package com.nitin.ilovezappos.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nitin on 02-10-2017.
 */

public class Product {
    @SerializedName("brandName")
    private String brandName;
    @SerializedName("thumbnailImageUrl")
    private String thumbnailImageUrl;
    @SerializedName("productId")
    private String productId;
    @SerializedName("originalPrice")
    private String originalPrice;
    @SerializedName("styleId")
    private String styleId;
    @SerializedName("colorId")
    private String colorId;
    @SerializedName("price")
    private String price;
    @SerializedName("percentOff")
    private String percentOff;
    @SerializedName("productUrl")
    private String productUrl;
    @SerializedName("productName")
    private String productName;

    public Product(String brandName, String thumbnailImageUrl, String productId, String originalPrice, String styleId, String colorId, String price, String percentOff, String productUrl, String productName) {
        this.brandName = brandName;
        this.thumbnailImageUrl = thumbnailImageUrl;
        this.productId = productId;
        this.originalPrice = originalPrice;
        this.styleId = styleId;
        this.colorId = colorId;
        this.price = price;
        this.percentOff = percentOff;
        this.productUrl = productUrl;
        this.productName = productName;
    }


    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getThumbnailImageUrl() {
        return thumbnailImageUrl;
    }

    public void setThumbnailImageUrl(String thumbnailImageUrl) {
        this.thumbnailImageUrl = thumbnailImageUrl;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getStyleId() {
        return styleId;
    }

    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPercentOff() {
        return percentOff;
    }

    public void setPercentOff(String percentOff) {
        this.percentOff = percentOff;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
