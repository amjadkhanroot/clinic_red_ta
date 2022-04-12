FROM maven:3.8-openjdk-17-slim

WORKDIR /clinicapi
COPY . /clinicapi
RUN mvn clean install


CMD mvn spring-boot:run