package com.e.assignment1;

public class ResultHelperClass
{
    String Surname,Name,Phone,Date,Age,Food;
    String ILikeToEatOut,ILikeToWatchMovies,ILikeToWatchTV,ILikeToListenToRadio;

    public ResultHelperClass()
    {

    }

    public ResultHelperClass(String surname, String name, String phone, String date, String age, String food, String ILikeToEatOut, String ILikeToWatchMovies, String ILikeToWatchTV, String ILikeToListenToRadio) {
        Surname = surname;
        Name = name;
        Phone = phone;
        Date = date;
        Age = age;
        Food = food;
        this.ILikeToEatOut = ILikeToEatOut;
        this.ILikeToWatchMovies = ILikeToWatchMovies;
        this.ILikeToWatchTV = ILikeToWatchTV;
        this.ILikeToListenToRadio = ILikeToListenToRadio;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getFood() {
        return Food;
    }

    public void setFood(String food) {
        Food = food;
    }

    public String getILikeToEatOut() {
        return ILikeToEatOut;
    }

    public void setILikeToEatOut(String ILikeToEatOut) {
        this.ILikeToEatOut = ILikeToEatOut;
    }

    public String getILikeToWatchMovies() {
        return ILikeToWatchMovies;
    }

    public void setILikeToWatchMovies(String ILikeToWatchMovies) {
        this.ILikeToWatchMovies = ILikeToWatchMovies;
    }

    public String getILikeToWatchTV() {
        return ILikeToWatchTV;
    }

    public void setILikeToWatchTV(String ILikeToWatchTV) {
        this.ILikeToWatchTV = ILikeToWatchTV;
    }

    public String getILikeToListenToRadio() {
        return ILikeToListenToRadio;
    }

    public void setILikeToListenToRadio(String ILikeToListenToRadio) {
        this.ILikeToListenToRadio = ILikeToListenToRadio;
    }
}
