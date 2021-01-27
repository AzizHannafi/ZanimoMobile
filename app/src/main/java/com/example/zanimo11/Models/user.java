package com.example.zanimo11.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class user {

    private String _id;
    private String imageuser;
    private boolean status;
    @SerializedName("nom")
    @Expose
    private String nom;

    @SerializedName("prenom")
    @Expose
    private String prenom;

    @SerializedName("mail")
    @Expose
    private String mail;

    @SerializedName("numtel")
    @Expose
    private String numtel;

    @SerializedName("motdepasse")
    @Expose
    private String motdepasse;

    private List<posts> posts;

    public user() {
    }

    public user(String nom, String prenom, String mail, String motdepasse, String numtel) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.motdepasse = motdepasse;
        this.numtel = numtel;

    }

    public user(String nom, String prenom, String imageuser, String mail, String numtel, String motdepasse, boolean status, List<com.example.zanimo11.Models.posts> posts) {
        this.nom = nom;
        this.prenom = prenom;
        this.imageuser = imageuser;
        this.mail = mail;
        this.numtel = numtel;
        this.motdepasse = motdepasse;
        this.status = status;
        this.posts = posts;
    }

    public List<com.example.zanimo11.Models.posts> getPosts() {
        return posts;
    }

    public String getImageuser() {
        return imageuser;
    }

    public void setImageuser(String imageuser) {
        this.imageuser = imageuser;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public void setPosts(List<com.example.zanimo11.Models.posts> posts) {
        this.posts = posts;
    }
}
