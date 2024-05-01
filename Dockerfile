FROM gradle:8.5.0-jdk21-alpine AS build

WORKDIR /app

COPY build.gradle.kts .
COPY settings.gradle.kts .

COPY src ./src

RUN gradle clean bootJar

FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY --from=build /app/build/libs/nexxiot.jar .

CMD ["java", "-jar", "nexxiot.jar"]