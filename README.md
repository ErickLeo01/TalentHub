<<<<<<< HEAD
# TalentHub

O **TalentHub** é uma plataforma de recrutamento desenvolvida para conectar candidatos e empresas em um único sistema. A aplicação permite o gerenciamento de candidatos, empresas, vagas e candidaturas.

O projeto está sendo desenvolvido com **Spring Boot no Back-end**, **Angular no Front-end** e **PostgreSQL** como banco de dados.

>  **Status:** Em desenvolvimento

##  Tecnologias

### Back-end
- Java 21
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT
- BCrypt
- PostgreSQL
- Maven

### Front-end
- Angular
- TypeScript
- HTML
- CSS
- RxJS

### Ferramentas
- IntelliJ IDEA
- VS Code
- DBeaver
- Apidog
- Git e GitHub

## 📌 Funcionalidades

### 👤 Candidato
- [x] Cadastro
- [x] Atualização de cadastro
- [x] Exclusão de cadastro
- [x] Busca por nome
- [x] Login
- [x] Candidatura em vagas
- [ ] Interface completa no Front-end

### 🏢 Empresa
- [x] Cadastro
- [x] Atualização
- [x] Exclusão
- [x] Busca
- [ ] Interface completa no Front-end

### 💼 Vagas
- [x] Cadastro de vagas
- [x] Atualização de vagas
- [x] Exclusão de vagas
- [x] Busca de vagas
- [ ] Interface completa no Front-end

## 🔐 Segurança

O projeto possui estrutura de autenticação utilizando:

- Spring Security
- JWT
- BCrypt para criptografia das senhas
- Filtros de autenticação



## 🔄 Arquitetura

```text
Angular
   │
   │ HTTP / JSON
   ▼
Spring Boot
   │
   ├── Controllers
   ├── Use Cases
   ├── DTOs
   ├── Repositories
   └── Security
   │
   ▼
PostgreSQL
```

## 🎯 Objetivo

O projeto tem como objetivo aplicar na prática conhecimentos de desenvolvimento **Back-end e Front-end**, trabalhando com APIs REST, autenticação, banco de dados, integração entre Angular e Spring Boot e organização de uma aplicação Full Stack.

## 📈 Status do desenvolvimento

O **Back-end** possui as principais funcionalidades implementadas e o **Front-end Angular está em desenvolvimento**, atualmente com a estrutura dos componentes, interfaces e comunicação com a API sendo construída.

Novas funcionalidades e melhorias serão adicionadas ao projeto durante o desenvolvimento.

## 👨‍💻 Autor

**Erick Leonardo de Lima dos Santos**

Estudante de Análise e Desenvolvimento de Sistemas (ADS), com foco em desenvolvimento Back-end e aplicações Full Stack.

### 🔗 Links

- GitHub: `ErickLeo01`
- LinkedIn: `erick-leonardo87`
=======
# Talenthub

This project was generated using [Angular CLI](https://github.com/angular/angular-cli) version 22.0.7.

## Development server

To start a local development server, run:

```bash
ng serve
```

Once the server is running, open your browser and navigate to `http://localhost:4200/`. The application will automatically reload whenever you modify any of the source files.

## Code scaffolding

Angular CLI includes powerful code scaffolding tools. To generate a new component, run:

```bash
ng generate component component-name
```

For a complete list of available schematics (such as `components`, `directives`, or `pipes`), run:

```bash
ng generate --help
```

## Building

To build the project run:

```bash
ng build
```

This will compile your project and store the build artifacts in the `dist/` directory. By default, the production build optimizes your application for performance and speed.

## Running unit tests

To execute unit tests with the [Vitest](https://vitest.dev/) test runner, use the following command:

```bash
ng test
```

## Running end-to-end tests

For end-to-end (e2e) testing, run:

```bash
ng e2e
```

Angular CLI does not come with an end-to-end testing framework by default. You can choose one that suits your needs.

## Additional Resources

For more information on using the Angular CLI, including detailed command references, visit the [Angular CLI Overview and Command Reference](https://angular.dev/tools/cli) page.
>>>>>>> 2a097e8 (feat: adiciona componentes de gerenciamento de candidatos)
