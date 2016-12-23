package com.example.Daria.myapplication.backend;


import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Entity
public class Room {
    @Id
    @GeneratedValue(strategy=	GenerationType.IDENTITY)
    private int idRoom;
    private String nameRoom;
    private int nbPeople;


    public Room() {
    }


    public Room(int idRoom, String nameRoom, int nbPeople) {
        this.idRoom = idRoom;
        this.nameRoom = nameRoom;
        this.nbPeople = nbPeople;


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


}
