package co.com.sofka.task;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Put;

public class DoPut implements Task {

    private String resource;
    private String bodyRequest;

    public DoPut usingTheResource(String resource) {
        this.resource = resource;
        return this;
    }

    public DoPut andBodyRequest(String bodyRequest) {
        this.bodyRequest = bodyRequest;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Put.to(resource)
                        .with(
                                requestSpecification -> requestSpecification.relaxedHTTPSValidation()
                                        .contentType(ContentType.JSON)
                                        .body(bodyRequest)
                        )
        );
    }

    public static DoPut doPut(){
        return new DoPut();
    }

}
