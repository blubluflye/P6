# PayMyBuddy

Web application in java to manage transfer of money with ur contact.

## Description

An in-depth paragraph about your project and overview of use.

## UML of the project
this is the Unified Modeling Language of the project:


![alt tag](https://github.com/blubluflye/P6/blob/main/uml.png)

## PDM of the project

this is the physical data model of PayMyBuddy:


![alt tag](https://github.com/blubluflye/P6/blob/main/mpd.png)

## Getting Started


### Dependencies

written in the pom.xml:
* spring-boot-starter-data-jpa
* spring-boot-starter-oauth2-client
* spring-boot-starter-security
* spring-boot-starter-thymeleaf
* spring-boot-starter-web
* thymeleaf-extras-springsecurity6
* mysql-connector-j
* lombok
* spring-boot-starter-test
* spring-security-test

### Installing

* The directory of the project is needed.
* MYSQL installed on your computer
* setting 4 environment variable:
  * Your MYSQL acces rights with the name DATABASE_USERNAME and DATABASE_PASSWORD
  * your usrl to acces for each database called PAYMYBUDDY_DB_URL of type : "jdbc:mysql://localhost:XXXX/paymybuddy" and PAYMYBUDDY_TEST_DB_URL of type ":jdbc:mysql://localhost:XXXX/paymybuddyTest"
* create 2 empty database called paymybuddy and paymybuddyTest

### Executing program

* going directly to the directory of the poject payMyBuddy and use the commandLine:
```
mvn spring-boot:run
```
then on the browser of ur choice u can use the url  localhost:8080
