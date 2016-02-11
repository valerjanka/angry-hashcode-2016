# angry-hashcode-2016
Angry CrossFit Beavers team repo

Project read from input file (default = "in.txt") and write result to out.txt

# How to run
1. CMD:
To run via Maven build tool use next commands:
cd <workspace>
mvn clean package exec:java 

It will read from default in.txt and will write to out.txt
2. IDEA, Eclipse:
Import project like Maven project and run:
2.1 via Maven:
    mvn clean package exec:java
2.2 Solution main class

To change input file add to the end of command -Dexec.args="<file name>"

Maven config:
pom.xml is used to configure all build process.
To perform any build process use appropriate Maven goals

1.  mvn install – compiles classes and puts them into jar file
1.  mvn compile – compiles classes
2.	mvn clean – removed compiled classes and built jar and zip
3.	mvn test – run tests

(see more about Maven at http://maven.apache.org/)

