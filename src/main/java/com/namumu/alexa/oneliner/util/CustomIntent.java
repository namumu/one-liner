package com.namumu.alexa.oneliner.util;

public enum CustomIntent {
    SHARE_JOKE_INTENT("ShareJokeIntent"),
    RANDOM_JOKE_INTENT("RandomJokeIntent"),
    SAY_AUTHOR_NAME_INTENT("SayAuthorNameIntent"),
    SAY_JOKE_INTENT("SayJokeIntent");

    private String value;

    CustomIntent(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
