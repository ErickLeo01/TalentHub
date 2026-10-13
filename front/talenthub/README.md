# TalentHub 🚀

O **TalentHub** é uma plataforma de recrutamento desenvolvida para conectar candidatos e empresas em um único sistema. A aplicação tem como objetivo facilitar o gerenciamento de candidatos, empresas, vagas e candidaturas.

O projeto está sendo desenvolvido como uma aplicação **Full Stack**, utilizando Spring Boot no Back-end, Angular no Front-end e PostgreSQL como banco de dados.

**Status:** Em desenvolvimento 🚧

## 🛠️ Tecnologias

### Back-end
- Java 21
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT (JSON Web Token)
- BCrypt
- PostgreSQL
- Maven

### Front-end
- Angular
- TypeScript
- HTML5
- CSS3
- RxJS

### Ferramentas
- IntelliJ IDEA
- Visual Studio Code
- DBeaver
- Apidog
- Git e GitHub

## 📌 Funcionalidades

### 👤 Candidatos

**Back-end**
- [x] Cadastro de candidatos
- [x] Atualização de cadastro
- [x] Exclusão de cadastro
- [x] Busca de candidatos por nome
- [x] Login com autenticação JWT
- [x] Candidatura em vagas

**Front-end**
- [x] Serviço de integração com a API
- [x] Componente de login
- [x] Componente de atualização de cadastro
- [ ] Finalizar e integrar as telas de candidatos
- [ ] Implementar visualização do próprio perfil
- [ ] Concluir a interface de gerenciamento de candidatos

### 🏢 Empresas

**Back-end**
- [x] Cadastro de empresas
- [x] Atualização de cadastro
- [x] Exclusão de cadastro
- [x] Busca de empresas
- [x] Login com autenticação JWT

**Front-end**
- [x] Serviço de integração com a API
- [ ] Finalizar formulário de cadastro
- [ ] Implementar login de empresas
- [ ] Implementar atualização e exclusão de cadastro
- [ ] Implementar listagem e busca de empresas
- [ ] Implementar visualização do perfil empresarial

### 💼 Vagas

**Back-end**
- [x] Cadastro de vagas
- [x] Atualização de vagas
- [x] Exclusão de vagas
- [x] Busca de vagas

**Front-end**
- [ ] Implementar listagem e busca de vagas
- [ ] Implementar cadastro e gerenciamento de vagas
- [ ] Integrar as telas com a API
- [ ] Implementar a interface de candidaturas

## 🔐 Segurança

O projeto utiliza tecnologias de autenticação e proteção de requisições, com o objetivo de aumentar a segurança das operações da API.

- **Spring Security:** configuração de autenticação e autorização.
- **JWT (JSON Web Token):** autenticação baseada em tokens.
- **BCrypt:** hashing de senhas.
- **Filtros de segurança:** validação de tokens e gerenciamento do contexto de autenticação.
- **Controle de acesso por perfil:** estrutura para diferenciar candidatos e empresas.

As regras de autorização e de acesso aos recursos próprios devem ser verificadas e testadas durante a evolução do projeto.

## 🏗️ Arquitetura

O TalentHub utiliza uma arquitetura na qual o Front-end Angular se comunica com uma API REST desenvolvida em Spring Boot, responsável pelas regras de negócio e pela comunicação com o banco de dados PostgreSQL.

```text
Angular
   │
   │ HTTP / JSON
   ▼
Spring Boot — API REST
   │
   ├── Controllers
   ├── DTOs
   ├── Use Cases / Services
   ├── Repositories
   └── Security
   │
   ▼
PostgreSQL
```

## 🎯 Objetivo do projeto

O objetivo do TalentHub é aplicar e aprimorar conhecimentos de desenvolvimento Back-end e Front-end por meio da construção de uma aplicação Full Stack.

Durante o desenvolvimento, são praticados:

- Desenvolvimento de APIs REST com Java e Spring Boot.
- Mapeamento de entidades e relacionamentos com Spring Data JPA.
- Modelagem e persistência de dados com PostgreSQL.
- Autenticação e segurança com Spring Security e JWT.
- Hashing de senhas com BCrypt.
- Desenvolvimento de interfaces com Angular e TypeScript.
- Integração entre Front-end e Back-end.
- Versionamento de código com Git e GitHub.
- Organização de código em camadas e separação de responsabilidades.

## 📈 Próximas etapas

- [ ] Finalizar os componentes de perfil de candidatos e empresas.
- [ ] Concluir os formulários e fluxos de autenticação do Front-end.
- [ ] Implementar as interfaces de gerenciamento de vagas.
- [ ] Concluir a integração entre Angular e Spring Boot.
- [ ] Revisar as rotas e as permissões de acesso.
- [ ] Testar as regras de autorização no Back-end.
- [ ] Melhorar a responsividade e a experiência do usuário.
- [ ] Realizar testes integrados dos principais fluxos.
- [ ] Preparar a aplicação para deploy.

## 👨‍💻 Autor

**Erick Leonardo de Lima dos Santos**

Estudante de Análise e Desenvolvimento de Sistemas (ADS), com foco em desenvolvimento Back-end e aplicações Full Stack.

### 🔗 Links

- **GitHub:** [ErickLeo01](https://github.com/ErickLeo01)
- **LinkedIn:** [erick-leonardo87](https://www.linkedin.com/in/erick-leonardo87/)

---

*Projeto desenvolvido para fins de aprendizado e evolução prática em desenvolvimento de software.*
