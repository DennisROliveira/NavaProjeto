Gestão de Logins 
Este é um projeto de API para gestão de usuários, onde é possível logar, listar os dados do usuário e atualizar informações. A aplicação foi construída com Spring Boot para o back-end e Angular para o front-end.

Tecnologias Utilizadas
Back-End
Spring Boot: Framework Java para construção de APIs.
Spring Data JPA: Para manipulação de dados no banco de dados.
H2 Database (ou outro banco de dados relacional): Banco de dados em memória utilizado no exemplo (você pode configurar outro, como MySQL).
Swagger: Para documentação da API.
Java 17 ou superior: Para rodar o back-end.
Front-End
Angular: Framework JavaScript para construção da interface de usuário.
HttpClientModule: Para comunicação com a API back-end.
TypeScript: Linguagem usada no Angular.
HTML e CSS: Para estruturação e estilização do layout da página.
Funcionalidades
A API permite a execução das seguintes operações:

POST - Login na aplicação
GET - Listar os dados do usuário
PUT - Atuaizar os dados do usuário


Como Rodar o Projeto
Back-End (API)
Instale o Java 17 ou superior:

Foi utilizado o JDK 21**
Você pode fazer o download do Java aqui.
Clone este repositório:

git clone -b develop https://github.com/DennisROliveira/NavaProjeto.git

======================================================================================================================== 
Para abrir o projeto Navegue até a pasta do back-end:

Se você tiver o Maven instalado, use o comando: -mvn clean install 
Utilize o comando para rodar o Spring Boot: -mvn spring-boot:run

URL API E DOCUMENTAÇÃO:
URL API: http://localhost:8080
SWAGGER: http://localhost:8080/swagger-ui.html 

========================================================================================================================

Front-End (Angular) Instale o Node.js: Navegue até a pasta do front-end:
 
No CMD Instale as dependências do Angular: -npm install 
Para rodar o front-end, use o comando: -ng serve
======================================================================================================================== 

Banco: Postgres
O banco é criado ao rodar o docker-compose up -d

conexão:
datasource.url=jdbc:postgresql://localhost:5433/pessoa_db
datasource.username=admin
datasource.password=admin

Scripts de auxílio:


CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,          
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,  
    name VARCHAR(100) NOT NULL    
);

CREATE TABLE usuario_detalhes (
    id SERIAL PRIMARY KEY,           
    usuario_id INT REFERENCES usuario(id) ON DELETE CASCADE,  
    email VARCHAR(100) UNIQUE NOT NULL,   
    cep VARCHAR(10),                -- Código postal (CEP)
    logradouro VARCHAR(255),        -- Endereço
    bairro VARCHAR(100),            -- Bairro
    cidade VARCHAR(100),            -- Cidade
    uf VARCHAR(2)                   -- Unidade federativa (UF)
);

CREATE TABLE api_log (
    id SERIAL PRIMARY KEY,
    data_created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    payload JSONB NOT NULL,   -- O corpo da requisição (novo dado)
    history JSONB,            -- O dado anterior (antes da atualização)
    requester VARCHAR(50),    -- O nome de usuário que fez a requisição
    status VARCHAR(50)        -- O status da requisição (sucesso/erro)
);

INSERT INTO usuario (username, password, name)
VALUES ('testuser', 'testpassword', 'usuario');

INSERT INTO usuario_detalhes (usuario_id, email, cep, logradouro, bairro, cidade, uf)
VALUES (1, 'usuario@example.com', '01001000', 'Rua Exemplo', 'Centro', 'São Paulo', 'SP');
