# using the latest OpenJDK 8 update (see https://registry.hub.docker.com/u/library/java/ for more details)
FROM java:openjdk-8-jdk
MAINTAINER Fabien Sanglier <fabien.sanglier@softwareag.com>

# Update Ubuntu
RUN \
  bash -c 'apt-get -qq update && apt-get -y upgrade && apt-get -y autoclean && apt-get -y autoremove' && \
  bash -c 'DEBIAN_FRONTEND=noninteractive apt-get install -y curl wget tar'

ARG JAR_PATH
ENV USER_NAME ehcacheusr
ENV APP_HOME /opt/ehcache-rest-server/$USER_NAME
ENV appPort

RUN \
  useradd -ms /bin/bash $USER_NAME && \
  mkdir -p $APP_HOME

ADD ehcache2x-rest-server.jar ${APP_HOME}/ehcache2x-rest-server.jar

RUN \
  chown $USER_NAME $APP_HOME/ehcache2x-rest-server.jar && \
  bash -c 'touch ${APP_HOME}/ehcache2x-rest-server.jar'

ENV JAVA_TOOL_OPTIONS "-Xms128M -Xmx128M -Djava.awt.headless=true -Djava.security.egd=file:/dev/./urandom"

# expose the webapp port
EXPOSE $appPort

USER $USER_NAME
WORKDIR $APP_HOME

ENTRYPOINT ["java", "-jar", "ehcache2x-rest-server.jar"]

# Run as:
# docker run -idt -p 8701:8701 -e appPort=8701 lanimall/ehcache2x-rest-server:latest