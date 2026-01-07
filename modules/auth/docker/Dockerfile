FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /app

COPY certs/nexus.cer nexus.cer
RUN keytool -import -trustcacerts \
    -keystore /opt/java/openjdk/lib/security/cacerts \
    -storepass password \
    -noprompt \
    -alias my-local-nexus \
    -file nexus.cer

COPY pom.xml .

RUN mvn -N install \
    -Dmaven.resolver.transport=wagon \
    -Dmaven.wagon.http.ssl.insecure=true \
    -Dmaven.wagon.http.ssl.allowall=true

COPY /modules/auth/pom.xml ./modules/auth/pom.xml

RUN mvn -f modules/auth/pom.xml dependency:resolve-plugins \
    -Dmaven.resolver.transport=wagon \
    -Dmaven.wagon.http.ssl.insecure=true \
    -Dmaven.wagon.http.ssl.allowall=true

COPY /modules/auth/src ./modules/auth/src

RUN mvn -f modules/auth/pom.xml clean package -U -DskipTests \
    -Dmaven.resolver.transport=wagon \
    -Dmaven.wagon.http.ssl.insecure=true \
    -Dmaven.wagon.http.ssl.allowall=true

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /app/modules/auth/target/*.jar auth.jar
ENTRYPOINT ["java","-jar","auth.jar"]