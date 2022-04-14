Feature:Ver información de un usuario

  Como administrador de la pagina
  quiero ver información de un usuario
  para mantener un registro de usuarios

  Scenario: Visualizar informacion de un usuario
    Given el administrador ingresa a la pagina
    When el administrador ingresa el id del usuario
    Then el administrador visualiza la informacion del usuario

  Scenario: Visualizar informacion de un usuario inexistente
    Given el administrador ingresa a la pagina
    When el administrador ingresa el id del usuario inexistente
    Then el administrador no visualiza informacion.