package com.exemple.constrackerok.Objects;



public class Topic {

    private long idTopic;
    private String nameTopic;
    private String date;
    private String startTime;
    private String endTime;
    private int idSpeaker;
    private int idRoom;


    public Topic() {

    }

    public Topic(long idTopic, String nameTopic,  String date, String startTime, String endTime, int idSpeaker, int idRoom) {
        this.idTopic = idTopic;
        this.nameTopic = nameTopic;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.idRoom = idRoom;
        this.idSpeaker = idSpeaker;

    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getIdRoom() {
        return this.idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public int getIdSpeaker() {
        return this.idSpeaker;
    }

    public void setIdSpeaker(int idSpeaker) {
        this.idSpeaker = idSpeaker;
    }

    public long getIdTopic() {
        return this.idTopic;
    }

    public void setIdTopic(long idTopic) {
        this.idTopic = idTopic;
    }

    public String getNameTopic() {
        return nameTopic;
    }

    public void setNameTopic(String nameTopic) {
        this.nameTopic = nameTopic;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }


}

