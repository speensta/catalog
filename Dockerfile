FROM adoptopenjdk/openjdk11:ubi
ADD target/catalog-1.0.jar catalog.jar
ENTRYPOINT ["java","-jar","catalog.jar"]