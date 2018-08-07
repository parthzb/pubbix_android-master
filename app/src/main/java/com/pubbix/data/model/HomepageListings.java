
package com.pubbix.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomepageListings {

    @SerializedName("statusCode")
    @Expose
    public int statusCode;
    @SerializedName("categories")
    @Expose
    public List<Category> categories = null;

}
