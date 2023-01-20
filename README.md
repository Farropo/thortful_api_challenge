# Virtual Reality News API

### Introduction

Hello there!

Just wanted to give a small comment about this API and the
approach behind it. :)

So, starting with the theme...This was supposed to be
an API that would show us the top results, per song, of a 
VR game called "Beat Saber". Unfortunately, the api that i was
experimenting with was not as reliable as i wanted it to be.

With that in mind, i decided to do a post fetcher from the
subreddit "Virtual Reality" and show the top 10 posts of the day.

The API also allow us to get the latest's posts, and get one by id.


About the code itself, nothing too special i believe. I used
webflux and the webclient to fetch the data from the subreddit,
and tried to apply some functional programming concepts (as a way to
also refresh my knowledge about it).

Note: I tried to create a swagger documentation, but i was not able to
get it working for some reason. will leave the libs and the annotations
for the sake of it.


### How to run

To run this application, you will need to have a few things set up on your computer:

Java Development Kit (JDK) version 11 or higher
A Java Integrated Development Environment (IDE) such as Eclipse, IntelliJ IDEA,
or Visual Studio Code might be helpful, but is not required.
Maven, a build automation tool, installed on your computer (3.8.6), or you can use the wrapper

#### Steps:
1. Open a command prompt or terminal window and navigate to the root directory of the application.
2. Run the command "mvn clean install" to build the application // or .mvnw clean install
3. Once the build is successful, run the command "mvn spring-boot:run" to start the application; or .mvnw spring-boot:run 
4. The application should now be running on http://localhost:8080, you can access the endpoints using any rest client
5. You can also create a jar file and run the application using the following command:Copy code
mvn package
This will create a jar file in the target directory. You can run the jar file using the following command:

Copy code
java -jar target/your-jar-file.jar
You may also specify any configuration options using command line arguments.

### Endpoints:
http://localhost:8080/api/virtual-reality/new -> shows the latest 10 posts;
http://localhost:8080/api/virtual-reality/top -> shows the 10 posts with the most upvotes;
http://localhost:8080/api/virtual-reality/{id} -> shows the post with the given id;
