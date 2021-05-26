# syntax=docker/dockerfile:1
FROM centos

RUN mkdir /opt/tomcat/
RUN ls -a /opt/tomcat/
RUN ls -a etc
RUN ls -a home


WORKDIR /opt/tomcat
RUN curl -O https://downloads.apache.org/tomcat/tomcat-9/v9.0.46/bin/apache-tomcat-9.0.46.tar.gz
RUN tar xvfz apache*.tar.gz
RUN ls -a /opt/tomcat/
#RUN mv apache-tomcat-9.0.46* /opt/tomcat/.
RUN yum -y install java
RUN java -version

WORKDIR /opt/tomcat/
RUN curl -O -L https://github.com/omaritgis/SiteSnapWAR/raw/main/SiteSnap.war

EXPOSE 8080

RUN pwd
RUN ls -a /opt/tomcat

CMD ["/opt/tomcat/apache-tomcat-9.0.46/bin/catalina.sh", "run"]

COPY SiteSnap.war apache-tomcat-9.0.46/webapps/
RUN ls -a apache-tomcat-9.0.46/webapps/
