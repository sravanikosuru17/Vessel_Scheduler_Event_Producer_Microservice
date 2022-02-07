FROM openjdk:11
EXPOSE 8080
ADD Poc Demo/Vessel_Scheduler_Event_Producer_Microservice/target/Vessel_Scheduler_Event_Producer_Microservice.jar
ENTRYPOINT ["java","-jar","/Vessel_Scheduler_Event_Producer_Microservice.jar"]