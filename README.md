# Beacon23
Beacon23 - A Vulnerability Finder

A Simple REST API to process National Vulnerability Database - cve modified data feed and 
display that data. A client application can call these APIs to display the data with nice 
visualization. 

The backend Spring Boot application processes the data feed JSON file and stores the data in H2 
In-Memory database (NOTE - this is only for demo purpose and not for production usage).

REST API can retrieve the required data from this in-memory database.

### **Table of Content**
- Getting Started
- Testing
- Deployment
- License
- Acknowledgements

# Getting Started
You can set up this project by importing the source in your favorite IDE. I have also provided a 
docker-compose file and that can be quick way to deploy this project. 

### Prerequisites

#### For Development
- Java 8
- Gradle
- IDE

#### For Deployment
Install Docker and Docker Compose 

# Testing
- If you run the application through docker, it can be accessed at http://localhost:8080

- You can also run the application by downloading this repository and building it on your local 
  environment provided you have gradle.
   - Build a jar ./gradlew clean build
   - cd ./build/libs
   - java -jar beacon23-0.0.1-SNAPSHOT.jar
   - You can access the APIs 
     - List of Vulnerabilities - http://localhost:8080/v1/beacon23/vulnerabilities/list 
     - List of Vulnerabilities published in a date range - 
       http://localhost:8080/v1/beacon23/vulnerabilities/list?fromDate=2021-06-01&toDate=2021-06-09
     - Vulnerability by CVE Id - http://localhost:8080/v1/beacon23/vulnerabilities?cveId=CVE-2021-33742  
    
# Deployment
You can deploy this application using docker-compose easily. 

The application will be accessible at http://localhost:8080/v1/beacon23/vulnerabilities/list

Run from the root directory - 
 - docker-compose build
 - docker-compose up -d

# License 
This project is licensed under the MIT-License - see the [LICENSE](./LICENSE) file for details

# Acknowledgements
- [National Vulnerability Database](https://nvd.nist.gov/vuln/data-feeds)

# Why Beacon23?
Beacon23 is a famous novel by author Huge Howey. In 23rd century, when the humans are 
inter-planetary species, beacons are lighthouses in space saving humans from foreign forces. 
This is a small attempt in the big world of internet - Beacon23.