FROM nginx:1.23.3

LABEL org.opencontainers.image.title="chirper-ng"
LABEL org.opencontainers.image.description="Chirper Angular Frontend"
LABEL org.opencontainers.image.authors="Jakub Jedrzejczyk"
LABEL org.opencontainers.image.version="0.0.1-SNAPSHOT"

LABEL build_version=""
LABEL maintainer=""

ENV VERSION="0.0.1-SNAPSHOT"

ENV API_URL http://localhost:8080/api

EXPOSE 80

ADD dist/chirper-ng /usr/share/nginx/html
ADD docker/etc/nginx/templates/ /etc/nginx/templates/
