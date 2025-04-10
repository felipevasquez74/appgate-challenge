FROM amazoncorretto:21-alpine3.17-jdk

ENV TZ=America/Bogota
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

CMD ["mkdir", "/home/app/"]
ADD ./target/appgate-challenge-*.jar /home/app/
ADD ./target/classes/logback-spring.xml /home/app/logback-spring.xml
RUN mv /home/app/appgate-challenge-*.jar /home/app/appgate-challenge.jar
ENV LOGBACK_CONF=/home/app/logback-spring.xml

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/home/app/appgate-challenge.jar", "-Dlogback.configurationFile=/home/app/logback-spring.xml"]