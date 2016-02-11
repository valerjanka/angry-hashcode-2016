# angry-hashcode-2016
Angry CrossFit Beavers team repo

# How to run
To create zip this project you need Maven build tool and run command from workspace
mvn clean install
It will produce zip file for submission.

Maven config:
pom.xml is used to configure all build process.
To perform any build process use appropriate Maven goals

1.  mvn install – compiles classes and puts them into jar file, create needed folders and placed needed files(like libs, configs) in it and after all zipped working project.
1.  mvn compile – compiles classes
2.	mvn clean – removed compiled classes and built jar and zip
3.	mvn test – run tests

(see more about Maven at http://maven.apache.org/)

As a result of executing 'mvn clean install' you will get 'ums-*-dist.zip' file under 'target' folder. 
Inside this zip you`ll have:
bin\ - contains run files for Windows and Linux 
lib\ - libs
logs\ - logs
properties\ - configurations

to run service just execute run file from bin directory.

