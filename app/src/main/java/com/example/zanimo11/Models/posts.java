package com.example.zanimo11.Models;

import java.util.List;

public class posts {

    private String _id, image, date, description, dateExpiration, genreAnimale, typeAnimale, typeAnnonce;
    private List<Comments> comments;

    public posts() {
    }

    public posts(String image, String date, String description, String dateExpiration, String genreAnimale, String typeAnimale, String typeAnnonce) {
        this.image = image;
        this.date = date;
        this.description = description;
        this.dateExpiration = dateExpiration;
        this.genreAnimale = genreAnimale;
        this.typeAnimale = typeAnimale;
        this.typeAnnonce = typeAnnonce;
        this.comments = comments;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(String dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public String getGenreAnimale() {
        return genreAnimale;
    }

    public void setGenreAnimale(String genreAnimale) {
        this.genreAnimale = genreAnimale;
    }

    public String getTypeAnimale() {
        return typeAnimale;
    }

    public void setTypeAnimale(String typeAnimale) {
        this.typeAnimale = typeAnimale;
    }

    public String getTypeAnnonce() {
        return typeAnnonce;
    }

    public void setTypeAnnonce(String typeAnnonce) {
        this.typeAnnonce = typeAnnonce;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }
}
