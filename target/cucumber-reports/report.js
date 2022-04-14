$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/features/reqres/reqres-get.feature");
formatter.feature({
  "name": "Ver información de un usuario",
  "description": "  Como administrador de la pagina\n  quiero ver información de un usuario\n  para mantener un registro de usuarios",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "Visualizar informacion de un usuario",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "el administrador ingresa a la pagina",
  "keyword": "Given "
});
formatter.match({
  "location": "co.com.reqres.stepdefinitions.reqresget.ReqResGetStepDefinition.elAdministradorIngresaALaPagina()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "el administrador ingresa el id del usuario",
  "keyword": "When "
});
formatter.match({
  "location": "co.com.reqres.stepdefinitions.reqresget.ReqResGetStepDefinition.elAdministradorIngresaElIdDelUsuario()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "el administrador visualiza la informacion del usuario",
  "keyword": "Then "
});
formatter.match({
  "location": "co.com.reqres.stepdefinitions.reqresget.ReqResGetStepDefinition.elAdministradorVisualizaLaInformacionDelUsuario()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Visualizar informacion de un usuario inexistente",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "el administrador ingresa a la pagina",
  "keyword": "Given "
});
formatter.match({
  "location": "co.com.reqres.stepdefinitions.reqresget.ReqResGetStepDefinition.elAdministradorIngresaALaPagina()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "el administrador ingresa el id del usuario inexistente",
  "keyword": "When "
});
formatter.match({
  "location": "co.com.reqres.stepdefinitions.reqresget.ReqResGetStepDefinition.elAdministradorIngresaElIdDelUsuarioInexistente()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "el administrador no visualiza informacion.",
  "keyword": "Then "
});
formatter.match({
  "location": "co.com.reqres.stepdefinitions.reqresget.ReqResGetStepDefinition.elAdministradorNoVisualizaInformacion()"
});
formatter.result({
  "status": "passed"
});
});