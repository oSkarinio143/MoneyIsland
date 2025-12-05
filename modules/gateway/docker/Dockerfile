FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /gateway

COPY certs/nexus.cer nexus.cer
RUN keytool -import -trustcacerts \
    -keystore /opt/java/openjdk/lib/security/cacerts \
    -storepass password \
    -noprompt \
    -alias my-local-nexus \
    -file nexus.cer

COPY /modules/gateway/pom.xml .
COPY /modules/gateway/src ./src

RUN mvn clean package -DskipTests \
    -Dmaven.resolver.transport=wagon \
    -Dmaven.wagon.http.ssl.insecure=true \
    -Dmaven.wagon.http.ssl.allowall=true

FROM eclipse-temurin:21-jre
WORKDIR /gateway
COPY --from=builder /gateway/target/*.jar gateway.jar
ENTRYPOINT ["java","-jar","gateway.jar"]