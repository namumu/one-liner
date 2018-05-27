package com.namumu.alexa.oneliner.util;

public enum CustomState {
    SAY_JOKE_STATE("SayJokeState"),
    SAY_AUTHOR_NAME_STATE("SayAuthorNameState"),
    CONFIRM_AUTHOR_NAME_STATE("ConfirmAuthorNameState");

    private String value;

    CustomState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
