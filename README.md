# ConaTrack

[![Build Status](https://travis-ci.org/omoniyi24/ConaTrack.svg?branch=master)](https://travis-ci.org/omoniyi24/ConaTrack)

ConaTrack is a web application(also provides RESTful web service) that helps to limit the spread of coronavirus by it ability to trace the virus' journey around the world in real time.

[![ConaTrack](https://res.cloudinary.com/omoniyi24/image/upload/v1595600474/Screenshot_2020-07-24_at_15.15.27_xrormc.png)](https://omoniyi24.github.io/)

# Features

- Track occurance of virus around the globe in real-time
- Give the difference between occurences of the day and previous day
- Sends real-time recorded cases to email in PDF format

# Technology Used
- Backend: ConaTrack uses Java 11 for it backend programming. Leveraged on the amazing power of the standardized HttpClient for http calls. This feature is known to be served by Java 11.
- Framework: the application is a Spring Boot application uses Maven as build tool, Java 8 stream funtion and lambda expression for some iterations and arithemetical calculations.
- JavaMailSender for sending out emails.
- Thymeleaf as the template engine that helped backend - frontend communication.
- Jasper report engine was used to generate Report in PDF format.
- Junit5 and Mockito for unit Test.
- Jacoco for efficient code review and report analysis.
- Sonarlint was used with IntelliJ to ensure great code quality
- Docker for containerization
- For continuous integration, build and release management. Automatation of builds, testing, and release cycle (Travis CI)
- Frontend: ConaTrack renders HTML5 and CSS.

### Todos

- Write MORE Tests
