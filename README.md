checkers
========

Final project for SWE 681 demonstrating secure software practices

## Loading Application on localhost ##
0. `mvn clean test` for sanity check
1. `mvn tomcat:run`
2. Navigate to `localhost:8080/checkers` which will allow you to login or signup for an account
	1. Navigating to any page will only force the login page to be displayed since authentication is necessary
3. Once logged in your profile is displayed along with the ability to view other users in the system... more to come...

## Database Administration ##
### Local H2 Database ###
You can navigate to the database console by accessing `localhost:8080/checkers/admin/h2`

* Driver Class: org.h2.Driver
* JDBC URL: jdbc:h2:file:~/CheckersLocalPersistence
* User Name: user
* Enter password...
