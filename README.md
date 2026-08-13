# 🍕 Spring API Pizzaria

API REST desenvolvida em **Java com Spring Boot**, com o tema de **pizzaria**, implementando **CRUD completo** e consumo dos principais **verbos HTTP (GET, POST, PUT, DELETE)**.

O projeto foi criado **para fins de estudo**, com foco em boas práticas de arquitetura, organização em camadas e segurança com JWT chave (PRIVADA e PUBLICA).

---

## 🚀 Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- OAuth2 Resource Server
- JWT (RS256)
- MySQL
- Maven

---

## 🔐 Segurança

O projeto utiliza **Spring Security** com **OAuth2 Resource Server**, implementando autenticação baseada em **JWT**.

- Tokens JWT assinados com **RSA (RS256)**
- Endpoint de login para geração do token
- Endpoints protegidos via `Authorization: Bearer <token>`

⚠️ **Atenção:**  
As chaves RSA estão versionadas **apenas para fins de estudo**.  
**Nunca utilize este padrão em produção.**

---

## 📦 Funcionalidades

- Cadastro de usuários
- Autenticação com JWT
- CRUD de clientes
- CRUD de pizzas
- CRUD de pedidos
- Relacionamento entre cliente, pedido e pizza
- Persistência com banco de dados relacional

---

## 🧱 Estrutura da API

A aplicação segue uma separação em camadas:

- `controller` – endpoints REST
- `service` – regras de negócio
- `repository` – acesso ao banco de dados
- `domain/entity` – entidades JPA
- `domain/dto` – objetos de transferência de dados
- `config` – configurações de segurança

---

## 📄 Exemplos de JSON (Requests)

### 👤 Usuario
```json
{
  "name": "Elevir",
  "password": "123"
}



🍕 Pizza
{
  "nome": "Pizzaria Now",
  "descricao": "Pizza de calabresa com cebola e azeitona"
}



🧾 Pedido
{
  "dataPedido": "2025-10-16T18:30:00",
  "statusPedido": "EM_PREPARO",
  "pizza": {
    "id": 1
  },
  "cliente": {
    "id": 2
  }
}


👥 Cliente
{
  "nome": "Elevir.Junior7",
  "telefone": "58315000",
  "endereco": {
    "rua": "Rua das Flores",
    "numero": "123",
    "bairro": "Gramado",
    "cidade": "Gramado",
    "estado": "SC",
    "cep": "444422"
  }
}


## ▶️ Como Executar o Projeto

### 1. Clone o projeto Producer

```bash
git clone https://github.com/ElevirJJ/Spring_API-Pizza.git
cd Spring_API-Pizza
```

### 2. Suba os containers com Docker

Certifique-se de que o Docker esteja em execução e execute:

```bash
docker compose up -d
```

Esse comando irá subir os serviços necessários para o projeto, incluindo o RabbitMQ.

### 3. Clone o projeto Consumer

Em outro diretório, clone o projeto responsável por consumir as mensagens do RabbitMQ:

```bash
git clone https://github.com/ElevirJJ/Consume_RabbitMQ.git
cd Consume_RabbitMQ
```

### 4. Configure o banco de dados

Ajuste as configurações do banco de dados no arquivo:

```text
src/main/resources/application.properties
```

### 5. Execute a aplicação Producer

No projeto principal, execute:

```bash
mvn spring-boot:run
```

### 6. Execute o Consumer

No projeto `Consume_RabbitMQ`, execute:

```bash
mvn spring-boot:run
```

### 🌐 Acesso à API

A API estará disponível em:

```text
http://localhost:8081
```

### 🐰 RabbitMQ

Com o Docker em execução, o RabbitMQ estará disponível para comunicação entre o **Producer** e o **Consumer**.

O projeto utiliza o RabbitMQ para implementar a comunicação assíncrona entre as aplicações, onde o **Producer** envia os eventos e o **Consumer** recebe e processa essas mensagens.

