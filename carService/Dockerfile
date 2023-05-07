FROM gradle:latest as GRADLE_BUILD
COPY ./ ./
RUN gradle build -x test

FROM openjdk:17
COPY --from=GRADLE_BUILD /home/gradle/build/libs/*.jar /opt/car_service.jar
WORKDIR /opt
CMD ["java", "-jar", "car_service.jar"]
