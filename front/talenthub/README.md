# TalentHub 🚀

O **TalentHub** é uma plataforma de recrutamento desenvolvida para conectar candidatos e empresas em um único sistema. A aplicação permite o gerenciamento de candidatos, empresas, vagas e candidaturas.

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
- [x] Busca por nome
- [x] Login com autenticação JWT
- [x] Candidatura em vagas

**Front-end**
- [x] Serviço de integração com a API
- [x] Componente de login
- [x] Componente de atualização de cadastro
- [ ] Finalizar e integrar as telas de candidatos
- [ ] Visualização do próprio perfil
- [ ] Interface completa de gerenciamento

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

O projeto utiliza recursos de autenticação e autorização para proteger as operações da API.

- **Spring Security:** controle de autenticação e autorização das requisições.
- **JWT (JSON Web Token):** autenticação baseada em tokens.
- **BCrypt:** hashing de senhas para armazenamento mais seguro.
- **Filtros de segurança:** validação dos tokens e configuração do contexto de autenticação.
- **Controle de acesso por perfil:** diferenciação entre candidatos e empresas.
- **Validação de propriedade:** regras para restringir operações aos recursos autorizados.

## 🏗️ Arquitetura

O TalentHub utiliza uma arquitetura na qual o Front-end Angular se comunica com a API REST desenvolvida em Spring Boot, que acessa o banco de dados PostgreSQL.

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

O objetivo do TalentHub é aplicar conhecimentos de desenvolvimento Back-end e Front-end na construção de uma aplicação Full Stack.

O projeto permite praticar:

- Desenvolvimento de APIs REST com Java e Spring Boot.
- Mapeamento de entidades e relacionamentos com Spring Data JPA.
- Modelagem e persistência de dados com PostgreSQL.
- Autenticação e autorização utilizando Spring Security e JWT.
- Desenvolvimento de interfaces com Angular e TypeScript.
- Integração entre Front-end e Back-end.
- Versionamento de código com Git e GitHub.

## 📈 Próximas etapas

- [ ] Finalizar os componentes do Front-end.
- [ ] Concluir a integração entre Angular e Spring Boot.
- [ ] Revisar a navegação e o controle de acesso por perfil.
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
