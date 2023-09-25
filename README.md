# INFO4 CPOO project

This is the base project your team **MUST** FORK to implement its version of the instant messaging app.

## Server

The server is a Spring Boot app that should ne located in the /server directory.
An embryo allowing to receive an send messages to other domains is provided.

## API and end-to-end tests

The OpenAPI definition of the server API is located in the /api directory.
The /api/e2etests contains Java end-to-end test code of the API (generated using OpenAPI code generator).

## Client

The client is an Angular Web app that should be located in the /client directory.

## Mockups

The static mockups of the UI (images, HTML files...) should be deliveed in the /client/mockups directory.

The "actionable" version of the mockups should be delivered in the /client directory.
It is recommanded but not mandatory to deliver this "actionable" version the first version of the Angular app.

## Router

The message router used by your server to send and receive messages. It is deployed at https://cpoo-router.mightycode.tech/

**This code is provided for information, you don't actually need it to implement your the project.**
