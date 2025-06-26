# Establishment Manager - PÓS TECH FIAP

## Descrição do Projeto

O Establishment Manager é uma aplicação web desenvolvida para facilitar a gestão de restaurantes de forma centralizada e colaborativa. A plataforma permite que restaurantes cadastrem e gerenciem seus estabelecimentos, cardápios e pedidos, enquanto clientes podem consultar informações, avaliar restaurantes e realizar pedidos online. O sistema foi projetado para ser entregue em fases, possibilitando evolução contínua conforme o uso e feedback dos usuários.

## Pré-requisitos

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)

## Subindo a aplicação com Docker

1. **Clone o repositório:**
   ```bash
   git clone <URL_DO_REPOSITORIO>
   cd establishment.manager
   ```

2. **(Opcional) Ajuste o arquivo `application.properties` se necessário.**
   - O arquivo já está configurado para uso do H2 Database em memória e acesso ao H2 Console.

3. **Construa e suba os containers:**
   ```bash
   docker-compose up --build
   ```
   Isso irá:
   - Construir a imagem da aplicação
   - Subir o container na porta 8080

4. **Acesse a aplicação:**
   - API: http://localhost:8080
   - H2 Console: http://localhost:8080/h2-console

     - **JDBC URL:** `jdbc:h2:mem:establishment.manager`
     - **Usuário:** `sa`
     - **Senha:** `password`

   > **Obs:** Se aparecer o erro `Sorry, remote connections ('webAllowOthers') are disabled on this server.`, adicione a linha abaixo ao seu `application.properties`:
   >
   > `spring.h2.console.settings.web-allow-others=true`

## Parar a aplicação

Para parar e remover os containers:
```bash
docker-compose down
```

## Observações
- O banco H2 está em modo memória, ou seja, os dados são perdidos ao parar o container.
- O H2 Console é exposto apenas para facilitar o desenvolvimento. Não utilize em produção.

---

Se tiver dúvidas ou problemas, abra uma issue ou entre em contato com o mantenedor do projeto. 