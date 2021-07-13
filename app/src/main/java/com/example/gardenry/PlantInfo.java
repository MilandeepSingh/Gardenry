package com.example.gardenry;

public class PlantInfo {
    private String name;
    private String sciName;
    private boolean indoor, ornamental, productive;

    public PlantInfo(String name, String sciName, boolean indoor, boolean ornamental, boolean productive) {
        this.name = name;
        this.sciName = sciName;
        this.indoor = indoor;
        this.ornamental = ornamental;
        this.productive = productive;
    }

    public boolean isProductive() {
        return productive;
    }

    public void setProductive(boolean productive) {
        this.productive = productive;
    }

    public PlantInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSciName() {
        return sciName;
    }

    public void setSciName(String sciName) {
        this.sciName = sciName;
    }

    public boolean isIndoor() {
        return indoor;
    }

    public void setIndoor(boolean indoor) {
        this.indoor = indoor;
    }

    public boolean isOrnamental() {
        return ornamental;
    }

    public void setOrnamental(boolean ornamental) {
        this.ornamental = ornamental;
    }

}