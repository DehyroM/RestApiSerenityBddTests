package co.com.sofka.question.JsonPlaceHolder;

import co.com.sofka.model.JsonPlaceHolder.User;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class GetUsersResponse implements Question <User[]>{

    @Override
    public User[] answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(User[].class);
    }

    public static GetUsersResponse userResponse(){
        return new GetUsersResponse();
    }
}
