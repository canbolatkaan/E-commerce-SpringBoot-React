package com.godoro.marketapp.enums;

public enum CartStatus {
    NEW("NEW"),
    COMPLETED("COMPLETED");

    private String value;

    CartStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}