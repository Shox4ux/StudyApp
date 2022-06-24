package com.example.dictionary.Classes.Model;

import com.google.firebase.database.ServerValue;

public class Class {
    String classKey;
    String title,description,classTimes;
    String ownerId,ownerPhoto,postImg;
    Object timeStamp;

    public Class(String title, String description, String classTimes, String ownerId, String ownerPhoto, String postImg) {
        this.title = title;
        this.description = description;
        this.classTimes = classTimes;
        this.ownerId = ownerId;
        this.ownerPhoto = ownerPhoto;
        this.postImg = postImg;
        this.timeStamp = ServerValue.TIMESTAMP;
    }
    public Class(){

    }


    public String getClassKey() {
        return classKey;
    }

    public void setClassKey(String classKey) {
        this.classKey = classKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClassTimes() {
        return classTimes;
    }

    public void setClassTimes(String classTimes) {
        this.classTimes = classTimes;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerPhoto() {
        return ownerPhoto;
    }

    public void setOwnerPhoto(String ownerPhoto) {
        this.ownerPhoto = ownerPhoto;
    }

    public String getPostImg() {
        return postImg;
    }

    public void setPostImg(String postImg) {
        this.postImg = postImg;
    }

    public Object getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Object timeStamp) {
        this.timeStamp = timeStamp;
    }
}
