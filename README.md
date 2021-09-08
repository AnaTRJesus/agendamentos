Estrutura da aplicação:

Temos três entidades:

-- Cliente
-- Serviço
-- Agendamento

Para a entidade de cliente, temos os endpoints:

-- Criação
-- Atualização
-- Exclusão
-- Consulta de um cliente por id
-- Consulta paginada de todos os clientes


Para a entidade de serviço, temos os endpoints:

-- Criação
-- Atualização
-- Exclusão
-- Consulta de um serviço por id
-- Consulta paginada de todos os serviços


Para a entidade de agendamento, temos os endpoints:

-- Criação
-- Atualização da data de agendamento
-- Consulta paginada de todos os agendamentos
-- Consulta paginada de todos os agendamentos com ordenação por data e preço do serviço

----------------------------------------------------------------------------------------------------------------------------------------

A aplicação está dockerizada e pode ser executada com os comandos:

1 - Dentro da pasta onde se encontra o arquivo Dockerfile, o seguinte comando deve ser executado:

-- docker build . 

2 - Após gerar a imagem dockerizada, o container pode ser executado com o comando:

-- sudo docker run -d -p 8080:8080 --name agendamentosapp <nomeDaImagem>

-----------------------------------------------------------------------------------------------------------------------------------------

Após a subida do container, sera possível acessar o swagger da aplicação no endereço:

-- http://localhost:8080/swagger-ui.html

Com essas configurações será possível consultar os endpoints, alguns inserts foram criados nas migrations para permitir a navegação
prévia pelos endpoints da aplicação.

-----------------------------------------------------------------------------------------------------------------------------------------

Essa aplicação foi desenvolvida com as tecnologias

-- Java 11
-- Spring Boot
-- Banco H2 In Memory
-- Maven como ferramenta de Build
-- Flyway para migrations e evolução do banco
-- Lombok para redução do código
-- Swagger para documentação








