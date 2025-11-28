FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /build
COPY ../pom.xml /app/pom.xml
COPY pom.xml /app/finance/pom.xml
COPY src /app/finance/src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /build/modules/finance/target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]