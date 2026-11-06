# Catálogo de Cursos

**Integrantes:** Murilo Daon e Bruno Henrique  
**Professor:** Wesley Soares

Projeto independente desenvolvido para a atividade prática de Programação com Java e Spring Boot.

## Tecnologias utilizadas

- Java 21
- Spring Boot
- Maven
- PostgreSQL 16
- Docker e Docker Compose
- Spring Data JPA
- Liquibase
- Bean Validation
- Spring Web
- Spring Boot Test

## 1. Pré-requisitos

Antes de executar o projeto, é necessário ter instalado:

- Java 21
- IntelliJ IDEA
- Docker Desktop
- Postman (para testar a API)

## 2. Iniciar o banco de dados

Na pasta principal do projeto, abra o terminal e execute:

```bash
docker compose up -d
```

Esse comando inicia o PostgreSQL pelo Docker e cria automaticamente o banco `catalogocursos_dev`.

Configurações utilizadas:

- Banco: `catalogocursos_dev`
- Usuário: `postgres`
- Senha: `postgres`
- Porta: `5432`

## 3. Abrir e executar o projeto

1. Abra o IntelliJ IDEA.
2. Selecione **Open**.
3. Escolha a pasta `atividade-catalogo-cursos`, onde está localizado o arquivo `pom.xml`.
4. Aguarde o Maven baixar as dependências.
5. Configure o Project SDK como Java 21.
6. Abra a classe `CatalogoCursosApplication`.
7. Clique no botão verde para executar.

O Liquibase cria automaticamente as tabelas na primeira inicialização.

O Hibernate está configurado com `ddl-auto=validate`.

## 4. Endpoints da API

| Método | Endpoint | Função |
|---|---|---|
| POST | `/api/instrutores` | Cadastrar instrutor |
| POST | `/api/cursos` | Cadastrar curso |
| GET | `/api/cursos/{id}` | Buscar curso por ID |
| GET | `/api/instrutores/{id}/cursos` | Listar cursos de um instrutor |

### Exemplo: cadastrar instrutor

```json
{
  "nome": "Marina Costa",
  "email": "marina.costa@example.com"
}
```

### Exemplo: cadastrar curso

```json
{
  "titulo": "APIs REST com Spring Boot",
  "cargaHoraria": 40,
  "nivel": "INTERMEDIARIO",
  "instrutorId": 1
}
```

Os níveis disponíveis são:

- INICIANTE
- INTERMEDIARIO
- AVANCADO

## 5. Executar os testes

No terminal do IntelliJ, execute:

```bash
mvn clean test
```

Os testes verificam as associações entre instrutores e cursos, a validação de dados e a listagem dos cursos.

**Resultado dos testes realizados:** 4 testes executados, 0 falhas e 0 erros.

## 6. Encerrar o banco de dados

Quando terminar de utilizar o projeto, execute:

```bash
docker compose down
```

Esse comando encerra o container sem apagar os dados armazenados no volume do PostgreSQL.

## Observação

O projeto utiliza PostgreSQL executado pelo Docker, facilitando a configuração do ambiente e a execução em outros computadores.