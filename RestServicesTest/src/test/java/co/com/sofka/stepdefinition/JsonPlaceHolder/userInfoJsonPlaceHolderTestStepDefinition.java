package co.com.sofka.stepdefinition.JsonPlaceHolder;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import static co.com.sofka.question.JsonPlaceHolder.GetUsersResponse.userResponse;
import static co.com.sofka.question.JsonPlaceHolder.UserInfoResponse.response;
import static co.com.sofka.task.DoPost.doPost;
import static co.com.sofka.task.GetUser.getUser;
import static co.com.sofka.util.FileUtilities.readFile;
import static co.com.sofka.util.Log4jValues.LOG4J_PROPERTIES_FILE_PATH;
import static co.com.sofka.util.UserJsonKeys.*;
import static co.com.sofka.util.UserInfoKeys.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.*;

import co.com.sofka.model.JsonPlaceHolder.User;

public class userInfoJsonPlaceHolderTestStepDefinition {

    private static final Logger LOGGER = Logger.getLogger(userInfoJsonPlaceHolderTestStepDefinition.class);
    private static final String USER_INFO_JSON = System.getProperty("user.dir") + "\\src\\test\\resources\\files\\JsonPlaceHolder\\userInfo.json";
    private static final String URL_BASE = "https://jsonplaceholder.typicode.com";
    private static final String RESOURCE = "/users";

    private static final String POST_ERROR = "CREATE USER NOT DONE";
    private static final String GET_ERROR = "GET USER NOT DONE";
    private static final String POST_DONE = "CREATE USER DONE SUCCESSFULLY";
    private static final String GET_DONE = "GET USER DONE SUCCESSFULLY";

    private final Actor actor = Actor.named("User");
    private User[] user;
    private String bodyRequest;


    @Given("que el usuario esta en el recurso web indicando el nombre: {string}, nombre de usuario: {string} e email: {string}")
    public void queElUsuarioEstaEnElRecursoWebIndicandoElNombreNombreDeUsuarioEEmail(String name, String username, String email) {

        PropertyConfigurator.configure(System.getProperty("user.dir")+"/"+LOG4J_PROPERTIES_FILE_PATH.getValue());
        actor.can(CallAnApi.at(URL_BASE));
        bodyRequest = defineBodyRequest(name,username,email);
        LOGGER.info(bodyRequest);
    }

    @When("el usuario genera una consulta para creacion de identificador")
    public void elUsuarioGeneraUnaConsultaParaCreacionDeIdentificador() {

        try{
            actor.attemptsTo(
                    doPost()
                            .usingTheResource(RESOURCE)
                            .andBodyRequest(bodyRequest)
            );
        }catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            LOGGER.warn(POST_ERROR);
        }
    }

    @Then("el usuario debera ver su informacion de usuario y su identificador: {string}")
    public void elUsuarioDeberaVerSuInformacionDeUsuarioYSuIdentificador(String id) {

        LastResponse.received().answeredBy(actor).prettyPrint();

        actor.should(
                seeThatResponse("El c??digo de respuesta debe ser: " + HttpStatus.SC_CREATED,
                        validatableResponse -> validatableResponse.statusCode(HttpStatus.SC_CREATED)
                ),
                seeThat("El id debe ser: " + id,
                        response(), containsString(id))
        );
        LOGGER.info(POST_DONE);
    }

    @Given("que el primer usuario esta en el recurso web para obtener su informacion de usuario")
    public void queElUsuarioEstaEnElRecursoWebParaObtenerSuUsuario() {

        PropertyConfigurator.configure(System.getProperty("user.dir")+"/"+LOG4J_PROPERTIES_FILE_PATH.getValue());
        actor.can(CallAnApi.at(URL_BASE));

    }

    @When("el primer usuario genera una consulta para obtener su informacion")
    public void elUsuarioGeneraUnaConsultaParaObtenerSuInformacion() {

        try {
            actor.attemptsTo(
                    getUser()
                            .usingTheResource(RESOURCE)
            );
        }catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            LOGGER.warn(GET_ERROR);
        }
    }

    @Then("el primer usuario debera ver su informacion de usuario")
    public void elUsuarioDeberaVerSuInformacionDeUsuario() {

        LastResponse.received().answeredBy(actor).prettyPrint();

        actor.should(
                seeThatResponse("El c??digo de respuesta debe ser: " + HttpStatus.SC_OK,
                        validatableResponse -> validatableResponse.statusCode(HttpStatus.SC_OK)
                )
        );

        user = userResponse().answeredBy(actor);

        actor.should(
                seeThat("el primer usuario no es nulo", act -> user[0], notNullValue())
        );

        actor.should(
                seeThat("el id del primer usuario", act -> user[0].getId(), equalTo(Integer.parseInt(ID_USER_ONE.getValue()))),
                seeThat("el nombre del primero usuario", act -> user[0].getName(), equalTo(NAME_USER_ONE.getValue())),
                seeThat("el nombre de usuario del primer usuario", act -> user[0].getUsername(), equalTo(USERNAME_USER_ONE.getValue())),
                seeThat("el email del primer usuario", act -> user[0].getEmail(), equalTo(EMAIL_USER_ONE.getValue()))
        );
        LOGGER.info(GET_DONE);
    }

    private String defineBodyRequest(String name, String username, String email){
        return readFile(USER_INFO_JSON)
                .replace(NAME.getValue(), name)
                .replace(USERNAME.getValue(), username)
                .replace(EMAIL.getValue(), email);
    }
}
