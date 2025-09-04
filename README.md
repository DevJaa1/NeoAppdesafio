# NeoApp Cliente API

API REST para cadastro e gerenciamento de clientes pessoa física, desenvolvida como desafio técnico da NeoApp.

---

## Índice

- [Tecnologias](#tecnologias)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Pré-requisitos](#pré-requisitos)
- [Instalação e Execução](#instalação-e-execução)
- [Docker](#docker)
- [Endpoints](#endpoints)
- [Autenticação](#autenticação)
- [Documentação API](#documentação-api)
- [Tratamento de Erros](#tratamento-de-erros)
- [Licença](#licença)

---

## Tecnologias

- Java 17
- Spring Boot 3.2.3
- Spring Security
- Spring Data JPA
- H2 Database (em memória para testes)
- JWT (JSON Web Token)
- Swagger / OpenAPI
- Maven
- Docker

---

## Estrutura do Projeto

```
neoapp-cliente-api/
├── pom.xml
├── Dockerfile
├── src/main/java/com/neoapp/clienteapi/
│   ├── NeoappClienteApiApplication.java
│   ├── model/Cliente.java
│   ├── dto/ClienteDTO.java
│   ├── dto/ClienteUpdateDTO.java
│   ├── repository/ClienteRepository.java
│   ├── service/ClienteService.java
│   ├── controller/ClienteController.java
│   ├── security/
│   │   ├── JwtProvider.java
│   │   ├── JwtAuthenticationFilter.java
│   │   ├── UserDetailsServiceImpl.java
│   │   └── SecurityConfig.java
│   ├── config/OpenApiConfig.java
│   └── exception/GlobalExceptionHandler.java
└── src/main/resources/application.yml
```

---

## Pré-requisitos

- JDK 17+
- Maven 3.9+
- Docker (opcional)
- Git (para clonar o projeto)

---

## Instalação e Execução

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/neoapp-cliente-api.git
cd neoapp-cliente-api
```

2. Build e execução com Maven:

```bash
mvn clean package
java -jar target/cliente-api-0.0.1-SNAPSHOT.jar
```

3. A aplicação será iniciada na porta `8080`.

---

## Docker

Para rodar a aplicação via Docker:

1. Build da imagem:

```bash
docker build -t neoapp-cliente-api .
```

2. Executar o container:

```bash
docker run -p 8080:8080 neoapp-cliente-api
```

---

## Endpoints

| Método | Endpoint               | Descrição                        |
|--------|------------------------|----------------------------------|
| POST   | /api/clientes          | Criar novo cliente               |
| PUT    | /api/clientes/{id}     | Atualizar cliente existente      |
| GET    | /api/clientes          | Listar clientes (paginado/busca)|
| GET    | /api/clientes/{id}     | Buscar cliente por ID            |
| DELETE | /api/clientes/{id}     | Excluir cliente                  |

---

## Autenticação

- Todos os endpoints `/api/clientes/**` exigem autenticação JWT.
- Usuário padrão para teste:

```
Username: admin
Password: admin123
```

- Gerar token e incluir no header `Authorization`:

```
Authorization: Bearer <token>
```

---

## Documentação API

A API possui documentação Swagger acessível em:

```
http://localhost:8080/swagger-ui.html
```

---

## Tratamento de Erros

- **404 Not Found**: Quando o cliente não é encontrado.
- **500 Internal Server Error**: Para erros inesperados.
- Estrutura de retorno de erro:

```json
{
  "timestamp": "2025-09-03T20:00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Cliente não encontrado com id: 1"
}
```

---
## Hospedagem da API

A API do projeto **NeoApp** está hospedada no **Render Web Service**, uma plataforma de nuvem que permite disponibilizar aplicações web de forma segura e confiável.  

Você pode acessar a API através do seguinte link:  
[NeoApp API](https://neoappdesafio.onrender.com)
[NeoApp API Swagger](https://neoappdesafio.onrender.com/swagger-ui/index.html))

## Licença

Este projeto é para fins educacionais/desafio técnico.
