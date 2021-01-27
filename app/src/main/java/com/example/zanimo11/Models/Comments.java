package com.example.zanimo11.Models;

public class Comments {
    private String _id,comment;

    public Comments() {
    }

    public Comments(String comment) {
        this.comment = comment;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
