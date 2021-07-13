package com.example.gardenry;

public class Request {
    String timestamp;
    String name;
    String desc;
    String remarks;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Request(String name, String desc, String timestamp) {
        this.name = name;
        this.desc = desc;
        this.timestamp = timestamp;
        this.remarks = "";
    }

    public Request() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
