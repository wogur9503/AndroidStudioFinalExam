package com.cookandroid.p2016316029_final_exam;

public class PhotoItem {
    private int photoId;
    private String message;
    public PhotoItem(){}

    public PhotoItem(int photoId, String message){
        this.photoId = photoId;
        this.message = message;
    }

    public int getPhotoId() {
        return photoId;
    }

    public String getMessage() {
        return message;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
