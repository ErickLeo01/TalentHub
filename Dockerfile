# Imagem base com Java 21
FROM eclipse-temurin:21-jdk

# Diretório de trabalho dentro do container
WORKDIR /app

# Copia todos os arquivos do projeto
COPY . .

# Dá permissão de execução para o Maven Wrapper
RUN chmod +x mvnw

# Compila o projeto sem executar os testes
RUN ./mvnw clean package -DskipTests

# Expõe a porta utilizada pelo Spring Boot
EXPOSE 8080

# Executa a aplicação
ENTRYPOINT ["java", "-jar", "target/talentHub-0.0.1-SNAPSHOT.jar"]