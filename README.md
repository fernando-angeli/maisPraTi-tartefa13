
# MaisPraTi - Tarefa 13

Projeto desenvolvido para gerenciar uma API com autenticação usando JWT e OAuth2.

## Funcionalidades

- **Autenticação JWT**: Proteção dos endpoints com tokens JWT.
- **OAuth2**: Implementação de autenticação com OAuth2.
- **API REST**: Criação, leitura, atualização e exclusão de dados.

## Tecnologias

- Java 21
- Spring Boot 3.3.4
- Spring Security com OAuth2 e JWT

## Pré-requisitos

- **JDK** 17 ou superior
- **Maven** para gerenciar as dependências
- **Banco de dados** (MySQL ou H2 para testes)

## Configuração

1. Clone o repositório:
   ```bash
   git clone https://github.com/fernando-angeli/maisPraTi-tartefa13.git
   ```
2. Configure o banco de dados no arquivo `application.properties`.

3. Instale as dependências e compile o projeto:
   ```bash
   mvn clean install
   ```

## Executando a Aplicação

Inicie o servidor com:
```bash
mvn spring-boot:run
```

## Endpoints Principais

| Método | Endpoint      | Descrição             |
|--------|---------------|-----------------------|
| POST   | `/login`      | Autenticação do usuário |
| GET    | `/api/user`   | Recupera informações do usuário |

## Testes

- Utilize ferramentas como Postman para testar os endpoints.

## Contribuição

1. Fork o projeto.
2. Crie uma nova branch.
3. Envie um Pull Request para revisão.

## Licença

Este projeto está sob a licença MIT.
