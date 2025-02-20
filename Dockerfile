FROM ubuntu:latest
LABEL authors="ES42250209Z"

ENTRYPOINT ["top", "-b"]

# Usa una imagen base de OpenJDK con Java 21
FROM eclipse-temurin:21-jdk

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR del proyecto al contenedor
COPY target/*.jar app.jar

# Expone el puerto en el que correrá la aplicación (por defecto 8080 en Spring Boot)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
