package com.abc.hotel.enums;

public enum UserRole {

    HOTEL_OWNER("HOTEL_OWNER"),
    STANDARD_USER("STANDARD_USER");

    private String role;

    private UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
