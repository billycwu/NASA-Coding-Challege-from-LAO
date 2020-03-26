# NASA-Coding-Challege-from-LAO
The exercise we’d like to see is to use the NASA API described here to build a project in GitHub that calls the Mars Rover API and selects a picture on a given day. We want your application to download and store each image locally.

Here is an <https://github.com/jlowery457/nasa-exercise | example> of this exercise done by one of our senior developers. This is the level of effort we are looking for from someone who wants to join the LAO development team.

##Acceptance Criteria

Please send a link to the GitHub repo via matt.hawkes@livingasone.com when you are complete.
Use list of dates below to pull the images were captured on that day by reading in a text ﬁle:
	02/27/17
	June 2, 2018
	Jul-13-2016
	April 31, 2018
Language needs to be Java.
We should be able to run and build (if applicable) locally after you submit it
Include relevant documentation (.MD, etc.) in the repo

##Bonus

    Bonus - Unit Tests, Static Analysis, Performance tests or any other things you feel are important for Deﬁnition of Done
    Double Bonus - Have the app display the image in a web browser
    Triple Bonus – Have it run in a Docker or K8s (Preferable)

### Prerequisites

You need the following installed on your local machine, Java 8, Git, Maven.  And make sure port 8080 is not being used locally.

### Building And Running The Project

After using checking out the project using git, use the following command to build,
```
mvn clean install
```
To start the Spring Boot application, use the following command from where you do the build,
```
java -jar target/nasa-thingy-0.0.1-SNAPSHOT.jar
```

### From The Browser
Use the following URL to access the application
```
http://localhost:8080/nasa-pics
```

### About This Exercise
Technologies used:  Java 8, Spring Boot, Angular JS, RESTful Web Services.

This application is basically a gateway to get the Mars Rover pictures through NASA's API (http://api.nasa.gov).  The specific API used for this exercise is,
```
https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?earth_date=2015-6-3&api_key=DEMO_KEY
```
The user can click an image link on the page to see the image through this application.  The application will cache the image locally on the server.  Since most of the images have moved to a different location, a redirect logic was also implemented to go to the new location to download the image.


