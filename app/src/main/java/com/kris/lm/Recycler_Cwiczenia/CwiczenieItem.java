package com.kris.lm.Recycler_Cwiczenia;

/**
 * Created by Kris on 2015-05-16.
 */
public class CwiczenieItem {
    private String mName;
    private String mDes;
    private int mThumbnail;
    private int mDifficulty;


    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getDes() {
        return mDes;
    }

    public void setDes(String des) {
        this.mDes = des;
    }

    public int getThumbnail() {
        return mThumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.mThumbnail = thumbnail;
    }

    public int getmDifficulty() {
        return mDifficulty;
    }

    public void setmDifficulty(int difficulty) {
        this.mDifficulty = difficulty;
    }

}
