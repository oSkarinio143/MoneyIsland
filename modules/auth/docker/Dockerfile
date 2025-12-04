FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /auth

COPY certs/nexus.cer nexus.cer
RUN keytool -import -trustcacerts \
    -keystore /opt/java/openjdk/lib/security/cacerts \
    -storepass password \
    -noprompt \
    -alias my-local-nexus \
    -file nexus.cer

COPY /modules/auth/pom.xml pom.xml
COPY /modules/auth/src src

RUN mvn clean package -DskipTests \
    -Dmaven.resolver.transport=wagon \
    -Dmaven.wagon.http.ssl.insecure=true \
    -Dmaven.wagon.http.ssl.allowall=true

FROM eclipse-temurin:21-jre
WORKDIR /auth
COPY --from=builder /auth/target/*.jar auth.jar
ENTRYPOINT ["java","-jar","auth.jar"]