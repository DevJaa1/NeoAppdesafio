# Etapa 1: Build
FROM maven:3.9.3-eclipse-temurin-17 AS build
WORKDIR /app
COPY neoapp/pom.xml .
COPY neoapp/src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Executar aplicação
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
ENTRYPOINT ["java","-jar","app.jar"]

