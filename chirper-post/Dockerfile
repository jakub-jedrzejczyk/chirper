FROM eclipse-temurin:22.0.2_9-jdk

LABEL org.opencontainers.image.title="chirper-post"
LABEL org.opencontainers.image.description="Chirper Post Service"
LABEL org.opencontainers.image.authors="Jakub Jedrzejczyk"
LABEL org.opencontainers.image.version="0.0.1-SNAPSHOT"

LABEL build_version=""
LABEL maintainer=""

ENV VERSION="0.0.1-SNAPSHOT"

ENV SERVER_PORT 8080

ENV SPRING_DATASOURCE_URL=jdbc:h2:mem:chirper-post
ENV SRPING_DATARSOURCE_DRIVERCLASSNAME=org.h2.Driver
ENV SPRING_DATASOURCE_USERNAME=admin
ENV SPRING_DATASOURCE_PASSWORD=password

ENV SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.H2Dialect
ENV SPRING_JPA_GENERATE_DDL=true
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update

ENV SPRING_H2_CONSOLE_ENABLED=true 

EXPOSE 8080

COPY target/chirper-${VERSION}.jar /opt/chirper-post/chirper-post.jar

CMD ["java", "-jar", "/opt/chirper-post/chirper-post.jar"]
