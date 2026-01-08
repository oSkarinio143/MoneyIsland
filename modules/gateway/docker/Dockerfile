FROM maven:3.9.9-eclipse-temurin-21 AS gateway-builder
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

COPY /modules/gateway/pom.xml ./modules/gateway/pom.xml

RUN mvn -f modules/gateway/pom.xml dependency:resolve-plugins \
    -Dmaven.resolver.transport=wagon \
    -Dmaven.wagon.http.ssl.insecure=true \
    -Dmaven.wagon.http.ssl.allowall=true

COPY /modules/gateway/src ./modules/gateway/src

RUN mvn -f modules/gateway/pom.xml clean package -DskipTests \
    -Dmaven.resolver.transport=wagon \
    -Dmaven.wagon.http.ssl.insecure=true \
    -Dmaven.wagon.http.ssl.allowall=true

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=gateway-builder /app/modules/gateway/target/*.jar gateway.jar
ENTRYPOINT ["java","-jar","gateway.jar"]