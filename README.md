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


▶️ Como Executar o Projeto

Clone o repositório

git clone https://github.com/seu-usuario/spring-api-pizzaria.git

Configure o banco de dados MySQL

Ajuste o application.properties

Execute a aplicação

mvn spring-boot:run

A API estará disponível em:

http://localhost:8080
