# TalentHub 🚀

Plataforma de recrutamento desenvolvida para conectar candidatos e empresas em um único sistema, permitindo o gerenciamento de perfis, vagas e candidaturas.

O **TalentHub** é um projeto Full Stack desenvolvido para praticar e aprimorar conhecimentos em desenvolvimento de APIs REST, segurança, persistência de dados e construção de interfaces web.

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
- [x] Candidatura a vagas

**Front-end**

- [x] Serviço de integração com a API
- [x] Componente de login
- [x] Componente de atualização de cadastro
- [x] Concluir a interface de gerenciamento de candidatos
- [x] Testar os principais fluxos de candidatos

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
- [x] Componente de login criado
- [x] Finalizar o formulário de cadastro
- [x] Validar a integração do login de empresas
- [x] Concluir a atualização e a exclusão de cadastro
- [x] Finalizar a listagem e a busca de empresas
- [x] Concluir a visualização do perfil empresarial
- [x] Testar os principais fluxos de empresas

### 💼 Vagas

**Back-end**

- [x] Cadastro de vagas
- [x] Atualização de vagas
- [x] Exclusão de vagas
- [x] Busca de vagas por nome
- [x] Conferir e testar a busca de vagas por ID
- [x] Validar as regras de autorização dos endpoints

**Front-end**

- [x] Serviço de integração com a API
- [x] Componente de cadastro de vagas
- [x] Componente de listagem e busca
- [x] Componente de atualização
- [x] Componente de exclusão
- [x] Componente de detalhes da vaga
- [x] Validar a integração de todas as telas com a API
- [x] Implementar e integrar a interface de candidaturas
- [x] Testar os principais fluxos de gerenciamento de vagas

## 🔐 Segurança

O TalentHub utiliza recursos do Spring Security para implementar a autenticação e estruturar a autorização das requisições.

- **Spring Security:** configuração de segurança e controle de acesso.
- **JWT:** autenticação baseada em tokens.
- **BCrypt:** hashing de senhas.
- **Filtros de segurança:** validação dos tokens e configuração do contexto de autenticação.
- **Controle de acesso por perfil:** diferenciação entre candidatos e empresas.

As regras de autorização devem ser testadas para garantir que cada usuário acesse somente os recursos permitidos ao seu perfil.

**Boas práticas de segurança:**

- Não armazenar senhas em texto puro.
- Não publicar senhas do banco ou chaves JWT.
- Utilizar variáveis de ambiente para informações sensíveis.
- Validar a autenticação e as permissões em cada endpoint protegido.

## 🏗️ Arquitetura

O projeto utiliza uma arquitetura Full Stack na qual o front-end Angular se comunica com uma API REST desenvolvida em Spring Boot. O back-end concentra as regras de negócio, a segurança e o acesso aos dados persistidos no PostgreSQL.

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

### Organização das camadas

- **Controllers:** recebem e encaminham as requisições HTTP.
- **DTOs:** definem os dados recebidos e enviados pela API.
- **Use Cases / Services:** implementam as regras de negócio.
- **Repositories:** realizam as operações de persistência.
- **Security:** concentra as configurações e os componentes de segurança.

## ⚙️ Como executar o projeto

### Pré-requisitos

Antes de iniciar, instale:

- Java 21
- Node.js e npm compatíveis com a versão do Angular utilizada
- PostgreSQL
- Git

### 1. Clonar o repositório

```bash
git clone https://github.com/ErickLeo01/TalentHub.git
cd TalentHub
```

### 2. Configurar o banco de dados

Crie um banco de dados PostgreSQL chamado `talenthub`.

Configure as variáveis de ambiente utilizadas pelo back-end. No Windows, pelo PowerShell, você pode defini-las para a sessão atual:

```powershell
$env:DB_URL="jdbc:postgresql://localhost:5432/talenthub"
$env:DB_USERNAME="seu_usuario"
$env:DB_PASSWORD="sua_senha"
$env:JWT_SECRET="sua_chave_jwt"
```

Substitua os valores de exemplo pelas configurações do seu ambiente. Utilize uma chave JWT forte e compatível com a implementação do projeto.

Essas variáveis precisam estar definidas no ambiente em que o back-end for iniciado.

**Importante:** não publique credenciais reais no GitHub. Se alguma senha ou chave secreta já tiver sido exposta, substitua-a.

### 3. Executar o back-end

Abra um terminal e acesse a pasta do back-end:

```bash
cd back/talenthub-api
```

No Windows, execute:

```powershell
.\mvnw.cmd spring-boot:run
```

No Linux ou macOS, execute:

```bash
./mvnw spring-boot:run
```

A API está configurada para utilizar a porta `8081`. Confira a conexão com o PostgreSQL e as variáveis de ambiente caso a aplicação não inicie.

### 4. Executar o front-end

Abra outro terminal na raiz do repositório e acesse a pasta do Angular:

```bash
cd front/talenthub
npm install
ng serve
```

Após a inicialização, acesse:

[http://localhost:4200](http://localhost:4200)

Verifique se a URL configurada nos serviços Angular corresponde à porta utilizada pelo back-end.

## 🧪 Testes da API

O Apidog é utilizado para testar as requisições HTTP da aplicação.

Durante os testes, verifique:

- Cadastro, consulta, atualização e exclusão de candidatos.
- Cadastro e autenticação de empresas.
- Cadastro, consulta, atualização e exclusão de vagas.
- Candidaturas e prevenção de operações inválidas.
- Respostas HTTP e mensagens de erro.
- Acesso aos endpoints públicos e protegidos.
- Permissões de acesso conforme o perfil do usuário.

A cobertura dos testes será ampliada conforme as funcionalidades forem concluídas.

## 🎯 Objetivos de aprendizagem

O desenvolvimento do TalentHub permite praticar:

- Desenvolvimento de APIs REST com Java e Spring Boot.
- Programação orientada a objetos.
- Organização do código em camadas e separação de responsabilidades.
- Mapeamento de entidades e relacionamentos com Spring Data JPA.
- Modelagem e persistência de dados com PostgreSQL.
- Autenticação e autorização com Spring Security e JWT.
- Hashing de senhas com BCrypt.
- Desenvolvimento de interfaces com Angular e TypeScript.
- Integração entre front-end e back-end.
- Testes, depuração e versionamento com Git e GitHub.


## 👨‍💻 Autor

**Erick Leonardo de Lima dos Santos**

Estudante de Análise e Desenvolvimento de Sistemas (ADS), com foco em desenvolvimento back-end com Java e construção de aplicações Full Stack.

### 🔗 Links

- **GitHub:** [ErickLeo01](https://github.com/ErickLeo01)
- **LinkedIn:** [erick-leonardo87](https://www.linkedin.com/in/erick-leonardo87/)

---

*Projeto desenvolvido para fins de aprendizado e evolução prática em desenvolvimento de software.*
