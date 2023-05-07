FROM gradle:latest as GRADLE_BUILD
COPY ./ ./
RUN gradle build -x test

FROM openjdk:17
COPY --from=GRADLE_BUILD /home/gradle/build/libs/*.jar /opt/driver_service.jar
WORKDIR /opt
CMD ["java", "-jar", "driver_service.jar"]
