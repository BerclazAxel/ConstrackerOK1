package com.exemple.constrackerok.Objects;


public class Room {
    private int idRoom;
    private String nameRoom;
    private int nbPeople;
    private String city;

    public Room() {
    }


    public Room(int idRoom, String nameRoom, int nbPeople, String city) {
        this.idRoom = idRoom;
        this.nameRoom = nameRoom;
        this.nbPeople = nbPeople;
        this.city = city;

    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


}
