# ETAPA 1: Construcción (Build)
# Usamos una imagen con Maven y JDK 21 para compilar
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Compilamos saltando los tests para agilizar el build en la nube
RUN mvn clean package -DskipTests

# ETAPA 2: Ejecución (Runtime)
# Usamos una imagen muy ligera solo con JRE (Java Runtime)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Copiamos solo el JAR compilado desde la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Puerto que expone tu app (Render usa PORT env var, pero definimos 8092 por defecto)
EXPOSE 8092

# Comando de arranque
ENTRYPOINT ["java", "-jar", "app.jar"]
