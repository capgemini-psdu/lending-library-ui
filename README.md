# Lending Library application
A trivial implementation of a GOV.UK application using Spark Java.

## Dependencies
There are several different ways of building and running this application but as a minimum you will need Java 8 and internet access.

Ideally you will also have Gradle installed, although you can use the Gradle Wrapper that's bundled with the application instead if you prefer.

Whichever method you use the application will start on the port configured in the Application.java class.

## Building and running with bundled Gradle Wrapper
Use this method if you cannot install Gradle on your target machine.

Simply run the Gradle Wrapper `gradlew` script with the run command:

`gradlew run`

This will download all the application's dependencies from the internet, compile the application and start the application.

## Building and running with Gradle
If you have Gradle installed on your target machine then you can use that to build and then run the application:

`gradle build`

`gradle run`

## API back-end
The UI application makes API calls to an API application, deployed separately, to retrieve the book information.

By default the UI application expects to find the API application on localhost:5678.

If you want to use a different location for the API application then set the API_LOCATION environment variable accordingly e.g.:

`export API_LOCATION=localhost:8080`