# FROM supplies Maven and JDK 17 for compilation and tests.
FROM maven:3.9.16-eclipse-temurin-17 AS build
# WORKDIR selects the build directory.
WORKDIR /workspace
# COPY adds the existing Maven configuration and calculator source.
COPY pom.xml quality-rules.xml ./
COPY src ./src
# RUN tests and packages the inherited calculator application.
RUN mvn -B package
# FROM starts a smaller Java runtime stage.
FROM eclipse-temurin:17-jre-jammy
# WORKDIR selects the application directory.
WORKDIR /app
# COPY brings only the packaged application into the runtime.
COPY --from=build /workspace/target/calculator-1.0.jar calculator.jar
# EXPOSE documents the API port.
EXPOSE 8000
# ENTRYPOINT starts the HTTP API while retaining the original CLI class.
ENTRYPOINT ["java", "-cp", "/app/calculator.jar", "com.yourname.Api"]
