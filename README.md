# 🛍️ API E-commerce (Projeto Final - Parte em Grupo)

Esta é a API RESTful desenvolvida em **Java** com **Spring Boot** para o projeto final de E-commerce. A API segue o **Padrão em Camadas (Controller, Service, Repository)** e implementa todas as funcionalidades e requisitos solicitados para a parte em grupo (40 pontos).

## 🚀 Requisitos e Funcionalidades Implementadas

O projeto implementa os seguintes recursos e funcionalidades, conforme o documento:

### ⚙️ Arquitetura e Estrutura

  - **Padrão em Camadas:** Utilização de `Controller`, `Service`, `Repository`, `Entity` e `DTO`.
  - **CRUD Completo:** Implementação de `GET`, `POST`, `PUT` e `DELETE` para todas as entidades principais.
  - **Tratamento de Exceções:** Classe global `@ControllerAdvice` para retorno de erros padronizados (404 Not Found, 400 Bad Request, etc.).

### 🛒 Entidades e Regras de Negócio

| Entidade | Funcionalidades Específicas | Status |
| :--- | :--- | :--- |
| **Categoria** | CRUD simples. | ✅ |
| **Produto** | Relacionamento obrigatório com Categoria. | ✅ |
| **Cliente** | CRUD com paginação. | ✅ |
| **Pedido** | **Relacionamento N:N** via `ItemPedido` (com valor, desconto e quantidade). | ✅ |
| **Totalização** | Método para calcular o valor total do pedido (`valorVenda * quantidade - desconto`). | ✅ |
| **Status Pedido** | Utilização de `Enum` (`AGUARDANDO_PAGAMENTO`, `PAGO`, etc.) com tratamento de erro na atualização. | ✅ |

### 🔑 Requisitos Técnicos

  - **Paginação:** Recurso de paginação (`/clientes` e `/produtos`) implementado via `Pageable`.
  - **Serviço Externo (ViaCEP)**: *Lógica implementada no `ClienteService` (requer descomentar código).*
  - **Envio de E-mail:** *Lógica implementada no `ClienteService` (requer configuração de `spring.mail` e descomentar código).*
  - **Documentação:** Utilização do **SpringDoc OpenAPI (Swagger)**.
  - **Autenticação:** *Configuração básica de segurança implementada (pronto para receber JWT).*

-----

## 🛠️ Tecnologias Utilizadas

  - **Linguagem:** Java
  - **Framework:** Spring Boot
  - **Banco de Dados:** H2 (padrão para desenvolvimento/testes, configurável para PostgreSQL, MySQL, etc.)
  - **Acesso a Dados:** Spring Data JPA / Hibernate
  - **Utilidades:** Lombok (para simplificar Entidades e DTOs)
  - **Documentação:** SpringDoc OpenAPI
  - **Segurança:** Spring Security / JWT (estrutura pronta)

-----

## 💻 Como Rodar o Projeto

### Pré-requisitos

  - Java 17+
  - Maven
  - IDE (IntelliJ, VS Code, Eclipse)

### 1\. Clonar o Repositório

```bash
git clone https://docs.github.com/pt/repositories/creating-and-managing-repositories/quickstart-for-repositories
cd api-ecommerce
```

### 2\. Configurar o Banco de Dados

O projeto utiliza o **H2 Database** em memória por padrão (configurado no `application.properties`). Você não precisa de nenhuma configuração externa.

### 3\. Executar a Aplicação

Execute a classe principal que contém o método `main` na sua IDE, ou utilize o Maven:

```bash
./mvnw spring-boot:run
```

O servidor estará ativo em `http://localhost:8080`.

-----

## 🌐 Endpoints Principais

A documentação completa da API está disponível no Swagger UI:

👉 **[http://localhost:8080/swagger-ui/index.html](https://www.google.com/search?q=http://localhost:8080/swagger-ui/index.html)**

| Recurso | Método | Path | Descrição |
| :--- | :--- | :--- | :--- |
| **Categoria** | `POST` | `/categorias` | Cria uma nova categoria. |
| **Produto** | `POST` | `/produtos` | Cria produto (requer `categoriaId`). |
| **Cliente** | `GET` | `/clientes` | Lista clientes com paginação (`?page=0&size=10`). |
| **Pedido** | `POST` | `/pedidos` | Cria pedido com lista de itens (`ItemPedido`). |
| **Pedido** | `GET` | `/pedidos/{id}` | Lista pedido com totalização. |
| **Pedido** | `PATCH` | `/pedidos/{id}/status` | Atualiza o status do pedido. |

-----

## 🔐 Autenticação (Próxima Fase)

Atualmente, o Spring Security está configurado para permitir acesso a todos os endpoints (`permitAll()`) para facilitar os testes.

Para implementar a segurança com **JWT (JSON Web Token)**, será necessário:

1.  Implementar o *endpoint* de `/login`.
2.  Gerar o token JWT.
3.  Criar o filtro JWT para interceptar requisições e validar o token.
4.  Alterar o `SecurityConfig` para exigir autenticação (`.anyRequest().authenticated()`).

-----

## 👥 Colaboradores (Parte em Grupo)

  * Hugo Soares Americo
  * João Pedro Dias
  * Raphael Lima
  * Matheus Schwhartz

-----

## 💡 Próximos Passos (Parte Individual)

Na fase individual do projeto, cada membro do grupo irá criar sua própria `Branch` no GitHub e desenvolver uma funcionalidade extra.

  * **Exemplo:** `git checkout -b joao`

-----

**Serratec**
