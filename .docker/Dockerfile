FROM openjdk:22-jdk

WORKDIR /app

COPY .mvn/ ./.mvn
COPY mvnw pom.xml ./

RUN ["./mvnw", "dependency:go-offline"]

COPY . .

EXPOSE 8080

CMD ["./mvnw", "spring-boot:run"]