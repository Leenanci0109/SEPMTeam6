package com.example.foodlog;

public class Upload {
    private String user_name;
    private String image;

    public Upload() {
    }

    public Upload(String name, String imageUrl) {
        if (name.trim().equals("")) {
            name = "No Name";
        }

        user_name = name;
        image = imageUrl;
    }

    public String getName() {
        return user_name;
    }

    public void setName(String name) {
        user_name = name;
    }

    public String getImageUrl() {
        return image;
    }

    public void setImageUrl(String imageUrl) {
        image = imageUrl;
    }
}
