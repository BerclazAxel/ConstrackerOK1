package com.exemple.constrackerok.Objects;


public class Room {
    private long idRoom;
    private String nameRoom;
    private int nbPeople;


    public Room() {
    }


    public Room(long idRoom, String nameRoom, int nbPeople) {
        this.idRoom = idRoom;
        this.nameRoom = nameRoom;
        this.nbPeople = nbPeople;


    }

    public long getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(long idRoom) {
        this.idRoom = idRoom;
    }

    public String getNameRoom() {
        return nameRoom;
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    public int getNbPeople() {
        return nbPeople;
    }

    public void setNbPeople(int nbPeople) {
        this.nbPeople = nbPeople;
    }




}
