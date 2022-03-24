Feature: Información de usuario
  Como usuario
  Necesito poder acceder a mi usuario
  Para poder manipular mi información de contacto

  Scenario: Creacion de identificador de usuario
    Given que el usuario esta en el recurso web indicando el nombre: "Pepito Perez", nombre de usuario: "Pepito" e email: "pepito_perez@gmail.com"
    When el usuario genera una consulta para creacion de identificador
    Then el usuario debera ver su informacion de usuario y su identificador: "11"

  Scenario: obtener información del primer usuario
    Given que el primer usuario esta en el recurso web para obtener su informacion de usuario
    When el primer usuario genera una consulta para obtener su informacion
    Then el primer usuario debera ver su informacion de usuario
