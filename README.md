# Customer CRM Api
This is a simple light weight Spring Boot application in a Micro services ecosystem powered by Spring Cloud(Eureka and Zuul). Please not that since as per requirements, CRM is not yet ready for exposing REST APIS, I have taken the liberty to implement sample JPA repositories using the in-memory H2 database offered by Spring. 
I have also provided the interface for future CRM API consumption.
Following are the components of the eco system, and it can be easily extended.

  ## eureka-sevice
  * This is a lightweight service registry which makes use of Spring Cloud Eureka for registering clients. 
  **I have also merged an API gateway into this project which makes use of ZUUL proxy. Ideally this should have been a different project, but in the interest of time, I have merged it here.**
  * This project will act as a Eureka Server via the Spring wrapper for Netflix Eureka
  ## customer-api
  * This is a simple Spring boot application that does basic Customer CRUD using Spring MVC and JPA repository, and can be extended for consuming CRM data as it is not yet ready for exposing REST yet.
  * This project will act as a Eureka Client via the Spring wrapper for Netflix Eureka.
  * It will register itself on startup to Eureka and the configuration is specified in the application.yaml.
  * It also contains unit test cases for GET, POST and PUT using MockMVC.
  
