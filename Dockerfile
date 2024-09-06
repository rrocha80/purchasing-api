FROM maven:3.8.3-openjdk-17-slim as builder
WORKDIR /app
COPY pom.xml .
RUN mvn -B dependency:resolve
COPY src/ ./src/
RUN mvn -B package

FROM maven:3.8.3-openjdk-17-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/app.jar
EXPOSE 8080


CMD ["java", "-jar", "/app/app.jar"]