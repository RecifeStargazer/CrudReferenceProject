@tag
Feature: API permite consultar dominios

  @tag1 @TesteCadastrarCidade
  Scenario Outline: Cadastro de cidade.
    Given Acesso a API de cadastro
    When usuario faz uma requisicao POST para o servico de cadastrar cidade com payload "<payload>"
    Then usuario deve receber um status code <status> valido

    Examples: 
      | payload	  	  | status |
      | JsonCidadeOk  |   200  | #payload valido

  @tag2
  Scenario Outline: Cadastro de Cliente.
    Given Acesso a API de cadastro
    When usuario faz uma requisicao POST para o servico de cadastrar cliente com payload "<payload>"
    Then usuario deve receber um status code <status> valido

    Examples: 
      | payload	        | status |
      | JsonClienteOk   |   200  | #payload valido
      | Json2ClienteOk  |   200  | #payload valido
      
  @tag3
  Scenario Outline: Cadastro de Cliente.
    Given Acesso a API de cadastro
    When usuario faz uma requisicao PUT para o servico de editar cliente com payload "<payload>"
    Then usuario deve receber um status code <status> valido

    Examples: 
      | payload	       		| status |
      | JsonPutClienteOk  |   200  | #payload valido
      
  @tag4
  Scenario Outline: Cadastro de Cliente.
    Given Acesso a API de cadastro
    When usuario faz uma requisicao DELETE para o servico de deletar cliente com ID <id>
    Then usuario deve receber um status code <status> valido

    Examples: 
      | Id	       				| status |
      | 2								  |   200  | #payload valido
      
  @tag5
  Scenario Outline: Consulta de Cliente.
    Given Acesso a API de consulta
    When usuario faz uma requisicao GET para o servico de buscar cliente com ID <id>
    Then usuario deve receber um status code <status> valido

    Examples: 
      | id	       				| status |
      | 2								  |   200  | #payload valido
      
  @tag6
  Scenario Outline: Consulta de Cliente.
    Given Acesso a API de consulta
    When usuario faz uma requisicao GET para o servico de buscar cliente com nome "<nome>"
    Then usuario deve receber um status code <status> valido

    Examples: 
      | nome	       					| status |
      | jocao								  |   200  | #payload valido
      
  @tag7
  Scenario Outline: Consulta de cidade.
    Given Acesso a API de consulta
    When usuario faz uma requisicao GET para o servico de buscar cidade com cidade "<cidade>"
    Then usuario deve receber um status code <status> valido

    Examples: 
      | cidade	       					  | status |
      | Recife								  |   200  | #payload valido
      
  @tag8
  Scenario Outline: Consulta de cidade.
    Given Acesso a API de consulta
    When usuario faz uma requisicao GET para o servico de buscar cidade com estado "<estado>"
    Then usuario deve receber um status code <status> valido

    Examples: 
      | estado	       					  | status |
      | Recife								  |   200  | #payload valido
      
  