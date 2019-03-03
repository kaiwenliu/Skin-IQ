package com.neelk.pioneerhacks;

public class Disease {

    private int resId;
    private String name;

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

    public Disease(String name, int resId){
        this.name = name;
        this.resId = resId;

    }
}
