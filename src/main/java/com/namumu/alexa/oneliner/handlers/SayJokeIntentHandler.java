package com.namumu.alexa.oneliner.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.sessionAttribute;
import static com.namumu.alexa.oneliner.util.Attributes.STATE_KEY;
import static com.namumu.alexa.oneliner.util.CustomState.SAY_JOKE_STATE;
import static com.namumu.alexa.oneliner.util.Slots.JOKE;

public class SayJokeIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(sessionAttribute(STATE_KEY.getValue(), SAY_JOKE_STATE.getValue()));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();
        Slot jokeSlot = intentRequest.getIntent().getSlots().get(JOKE.getValue());
        String speechText = "Your joke was.<prosody pitch=\"x-high\">" + jokeSlot.getValue() + "</prosody>." + "Would you like to submit your joke?";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();
    }
}