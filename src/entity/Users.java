package entity;

import utiles.common.SubType;

public class Users {
    private String name;
    private String email;
    private SubType subType;

    public Users(String name, String email, SubType subType) {
        this.name = name;
        this.email = email;
        this.subType = subType;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public SubType getSubType() {
        return subType;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Email: " + email + ", Subscription: " + subType;
    }
}
