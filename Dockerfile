FROM openjdk:22-jdk-slim as build

RUN apt-get update && apt-get install -y wget gnupg2 software-properties-common unzip maven

WORKDIR /app

COPY pom.xml ./
RUN mvn dependency:go-offline

COPY src ./src

RUN mvn package -DskipTests -X

FROM openjdk:22-jdk-slim

WORKDIR /app

COPY --from=build /app/target/notification-0.0.1-SNAPSHOT.jar ./

CMD ["java", "-jar", "notification-0.0.1-SNAPSHOT.jar"]
