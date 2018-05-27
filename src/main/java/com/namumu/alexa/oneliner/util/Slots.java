package com.namumu.alexa.oneliner.util;

public enum Slots {
    AUTHOR_NAME("authorName"),
    JOKE("joke");

    private String value;

    Slots(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
