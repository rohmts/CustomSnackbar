package com.wordpress.pesanmerpati.customsnackbar.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Rohmats on 3/10/2018.
 */

public class User {

    @SerializedName("login")
    private String username;
    @SerializedName("avatar_url")
    private String avatar_url;
    @SerializedName("html_url")
    private String html_url;

    public User(String username, String avatar_url, String html_url) {
        this.username = username;
        this.avatar_url = avatar_url;
        this.html_url = html_url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }
}
