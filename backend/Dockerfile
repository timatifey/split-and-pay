FROM gradle:8.4.0-jdk21-alpine as builder
WORKDIR /split-and-pay/backend
COPY . .
RUN gradle clean bootJar

FROM azul/zulu-openjdk-alpine:21-jre
WORKDIR /split-and-pay/backend
COPY --from=builder /split-and-pay/backend/build/libs/backend-0.0.1-SNAPSHOT.jar .
CMD java -jar backend-0.0.1-SNAPSHOT.jar --spring.config.location=classpath:/application.yml,optional:/etc/split-and-pay/backend/application.yml
