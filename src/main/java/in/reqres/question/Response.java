package in.reqres.question;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.rest.questions.LastResponse;

public class Response implements Question {
    @Override
    public String answeredBy(Actor actor) {
        return new String(LastResponse.received().answeredBy(actor).asString());
    }

    public static Response getResponse() {
        return new Response();
    }
}
