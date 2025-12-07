FROM gradle:8.5-jdk21 AS build
WORKDIR /app

COPY build.gradle settings.gradle gradlew gradlew.bat ./
COPY gradle ./gradle

# Dar permissão de execução ao gradlew
RUN chmod +x gradlew

COPY src ./src

RUN ./gradlew build -x test --no-daemon --stacktrace

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
