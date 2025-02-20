# Etapa de construcción (Build Stage)
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Copiar archivos del proyecto
COPY build.gradle settings.gradle ./
COPY gradlew gradlew   # 👈 Agregar esta línea
COPY gradle ./gradle
COPY src ./src

# Descargar dependencias y compilar el proyecto
RUN chmod +x gradlew  # 👈 Eliminar el "./"
RUN ./gradlew clean build -x test

# Etapa final (Runtime Stage)
FROM eclipse-temurin:21-jdk
WORKDIR /app
VOLUME /tmp

# Copiar el JAR generado en la etapa de construcción
COPY --from=build /app/build/libs/*.jar app.jar

# Exponer el puerto 8080 para el servidor de Spring Boot
EXPOSE 8080

# Comando de inicio de la aplicación
ENTRYPOINT ["java", "-jar", "/app.jar"]
