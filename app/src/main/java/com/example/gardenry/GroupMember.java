package com.example.gardenry;

public class GroupMember {
    int dp;
    String name;
    String email;

    public GroupMember(int dp, String name, String email) {
        this.dp = dp;
        this.name = name;
        this.email = email;
    }

    public int getDp() {
        return dp;
    }

    public void setDp(int dp) {
        this.dp = dp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
