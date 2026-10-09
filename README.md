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

##  Arquitetura

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




