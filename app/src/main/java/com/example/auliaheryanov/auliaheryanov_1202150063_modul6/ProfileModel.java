package com.example.auliaheryanov.auliaheryanov_1202150063_modul6;

/**
 * Created by Aulia Heryanov on 01/04/2018.
 */

public class ProfileModel {
    private String mTitle;
    private String mCaption;
    private String mUid;
    private String mUrl;
    private String mEmail;

    public ProfileModel(){}

    public ProfileModel(String title, String caption, String uid, String url, String email ){
        mTitle = title;
        mCaption = caption;
        mUid = uid;
        mUrl = url;
        mEmail = email;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmCaption(String mCaption) {
        this.mCaption = mCaption;
    }

    public void setmUid(String mUid) {
        this.mUid = mUid;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmCaption() {
        return mCaption;
    }

    public String getmUid() {
        return mUid;
    }

    public String getmUrl() {
        return mUrl;
    }

    public String getmEmail() {
        return mEmail;
    }
}

