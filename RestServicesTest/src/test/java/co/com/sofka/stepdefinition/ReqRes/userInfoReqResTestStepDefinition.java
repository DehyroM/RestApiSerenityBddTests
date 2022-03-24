package co.com.sofka.stepdefinition.ReqRes;

import co.com.sofka.model.ReqRes.JobModel;
import co.com.sofka.model.ReqRes.Worker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import static co.com.sofka.question.ReqRes.UserJobResponse.userJobResponse;
import static co.com.sofka.task.DeleteUser.deleteUser;
import static co.com.sofka.task.DoPut.doPut;
import static co.com.sofka.util.FileUtilities.readFile;
import static co.com.sofka.util.Log4jValues.LOG4J_PROPERTIES_FILE_PATH;
import static co.com.sofka.util.UserJsonKeys.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.*;

public class userInfoReqResTestStepDefinition {

    private static final Logger LOGGER = Logger.getLogger(userInfoReqResTestStepDefinition.class);
    private static final String USER_UPDATE_JSON = System.getProperty("user.dir") + "\\src\\test\\resources\\files\\ReqRes\\userUpdate.json";
    private static final String URL_BASE = "https://reqres.in/api";
    private static final String RESOURCE = "/users/2";

    private static final String PUT_ERROR = "UPDATE USER NOT DONE";
    private static final String DELETE_ERROR = "DELETE USER NOT DONE";
    private static final String PUT_DONE = "UPDATE USER DONE SUCCESSFULLY";
    private static final String DELETE_DONE = "DELETE USER DONE SUCCESSFULLY";

    private final Actor actor = Actor.named("User");
    private String bodyRequest;
    private Worker worker;
    private final JobModel jobModel = new JobModel();

    @Given("que el usuario esta en el recurso web indicando un nombre y un titulo de trabajo")
    public void queElUsuarioEstaEnElRecursoWebIndicandoUnNombreYUnNombreDeTrabajo() {

        PropertyConfigurator.configure(System.getProperty("user.dir")+"/"+LOG4J_PROPERTIES_FILE_PATH.getValue());
        actor.can(CallAnApi.at(URL_BASE));
        jobModel.setUserName();
        jobModel.setJobTitle();
        bodyRequest = defineBodyRequest(jobModel.getUserName(),jobModel.getJobTitle());
        LOGGER.info(bodyRequest);

    }

    @When("el usuario genera una consulta para la actualizacion de sus datos")
    public void elUsuarioGeneraUnaConsultaParaLaActualizacionDeSusDatos() {

        try {
            actor.attemptsTo(
                    doPut()
                            .usingTheResource(RESOURCE)
                            .andBodyRequest(bodyRequest)
            );
        }catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            LOGGER.warn(PUT_ERROR);
        }
    }

    @Then("el usuario debera ver su informacion actualizada con una fecha de actualizacion")
    public void elUsuarioDeberaVerSuInformacionActualizadaConUnaFechaDeActualizacion() {

        LastResponse.received().answeredBy(actor).prettyPrint();

        worker = userJobResponse().answeredBy(actor);

        actor.should(
                seeThatResponse("El código de respuesta debe ser: " + HttpStatus.SC_OK,
                        validatableResponse -> validatableResponse.statusCode(HttpStatus.SC_OK)
                ),
                seeThat("el usuario del trabajador no es nulo", act -> worker, notNullValue()),
                seeThat("el nombre del trabajador es ", act -> worker.getName(), equalTo(jobModel.getUserName())),
                seeThat("el trabajo que desempeña es ", act -> worker.getJob(), equalTo(jobModel.getJobTitle())),
                seeThat("La informacion de actualizacion no es nula", act -> worker.getUpdatedAt(), notNullValue())
        );
        LOGGER.info(PUT_DONE);
    }

    @Given("que el usuario esta en el recurso web para el borrado de datos")
    public void queElUsuarioEstaEnElRecursoWebParaElBorradoDeDatos() {

        PropertyConfigurator.configure(System.getProperty("user.dir")+"/"+LOG4J_PROPERTIES_FILE_PATH.getValue());
        actor.can(CallAnApi.at(URL_BASE));

    }

    @When("el usuario genera una consulta para el borrado de sus datos")
    public void elUsuarioGeneraUnaConsultaParaElBorradoDeSusDatos() {

        try {
            actor.attemptsTo(
                    deleteUser()
                            .usingTheResource(RESOURCE)
            );
        }catch (Exception e){
            LOGGER.error(e.getMessage(), e);
            LOGGER.warn(DELETE_ERROR);
        }

    }

    @Then("el usuario debera ver un codigo de respuesta de borrado exitoso")
    public void elUsuarioDeberaVerUnCodigoDeRespuestaDeBorradoExitoso() {

        LastResponse.received().answeredBy(actor).prettyPrint();

        actor.should(
                seeThatResponse("El código de respuesta debe ser: " + HttpStatus.SC_NO_CONTENT,
                        validatableResponse -> validatableResponse.statusCode(HttpStatus.SC_NO_CONTENT))
        );
        LOGGER.info(DELETE_DONE);
    }

    private String defineBodyRequest(String name, String job){
        return readFile(USER_UPDATE_JSON)
                .replace(NAME.getValue(), name)
                .replace(JOB.getValue(), job);
    }

}
