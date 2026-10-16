# TalentHub 🚀

O **TalentHub** é uma plataforma de recrutamento desenvolvida para conectar candidatos e empresas em um único sistema. O projeto tem como objetivo facilitar o gerenciamento de candidatos, empresas, vagas e candidaturas.

A aplicação está sendo desenvolvida como um projeto **Full Stack**, utilizando Java e Spring Boot no back-end, Angular no front-end e PostgreSQL como banco de dados.

> 🚧 **Status:** Em desenvolvimento.

## 🛠️ Tecnologias utilizadas

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
- [ ] Testar os principais fluxos de candidatos

### 🏢 Empresas

**Back-end**
- [x] Cadastro de empresas
- [x] Atualização de cadastro
- [x] Exclusão de cadastro
- [x] Busca de empresas
- [x] Login com autenticação JWT

**Front-end**
- [x] Serviço de integração com a API
- [x] Componentes de listagem, perfil e atualização iniciados
- [ ] Finalizar o formulário de cadastro
- [ ] Implementar o login de empresas
- [ ] Concluir a atualização e a exclusão de cadastro
- [ ] Finalizar a listagem e a busca de empresas
- [ ] Concluir a visualização do perfil empresarial
- [ ] Testar os principais fluxos de empresas

### 💼 Vagas

**Back-end**
- [x] Cadastro de vagas
- [x] Atualização de vagas
- [x] Exclusão de vagas
- [x] Busca de vagas por nome
- [ ] Conferir e testar a busca de vagas por ID
- [ ] Validar as regras de autorização dos endpoints

**Front-end**
- [x] Serviço de integração com a API
- [x] Componente de cadastro de vagas
- [x] Componente de listagem e busca
- [x] Componente de atualização
- [x] Componente de exclusão
- [x] Componente de detalhes da vaga
- [ ] Validar a integração de todas as telas com a API
- [ ] Implementar e integrar a interface de candidaturas
- [ ] Testar os principais fluxos de gerenciamento de vagas

*Os itens concluídos indicam funcionalidades implementadas no código. A validação completa depende da execução dos testes e da integração entre os componentes.*

## 🔐 Segurança

O projeto utiliza recursos do Spring Security para estruturar a autenticação e a autorização das requisições.

- **Spring Security:** configuração de autenticação e autorização.
- **JWT (JSON Web Token):** autenticação baseada em tokens.
- **BCrypt:** hashing de senhas.
- **Filtros de segurança:** validação de tokens e gerenciamento do contexto de autenticação.
- **Controle de acesso por perfil:** diferenciação entre candidatos e empresas.

As permissões de acesso e a proteção dos recursos de cada usuário devem ser validadas por meio de testes antes de considerar a segurança concluída.

## 🏗️ Arquitetura

O TalentHub utiliza uma arquitetura em que o front-end Angular se comunica com uma API REST desenvolvida em Spring Boot. A API é responsável pelas regras de negócio, pela segurança e pelo acesso aos dados armazenados no PostgreSQL.

```text
Angular
   |
   | HTTP / JSON
   v
Spring Boot — API REST
   |
   +-- Controllers
   +-- DTOs
   +-- Use Cases / Services
   +-- Repositories
   +-- Security
   |
   v
PostgreSQL
```

## ⚙️ Como executar o projeto

### Pré-requisitos

Antes de executar a aplicação, instale:

- Java 21
- Node.js e npm compatíveis com a versão do Angular utilizada
- PostgreSQL
- Git

### 1. Clonar o repositório

```bash
git clone https://github.com/ErickLeo01/talenthub.git
cd talenthub
```

### 2. Configurar o banco de dados

Crie um banco de dados PostgreSQL para o projeto e configure as credenciais na configuração local do back-end.

**Importante:** os valores abaixo são exemplos. Utilize as configurações correspondentes ao seu ambiente e não publique credenciais reais.

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/NOME_DO_BANCO
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
```

Configure também a chave JWT conforme a implementação do projeto. Não publique chaves secretas no repositório.

### 3. Executar o back-end

No terminal, acesse a pasta do back-end:

```bash
cd back/talenthub-api
```

No Windows, execute:

```bash
.\mvnw.cmd spring-boot:run
```

No Linux ou macOS, execute:

```bash
./mvnw spring-boot:run
```

Verifique a configuração de conexão com o PostgreSQL antes de iniciar a aplicação.

### 4. Executar o front-end

Em outro terminal, acesse a pasta do Angular:

```bash
cd front/talenthub
npm install
ng serve
```

Depois, abra no navegador:

http://localhost:4200

*As instruções de execução devem ser conferidas no ambiente local, incluindo as configurações necessárias para o banco de dados, a autenticação e a comunicação entre o front-end e o back-end.*

## 🎯 Objetivo do projeto

O TalentHub tem como objetivo aplicar e aprimorar conhecimentos de desenvolvimento Back-end e Front-end por meio da construção de uma aplicação Full Stack.

Durante o desenvolvimento, são praticados:

- Desenvolvimento de APIs REST com Java e Spring Boot.
- Programação orientada a objetos.
- Mapeamento de entidades e relacionamentos com Spring Data JPA.
- Modelagem e persistência de dados com PostgreSQL.
- Autenticação e segurança com Spring Security e JWT.
- Hashing de senhas com BCrypt.
- Desenvolvimento de interfaces com Angular e TypeScript.
- Integração entre front-end e back-end.
- Organização do código em camadas e separação de responsabilidades.
- Versionamento de código com Git e GitHub.
- Testes e depuração de aplicações.

## 📈 Próximas etapas

- [ ] Finalizar os componentes de perfil de candidatos e empresas.
- [ ] Concluir os formulários e fluxos de autenticação do front-end.
- [ ] Finalizar a integração das funcionalidades de vagas.
- [ ] Validar as rotas e as permissões de acesso.
- [ ] Testar as regras de autorização no back-end.
- [ ] Executar testes integrados dos principais fluxos.
- [ ] Melhorar a responsividade e a experiência do usuário.
- [ ] Documentar os endpoints da API.
- [ ] Preparar a aplicação para deploy.

## 👨‍💻 Autor

**Erick Leonardo de Lima dos Santos**

Estudante de Análise e Desenvolvimento de Sistemas (ADS), com foco em desenvolvimento Back-end com Java e construção de aplicações Full Stack.

### 🔗 Links

- **GitHub:** [ErickLeo01](https://github.com/ErickLeo01)
- **LinkedIn:** [erick-leonardo87](https://www.linkedin.com/in/erick-leonardo87/)

---

*Projeto desenvolvido para fins de aprendizado e evolução prática em desenvolvimento de software.*
