# using the latest OpenJDK 8 update (see https://registry.hub.docker.com/u/library/java/ for more details)
FROM java:openjdk-8-jdk
MAINTAINER Fabien Sanglier <fabien.sanglier@softwareag.com>

# Update Ubuntu
RUN \
  bash -c 'apt-get -qq update && apt-get -y upgrade && apt-get -y autoclean && apt-get -y autoremove' && \
  bash -c 'DEBIAN_FRONTEND=noninteractive apt-get install -y curl wget tar'

ENV USER_NAME ehcacheusr
ENV APP_HOME /opt/ehcache-rest-server/$USER_NAME

RUN \
  useradd -ms /bin/bash $USER_NAME && \
  mkdir -p $APP_HOME

ADD ehcache-rest-server.jar ${APP_HOME}/ehcache-rest-server.jar

RUN \
  chown $USER_NAME $APP_HOME/ehcache-rest-server.jar && \
  bash -c 'touch ${APP_HOME}/ehcache-rest-server.jar'

ENV JAVA_TOOL_OPTIONS "-Xms128M -Xmx128M -Djava.awt.headless=true -Djava.security.egd=file:/dev/./urandom"

USER $USER_NAME
WORKDIR $APP_HOME
ENTRYPOINT ["java", "-jar", "ehcache-rest-server.jar"]

# Run as:
# docker run -idt -p 8701:8701 -e appPort=8701 lanimall/ehcache-rest-server:latest