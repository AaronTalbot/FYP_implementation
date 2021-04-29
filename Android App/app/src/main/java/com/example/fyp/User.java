package com.example.fyp;

import java.io.Serializable;

public class User implements Serializable {
    private String Fname;
    private String Lname;
    private String Email;
    private String UID;

    public User() {
    }

    public User(String fname, String lname, String email, String UID) {
        Fname = fname;
        Lname = lname;
        Email = email;
        this.UID = UID;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }
}
