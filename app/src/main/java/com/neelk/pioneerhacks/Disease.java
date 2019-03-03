package com.neelk.pioneerhacks;

public class Disease {

    private int resId;
    private String name;
    private String url;

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Disease(String name, int resId, String url){
        this.name = name;
        this.resId = resId;
        this.url =url;

    }
}
