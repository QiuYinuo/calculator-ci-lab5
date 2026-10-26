# FROM starts the build stage with official Maven and OpenJDK 17 tooling.
FROM maven:3.9.16-eclipse-temurin-17 AS build
# WORKDIR sets the directory for subsequent build instructions.
WORKDIR /workspace
# COPY adds the Maven configuration and the existing calculator source.
COPY pom.xml quality-rules.xml ./
COPY src ./src
# RUN compiles, runs all JUnit tests and packages the executable shaded JAR.
RUN mvn -B package

# FROM starts a smaller runtime stage containing a JRE rather than Maven/JDK.
FROM eclipse-temurin:17-jre-jammy
# WORKDIR provides a predictable location for the application.
WORKDIR /app
# COPY transfers only the executable JAR from the build stage.
COPY --from=build /workspace/target/calculator-1.0.jar calculator.jar
# ENTRYPOINT runs Java directly and forwards container arguments to the CLI.
ENTRYPOINT ["java", "-jar", "/app/calculator.jar"]
# CMD supplies a default example; docker run arguments replace these values.
CMD ["2", "+", "2"]
