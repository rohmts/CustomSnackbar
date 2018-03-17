package com.wordpress.pesanmerpati.customsnackbar.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Rohmats on 3/10/2018.
 */

public class Items {

    @SerializedName("login")
    private String username;

    private String avatar_url;

    private String html_url;

    public Items(String username, String avatar_url, String html_url) {
        this.username = username;
        this.avatar_url = avatar_url;
        this.html_url = html_url;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getHtml_url() {
        return html_url;
    }
}
