package com.namumu.alexa.oneliner;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.amazon.ask.SkillStreamHandler;

import com.namumu.alexa.oneliner.handlers.*;

public class OneLinerStreamHandler extends SkillStreamHandler {
    public OneLinerStreamHandler() {
        super(getSkill());
    }

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(new CancelandStopIntentHandler(), new RandomJokeIntentHandler(), new ShareJokeIntentHandler(), new SayAuthorNameIntentHandler(), new SayJokeIntentHandler(), new HelpIntentHandler(), new LaunchRequestHandler(), new SessionEndedRequestHandler())
                .build();
    }
}
