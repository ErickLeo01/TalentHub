# TalentHub

O **TalentHub** é uma plataforma de recrutamento desenvolvida para conectar candidatos e empresas em um único sistema. A aplicação permite o gerenciamento de candidatos, empresas, vagas e candidaturas.

O projeto está sendo desenvolvido com **Spring Boot no Back-end**, **Angular no Front-end** e **PostgreSQL** como banco de dados.

**Status:** Em desenvolvimento 🚧

## 🛠️ Tecnologias

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
- Visual Studio Code
- DBeaver
- Apidog
- Git e GitHub

## 📌 Funcionalidades

### 👤 Candidatos
- [x] Cadastro
- [x] Atualização de cadastro
- [x] Exclusão de cadastro
- [x] Busca por nome
- [x] Login
- [x] Candidatura em vagas
- [ ] Listagem de candidatos no Front-end
- [ ] Visualização de perfil
- [ ] Interface completa de gerenciamento

### 🏢 Empresas
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

A estrutura de segurança do projeto utiliza ou prevê a utilização dos seguintes recursos:

- **Spring Security:** controle de autenticação e autorização.
- **JWT (JSON Web Token):** autenticação baseada em tokens.
- **BCrypt:** armazenamento seguro de senhas por meio de hashing.
- **Filtros de segurança:** processamento das requisições protegidas.

##  Arquitetura

```text
Angular
   │
   │ HTTP / JSON
   ▼
Spring Boot
   │
   ├── Controllers
   ├── Use Cases / Services
   ├── DTOs
   ├── Repositories
   └── Security
   │
   ▼
PostgreSQL
```

##  Objetivo

O objetivo do TalentHub é aplicar na prática conhecimentos de desenvolvimento Back-end e Front-end, trabalhando com APIs REST, autenticação, banco de dados, integração entre Angular e Spring Boot e organização de uma aplicação Full Stack.

## 📈 Status do desenvolvimento

O Back-end possui funcionalidades de gerenciamento de candidatos, empresas e vagas. O Front-end Angular está em desenvolvimento, com a criação dos componentes, interfaces, serviços e integração com a API REST.

As próximas etapas incluem a conclusão das interfaces, a integração entre as telas e a API, e os testes dos fluxos completos da aplicação.

## 👨‍💻 Autor

**Erick Leonardo de Lima dos Santos**

Estudante de Análise e Desenvolvimento de Sistemas (ADS), com foco em desenvolvimento Back-end e aplicações Full Stack.

### 🔗 Links

- GitHub: [ErickLeo01](https://github.com/ErickLeo01)
- LinkedIn: [erick-leonardo87](https://www.linkedin.com/in/erick-leonardo87/)
