package co.com.sofka.question.ReqRes;

import co.com.sofka.model.ReqRes.Worker;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class UserJobResponse implements Question<Worker> {
    @Override
    public Worker answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(Worker.class);
    }

    public static UserJobResponse userJobResponse(){
        return new UserJobResponse();
    }
}
