package com.example.Daria.myapplication.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy=	GenerationType.IDENTITY)
    private long idUser;
    private String title;
    private String name;
    private String surname;
    private String tel;
    private String email;
    private String password;

    public User() {
    }

    public User(long idUser, String title, String name, String surname, String tel, String email, String password) {
        this.idUser = idUser;
        this.title = title;
        this.name = name;
        this.surname = surname;
        this.tel = tel;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }


}