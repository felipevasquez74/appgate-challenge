# Etapa de construcción
FROM maven:3.9.5-amazoncorretto-21-debian AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package

# Etapa de producción
FROM amazoncorretto:21-alpine3.17-jdk

ENV TZ=America/Bogota
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

WORKDIR /home/app

COPY --from=builder /app/target/appgate-social-media-analyzer*.jar appgate-social-media-analyzer.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "appgate-social-media-analyzer.jar"]

