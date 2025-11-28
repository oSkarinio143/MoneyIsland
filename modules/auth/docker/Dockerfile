FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /authMod
COPY /modules/auth/pom.xml pom.xml
COPY /modules/auth/src /src
COPY maven-settings.xml /usr/share/maven/ref/settings-docker.xml
#COPY modules/shared/pom.xml shared/pom.xml
#COPY modules/shared/src shared/src
RUN mvn -s /usr/share/maven/ref/settings-docker.xml -f modules/auth/pom.xml clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /authMod
COPY --from=builder /authMod/target/*.jar authMod.jar
ENTRYPOINT ["java","-jar","auth.jar"]