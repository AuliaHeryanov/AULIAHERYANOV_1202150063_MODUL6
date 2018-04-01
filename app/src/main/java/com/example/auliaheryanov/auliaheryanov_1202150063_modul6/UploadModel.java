package com.example.auliaheryanov.auliaheryanov_1202150063_modul6;

/**
 * Created by Aulia Heryanov on 01/04/2018.
 */

public class UploadModel {
    private String mTitle;
    private String mCaption;
    private String mUid;
    private String mUrl;
    private String mEmail;

    public UploadModel(){}

    public UploadModel(String title, String caption, String uid, String url, String email ){
        mTitle = title;
        mCaption = caption;
        mUid = uid;
        mUrl = url;
        mEmail = email;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmCaption() {
        return mCaption;
    }

    public void setmCaption(String mCaption) {
        this.mCaption = mCaption;
    }

    public String getmUid() {
        return mUid;
    }

    public void setmUid(String mUid) {
        this.mUid = mUid;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }
}
