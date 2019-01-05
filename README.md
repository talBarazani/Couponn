# springboot-sample-app
/-להתאים את החלק הראשון לאפליקציה שלי
[![Build Status](https://travis-ci.org/codecentric/springboot-sample-app.svg?branch=master)](https://travis-ci.org/codecentric/springboot-sample-app)
[![Coverage Status](https://coveralls.io/repos/github/codecentric/springboot-sample-app/badge.svg?branch=master)](https://coveralls.io/github/codecentric/springboot-sample-app?branch=master)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

Minimal [Spring Boot](http://projects.spring.io/spring-boot/) sample app.

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 4](https://maven.apache.org)
-[mySQL Server version: 8.0.12 MySQL Community Server - GPL] 
-[Eclipse/Spring Tool Suit]

## connect the database to project 
your database should be at localhost:3306
you need to create schema calls-"coupons-app", the application will connect to that scheme and the jpa.dll will auto generate the DB tables when you will first play the application
    
## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.tal.couponsdemo.CouponsDemoApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

* There are 2 jars that should run simultaneously, each in its own port: coupon (port 8080) and income (port 8888) 
```shell
mvn spring-boot:run
```

## Client side
I implement 3 pages one for each customer: admin, company and regular costumer.
Client side implement with HTML 5, jQuery, java script, bootstrap 4 
missing functionality (only on client side): delete customers, view customer details , view income company/admin
