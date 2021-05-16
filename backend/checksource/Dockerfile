FROM ubuntu

RUN apt-get update;

RUN apt-get -y install software-properties-common && apt-add-repository -y ppa:webupd8team/java && apt update && echo "oracle-java8-installer shared/accepted-oracle-license-v1-1 select true" | debconf-set-selections && apt install -y oracle-java8-installer

RUN echo "jdk 8  install completed"

ADD build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]