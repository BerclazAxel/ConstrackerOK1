package com.exemple.constrackerok.Objects;

import java.util.Date;


public class Topic {

    private int idTopic;
    private String nameTopic;
    private String startTime;
    private String endTime;
    private String date;
    private int idRoom;
    private int idSpeaker;

    public Topic() {

    }

    public Topic(int idTopic, String nameTopic,  String date, String startTime, String endTime, int idRoom, int idSpeaker) {
        this.idTopic = idTopic;
        this.nameTopic = nameTopic;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.idRoom = idRoom;
        this.idSpeaker = idSpeaker;

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public int getIdSpeaker() {
        return idSpeaker;
    }

    public void setIdSpeaker(int idSpeaker) {
        this.idSpeaker = idSpeaker;
    }

    public int getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(int idTopic) {
        this.idTopic = idTopic;
    }

    public String getNameTopic() {
        return nameTopic;
    }

    public void setNameTopic(String nameTopic) {
        this.nameTopic = nameTopic;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }


}

