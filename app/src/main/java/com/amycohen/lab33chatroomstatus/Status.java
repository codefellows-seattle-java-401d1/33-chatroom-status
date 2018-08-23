package com.amycohen.lab33chatroomstatus;

public class Status {
    public String username;
    public String status;
    public String statusText;

    public Status (String username, String status, String statusText) {
        this.username = username;
        this.status = status;
        this.statusText = statusText;
    }
}
