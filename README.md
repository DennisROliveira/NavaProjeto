# 🧑‍💻 Gestão de Logins

Este é um projeto full stack para **gestão de usuários**, com funcionalidades como login, listagem e atualização de dados. A aplicação foi construída com **Spring Boot** no back-end e **Angular** no front-end.

---

## 🚀 Tecnologias Utilizadas

### 🔧 Back-End
- **Spring Boot**: Framework Java para construção de APIs REST.
- **Spring Data JPA**: Manipulação de dados com ORM.
- **PostgreSQL** (via Docker): Banco de dados relacional.
- **Swagger**: Documentação interativa da API.
- **Java 17+** (foi utilizado o **JDK 21**).

### 💻 Front-End
- **Angular**: Framework para criação de SPAs.
- **HttpClientModule**: Comunicação com o back-end.
- **TypeScript**, **HTML** e **CSS**: Desenvolvimento da interface.

---

## ✅ Funcionalidades

- `POST` - Login do usuário  
- `GET` - Listar dados do usuário  
- `PUT` - Atualizar informações do usuário  

---

## 🛠️ Como Rodar o Projeto

### 🔙 Back-End (Spring Boot)

1. Instale o **Java 17 ou superior**  
   > Foi utilizado o **JDK 21**  
   [Download do Java](https://www.oracle.com/java/technologies/javase-downloads.html)

2. Clone o repositório:
   ```bash
   git clone -b develop https://github.com/DennisROliveira/NavaProjeto.git
   ```

3. Acesse a pasta do projeto back-end e execute:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. Acesse a API:
   - URL: `http://localhost:8080`
   - Swagger: `http://localhost:8080/swagger-ui.html`

---

### 🔜 Front-End (Angular)

1. Instale o **Node.js** e o **Angular CLI**:
   ```bash
   npm install -g @angular/cli
   ```

2. Acesse a pasta do front-end:
   ```bash
   cd caminho/para/o/front-end
   ```

3. Instale as dependências:
   ```bash
   npm install
   ```

4. Rode a aplicação:
   ```bash
   ng serve
   ```

5. Acesse no navegador:  
   `http://localhost:4200`

---

## 🛢️ Banco de Dados (PostgreSQL via Docker)

O banco de dados é criado automaticamente com:

```bash
docker-compose up -d
```

### 🔌 Configuração da Conexão

```properties
spring.datasource.url=jdbc:postgresql://localhost:5433/pessoa_db
spring.datasource.username=admin
spring.datasource.password=admin
```

---

## 📄 Scripts de Apoio

```sql
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
    cep VARCHAR(10),
    logradouro VARCHAR(255),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    uf VARCHAR(2)
);

CREATE TABLE api_log (
    id SERIAL PRIMARY KEY,
    data_created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    payload JSONB NOT NULL,
    history JSONB,
    requester VARCHAR(50),
    status VARCHAR(50)
);

-- Dados iniciais:
INSERT INTO usuario (username, password, name)
VALUES ('testuser', 'testpassword', 'usuario');

INSERT INTO usuario_detalhes (usuario_id, email, cep, logradouro, bairro, cidade, uf)
VALUES (1, 'usuario@example.com', '01001000', 'Rua Exemplo', 'Centro', 'São Paulo', 'SP');
```

---

