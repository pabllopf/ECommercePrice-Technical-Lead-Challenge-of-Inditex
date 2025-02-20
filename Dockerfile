# Etapa de construcción
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Copiar archivos Gradle
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle
COPY src ./src

# Dar permisos y compilar la aplicación
RUN chmod +x gradlew
RUN ./gradlew clean build -x test

# Etapa final (Runtime)
FROM eclipse-temurin:21-jdk
WORKDIR /app
VOLUME /tmp

# Copiar el JAR de manera segura
RUN mkdir -p /app/build/libs
COPY --from=build /app/build/libs/*.jar /app/app.jar

# Exponer el puerto
EXPOSE 8080

# Iniciar la aplicación
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
