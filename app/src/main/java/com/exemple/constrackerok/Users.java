package com.exemple.constrackerok;

public class Users {

    private int idUser;
    private String title;
    private String name;
    private String surname;
    private String tel;
    private String email;
    private String password;


    public Users() {
    }

    public Users(int idUser, String title, String name, String surname, String tel, String email, String password) {
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

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }


}
