package co.com.sofka.question.rest;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

import java.nio.charset.StandardCharsets;

public class UserInfoResponse implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return new String(LastResponse.received().answeredBy(actor).asByteArray(), StandardCharsets.UTF_8);
    }

    public static UserInfoResponse response(){
        return new UserInfoResponse();
    }
}

