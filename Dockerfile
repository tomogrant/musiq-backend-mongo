### Use to build: docker build -t reviewsapp:latest .
### Use to run: docker run -p9292:9292 reviewsapp:latest

#Type
FROM openjdk:21

#Label
LABEL authors="TGRANT"

#Exposes port
EXPOSE 9292
EXPOSE 27017

#Creates directory
RUN mkdir -p /reviewsapp

#Sets working directory
WORKDIR /reviewsapp

#Copies app to container in /reviewsapp
COPY musiq-backend-mongo/target/reviewsapp.jar /reviewsapp

#Starts app from location when container is run
ENTRYPOINT ["java","-jar","reviewsapp.jar"]
