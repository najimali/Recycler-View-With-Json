package com.example.nazi.recyclerviewjsonex;

/**
 * Created by Nazi on 5/20/2019.
 */

public class ElementItem {
    private String mUrl;
    private String mCreatorText;
    private int mLikes;

    public String getmUrl() {
        return mUrl;
    }

    public String getmCreatorText() {
        return mCreatorText;
    }

    public int getmLikes() {
        return mLikes;
    }

    public ElementItem(String mUrl, String mText, int mLikes) {

        this.mUrl = mUrl;
        this.mCreatorText = mText;
        this.mLikes = mLikes;
    }
}
