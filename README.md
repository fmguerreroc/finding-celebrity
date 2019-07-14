### **Find Celebrity Problem**

- Problem description:
In a team of n people, a celebrity is known by everyone but he/she doesn't know anybody. Find the celebrity!!

- The Algorithm used to solve: Beam Search.

- Platform: Java 8, enhanced by SpringBoot framework.

- DataSource type: H2 in-memory database.

- Prerequisites to run:
Java 1.8 virtual machine, gradle 4.7 or compatible, bin of both added to the system path.

- Building artifact: 
To build the artifact just open a system console and navigate inside the project folder, once there execute the command: *gradle clean build*  . This gonna compile the project, run the unit tests and generate the artifact. This artifact will be located inside de "build/libs" directory into the project folder.

- Executing artifact:
To execute the artifact please go to the artifact folder location and execute the command: *java -jar celebrity-finder-0.0.1-SNAPSHOT.jar* this will startup a tomcat instance with our SpringBoot application, so please be sure that port 8080 not be busy to avoid startup failures. With this instance also gonna be up an H2 database. Please use your browser to access database console via this url: *http://localhost:8080/h2-console* use the below connection config.
1. jdbc url: *jdbc:h2:mem:celebrity_finder_db* 
2. user: user
3. password: password

There are data preloaded but you can add new data using this console.

- Solution description: 
Data storing layer is based on a relational database inside a schema, so i decided to implement the solution in two different ways: 1. Executing the search using backend classes developed for this purpose 2. Executing the search using the database engine via custom JPQL query.

- Invoking the solution:
You can invoke the solution with the browser using these endpoints:
for back-end processing: *http://localhost:8080/get-celebrity-by-backend-processing*

for database processing: http://localhost:8080/get-celebrity-by-database-processing

- Data model:
You can find the ER diagram below.
![image](https://user-images.githubusercontent.com/12778024/61179057-592dfa00-a5c0-11e9-99d6-62e3e861b0cc.png)

- Understanding the response object:
The response object contains 4 fields:
1. celebrity: contains the celebrity object, this is null if there is no celebrity.
2. errorMessage: contains the error message, is null if there is no errors processing.
3. timeToProcessMillis: contains the milliseconds that took the processing. 
4. errorObject: contains the error stack trace, is null if there was no error.
