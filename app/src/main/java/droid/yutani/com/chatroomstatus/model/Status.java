package droid.yutani.com.chatroomstatus.model;

public class Status {
    public String username;
    public String status;
    public String statusMsg;

    public Status (String username, String status, String statusMsg) {
        this.username = username;
        this.status = status;
        this.statusMsg = statusMsg;
    }
}
