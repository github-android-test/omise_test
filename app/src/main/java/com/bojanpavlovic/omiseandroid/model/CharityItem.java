package com.bojanpavlovic.omiseandroid.model;

import com.google.gson.annotations.SerializedName;

public class CharityItem {
    @SerializedName("id")
    private long id;
    @SerializedName("name")
    private String name;
    @SerializedName("logo_url")
    private String logoUrl;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
