package com.example.chatroomstatus.models;

public class Status {
    public enum STATUS {
        ONLINE,
    }

    public String username;
    public String status; // One of "Online", "Away", "Offline"
    public String statusText; // "I'm out to lunch"

    public Status(String username, String status, String statusText) {
        this.username = username;
        this.status = status;
        this.statusText = statusText;
    }
}
