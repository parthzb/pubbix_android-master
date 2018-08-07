package com.pubbix.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category {

    @SerializedName("categoryId")
    @Expose
    public String categoryId;
    @SerializedName("categoryParentId")
    @Expose
    public String categoryParentId;
    @SerializedName("categoryName")
    @Expose
    public String categoryName;
    @SerializedName("callToAction")
    @Expose
    public String callToAction;
    @SerializedName("subCategories")
    @Expose
    public List<SubCategory> subCategories = null;
    @SerializedName("listings")
    @Expose
    public List<Listing> listings = null;

}
