package com.wordpress.pesanmerpati.customsnackbar.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Rohmats on 3/16/2018.
 */

public class User {

    @SerializedName("items")
    private List<Items> itemsList;

    public List<Items> getItemsList() {
        return itemsList;
    }
}
