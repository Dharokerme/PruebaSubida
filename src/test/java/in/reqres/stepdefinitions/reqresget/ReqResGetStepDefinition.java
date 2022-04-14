package in.reqres.stepdefinitions.reqresget;

import in.reqres.stepdefinitions.setup.BaseResources;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.questions.LastResponse;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.hamcrest.Matchers;

import java.util.HashMap;

import static in.reqres.question.Response.getResponse;
import static in.reqres.task.DoGet.doGet;
import static in.reqres.util.Log4jValues.LOG4J_PROPERTIES_FILE_PATH;
import static in.reqres.util.Log4jValues.USER_DIR;
import static in.reqres.util.Numbers.TWENTYTHREE;
import static in.reqres.util.Numbers.TWO;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.containsString;

public class ReqResGetStepDefinition extends BaseResources {

    private static final Logger LOGGER = Logger.getLogger(ReqResGetStepDefinition.class);
    private final Actor actor = Actor.named("Usuario");
    private final HashMap<String, Object> headers = new HashMap<>();

    @Given("el administrador ingresa a la pagina")
    public void elAdministradorIngresaALaPagina() {
        PropertyConfigurator.configure(USER_DIR.getValue() + LOG4J_PROPERTIES_FILE_PATH.getValue());
        actor.whoCan(CallAnApi.at(BASE_REQRES));
        headers.put("Content-Type", "application/json");
    }

    @When("el administrador ingresa el id del usuario")
    public void elAdministradorIngresaElIdDelUsuario() {

        actor.attemptsTo(
                doGet().usingTheResource(GET_RESOURCE + TWO.getValue())
                        .withHeaders(headers));

    }

    @Then("el administrador visualiza la informacion del usuario")
    public void elAdministradorVisualizaLaInformacionDelUsuario() {

        LastResponse.received().answeredBy(actor).prettyPrint();
        actor.should(seeThatResponse("El código de respuesta debe ser: " + HttpStatus.SC_OK,
                        validatableResponse -> validatableResponse.statusCode(HttpStatus.SC_OK)
                ),
                seeThat("Los datos del usuario recibido son: ",
                        getResponse(), Matchers.allOf(containsString("id"), containsString("email"), containsString("first_name"), containsString("last_name")))
        );
    }

    @When("el administrador ingresa el id del usuario inexistente")
    public void elAdministradorIngresaElIdDelUsuarioInexistente() {
        actor.attemptsTo(
                doGet().usingTheResource(GET_RESOURCE + TWENTYTHREE.getValue())
                        .withHeaders(headers));
    }

    @Then("el administrador no visualiza informacion.")
    public void elAdministradorNoVisualizaInformacion() {

        LastResponse.received().answeredBy(actor).prettyPrint();
        actor.should(seeThatResponse("El código de respuesta debe ser: " + HttpStatus.SC_NOT_FOUND,
                        validatableResponse -> validatableResponse.statusCode(HttpStatus.SC_NOT_FOUND)
                ),
                seeThat("El mensaje de respuesta debe vacio ",
                                        getResponse(), containsString("{\n" +
                                "    \n" +
                                "}"))
                );
    }
}