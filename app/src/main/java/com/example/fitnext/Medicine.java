package com.example.fitnext;

public class Medicine {
    private String name;
    private String avatarImageUrl;
    private String websiteLink;

    public Medicine(String name, String avatarImageUrl, String websiteLink) {
        this.name = name;
        this.avatarImageUrl = avatarImageUrl;
        this.websiteLink = websiteLink;
    }

    public String getName() {
        return name;
    }

    public String getAvatarImageUrl() {
        return avatarImageUrl;
    }

    public String getWebsiteLink() {
        return websiteLink;
    }
}
