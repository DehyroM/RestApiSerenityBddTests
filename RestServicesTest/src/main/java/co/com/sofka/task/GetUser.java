package co.com.sofka.task;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class GetUser implements Task {

    private String resource;

    public GetUser usingTheResource(String resource) {
        this.resource = resource;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                Get.resource(resource)
                        .with(requestSpecification
                                -> requestSpecification
                                .relaxedHTTPSValidation()
                                .contentType(ContentType.JSON)
                        )
        );
    }
    public static GetUser getUser(){
        return new GetUser();
    }
}
