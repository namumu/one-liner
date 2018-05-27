package com.namumu.alexa.oneliner.util;

public enum Attributes {
    STATE_KEY("StateKey");

    private String value;

    Attributes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
