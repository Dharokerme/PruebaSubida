package in.reqres.stepdefinitions.reqrespost;

import in.reqres.stepdefinitions.setup.BaseResources;
import in.reqres.util.ContentBody;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static in.reqres.question.Response.getResponse;
import static in.reqres.task.DoPost.doPost;
import static in.reqres.util.Log4jValues.LOG4J_PROPERTIES_FILE_PATH;
import static in.reqres.util.Log4jValues.USER_DIR;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.containsString;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.HashMap;


public class ReqResPostStepDefinition extends BaseResources {
    private static final Logger LOGGER = Logger.getLogger(ReqResPostStepDefinition.class);
    private final Actor actor = Actor.named("Usuario");
    private final HashMap<String, Object> headers = new HashMap<>();
    private String bodyRequest;
    private String bodyRequest2;

    @Given("que el cliente entro a la pagina.")
    public void queElClienteEntroALaPagina() {
        PropertyConfigurator.configure(USER_DIR.getValue() + LOG4J_PROPERTIES_FILE_PATH.getValue());
        actor.whoCan(CallAnApi.at(BASE_REQRES));
        headers.put("Content-Type", "application/json");
        bodyRequest = ContentBody.JSON_BODY.getValue();
        bodyRequest2 = ContentBody.JSON_BODY_WITHOUT_PASSWORD.getValue();
        LOGGER.info(bodyRequest);
        LOGGER.info(bodyRequest2);
    }

    @When("crea un usuario.")
    public void creaUnUsuario() {

        actor.attemptsTo(
                doPost().usingTheResource(POST_REGISTER_RESOURCE)
                        .withHeaders(headers)
                        .andBodyRequest(bodyRequest)
        );
    }

    @Then("obtendra un token de autenticacion.")
    public void obtendraUnTokenDeAutenticacion() {
        LastResponse.received().answeredBy(actor).prettyPrint();

        actor.should(seeThatResponse("El código de respuesta debe ser: " + HttpStatus.SC_OK,
                        validatableResponse -> validatableResponse.statusCode(HttpStatus.SC_OK)
                ),
                seeThat("El token recibido debe ser: QpwL5tke4Pnpja7X4",
                        getResponse(), containsString("QpwL5tke4Pnpja7X4"))
        );

    }

    @When("crea un usuario sin contrasena.")
    public void creaUnUsuarioSinContrasena() {
        actor.attemptsTo(
                doPost().usingTheResource(POST_REGISTER_RESOURCE)
                        .withHeaders(headers)
                        .andBodyRequest(bodyRequest2)
        );
    }

    @Then("recibira un mensaje de error")
    public void recibiraUnMensajeDeError() {
        actor.should(seeThatResponse("El código de respuesta debe ser: " + HttpStatus.SC_BAD_REQUEST,
                validatableResponse -> validatableResponse.statusCode(HttpStatus.SC_BAD_REQUEST)
//                ),
//                seeThat("El mensaje de error debe ser: Missing password",
//                        getResponse(), containsString("Missing password"))
        ));


    }
}
