Feature: Manipulacion de usuario
  Como usuario de un servicio
  Quiero poder manipular mis datos
  Para actualizarlos o borrarlos

  Scenario: Actualizacion de datos
    Given que el usuario esta en el recurso web indicando un nombre y un titulo de trabajo
    When el usuario genera una consulta para la actualizacion de sus datos
    Then el usuario debera ver su informacion actualizada con una fecha de actualizacion

  Scenario: Borrado de datos
    Given que el usuario esta en el recurso web para el borrado de datos
    When el usuario genera una consulta para el borrado de sus datos
    Then el usuario debera ver un codigo de respuesta de borrado exitoso