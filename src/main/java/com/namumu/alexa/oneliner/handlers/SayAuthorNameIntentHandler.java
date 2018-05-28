package com.namumu.alexa.oneliner.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazonaws.util.StringUtils;
import com.namumu.alexa.oneliner.util.Attributes;
import com.namumu.alexa.oneliner.util.CustomState;

import java.util.Map;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.sessionAttribute;
import static com.namumu.alexa.oneliner.util.Attributes.STATE_KEY;
import static com.namumu.alexa.oneliner.util.CustomState.SAY_AUTHOR_NAME_STATE;
import static com.namumu.alexa.oneliner.util.CustomState.SAY_JOKE_STATE;
import static com.namumu.alexa.oneliner.util.Slots.AUTHOR_NAME;

public class SayAuthorNameIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(sessionAttribute(STATE_KEY.getValue(), SAY_AUTHOR_NAME_STATE.getValue()));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText;

        IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();
        Map<String, Slot> slotMap = intentRequest.getIntent().getSlots();
        if (slotMap != null) {
            Slot authorNameSlot = slotMap.getOrDefault(AUTHOR_NAME.getValue(), Slot.builder().build());
            if (authorNameSlot != null && !StringUtils.isNullOrEmpty(authorNameSlot.getValue())) {
                setState(STATE_KEY, SAY_JOKE_STATE, input);
                speechText = "Alright " + authorNameSlot.getValue() + ". Tell me what you got!";
            } else {
                speechText = "Sorry, could you please repeat that?";
            }
        } else {
            speechText = "Sorry, could you please repeat that?";
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