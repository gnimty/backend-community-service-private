FROM arm64v8/openjdk:17-ea-16-jdk@sha256:149f7dbd5287cb06efc8c5d0dfffeffcc36e8a9872dca7736ef8c333a3eca6a2

ARG JAR_FILE=build/libs/communityapiserver-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} ./app.jar

ENTRYPOINT ["java", "-jar", "./app.jar"]

EXPOSE 8080