package com.namumu.alexa.oneliner.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazonaws.util.StringUtils;
import com.namumu.alexa.oneliner.util.Attributes;
import com.namumu.alexa.oneliner.util.CustomIntent;
import com.namumu.alexa.oneliner.util.CustomState;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;
import static com.namumu.alexa.oneliner.util.Attributes.STATE_KEY;
import static com.namumu.alexa.oneliner.util.CustomState.SAY_AUTHOR_NAME_STATE;
import static com.namumu.alexa.oneliner.util.CustomState.SAY_JOKE_STATE;
import static com.namumu.alexa.oneliner.util.Slots.AUTHOR_NAME;

public class ShareJokeIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName(CustomIntent.SHARE_JOKE_INTENT.getValue()));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        System.out.println("Handle: " + getClass().getName());
        String speechText;
        IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();
        Map<String, Slot> slotMap = intentRequest.getIntent().getSlots();
        if (slotMap != null) {
            Slot authorNameSlot = slotMap.getOrDefault(AUTHOR_NAME, Slot.builder().build());
            if (!StringUtils.isNullOrEmpty(authorNameSlot.getValue())) {
                setState(STATE_KEY, SAY_JOKE_STATE, input);
                speechText = "Alright " + authorNameSlot.getValue() + ". tell me what you got!";
            } else {
                setState(STATE_KEY, SAY_AUTHOR_NAME_STATE, input);
                speechText = "What name would you like to use for the joke?";
            }
        } else {
            setState(STATE_KEY, SAY_AUTHOR_NAME_STATE, input);
            speechText = "What name would you like to use for the joke?";
        }

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withShouldEndSession(false)
                .build();
    }

    private void setState(Attributes stateKey, CustomState state, HandlerInput input) {
        Map<String, Object> sessionAttributes = input.getAttributesManager().getSessionAttributes();
        sessionAttributes.put(stateKey.getValue(), state.getValue());
    }
}