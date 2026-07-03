# TalentHub

TalentHub é uma API REST desenvolvida em **Java** utilizando **Spring Boot**, com o objetivo de gerenciar candidatos, empresas e vagas de emprego.

O projeto está sendo desenvolvido como forma de consolidar conhecimentos em desenvolvimento Back-end com Java, aplicando conceitos de arquitetura em camadas, boas práticas de programação e desenvolvimento de APIs REST.

---

##  Tecnologias utilizadas


- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- Bean Validation
- PostgreSQL
- Maven
- Lombok
- Git
- GitHub

---

## 📂 Estrutura do Projeto

```
src
└── main
    └── java
        └── com.erickleo.talentHub
            ├── config
            ├── controllers
            ├── dtos
            ├── entities
            ├── exceptions
            ├── providers
            ├── repositories
            ├── security
            └── useCases
```

---

## ⚙️ Funcionalidades implementadas

### 👤 Candidatos

- ✅ Cadastro de candidato
- ✅ Atualização de candidato
- ✅ Exclusão de candidato
- ✅ Validação de CPF duplicado
- ✅ Validação de e-mail duplicado
- ✅ Retorno utilizando ResponseDTO

---

### 🏢 Empresas

- ✅ Cadastro de empresa
- ✅ Atualização de empresa
- ✅ Exclusão de empresa
- ✅ Validação de CNPJ duplicado
- ✅ Validação de e-mail duplicado
- ✅ Retorno utilizando ResponseDTO

---

### 💼 Vagas

- ✅ Cadastro de vaga
- ✅ Atualização de vaga
- ✅ Exclusão de vaga
- ✅ Retorno utilizando ResponseDTO

---

### ⚠️ Tratamento de erros

- ✅ Exceções personalizadas
- ✅ Validação de campos obrigatórios
- ✅ Handler global de exceções
- ✅ Mensagens de erro amigáveis

---

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas:

```
Controller
    ↓
UseCase
    ↓
Repository
    ↓
Banco de Dados
```

Os dados trafegam utilizando DTOs, evitando que as entidades sejam expostas diretamente pela API.

---

## 🛠️ Como executar o projeto

### Clone o repositório

```bash
git clone https://github.com/SEU-USUARIO/TalentHub.git
```

Entre na pasta do projeto

```bash
cd TalentHub
```

Execute utilizando Maven

```bash
./mvnw spring-boot:run
```

Ou pelo IntelliJ IDEA (ou outra IDE de sua preferência), executando a classe:

```
TalentHubApplication.java
```

---

## 📌 Próximas funcionalidades

- [ ] Login de candidatos
- [ ] Login de empresas
- [ ] Autenticação JWT
- [ ] Spring Security
- [ ] Documentação com Swagger
- [ ] Upload de currículo
- [ ] Cadastro de habilidades
- [ ] Aplicação em vagas
- [ ] Busca de vagas
- [ ] Busca de candidatos
- [ ] Docker
- [ ] Testes unitários

---

## 👨‍💻 Autor

Desenvolvido por **Erick Leonardo**.

Projeto desenvolvido para estudos de **Java, Spring Boot e desenvolvimento de APIs REST**.