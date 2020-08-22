# What is it?
Spring boot template for new backend project

#What included?
- scala support
- implemented security with pac4j
- cors
- everything is covered with tests

#How to start?
- install java 8
- open project with intellij idea IDE
- let gradle to download all dependencies
- go to settings of IDE, find option "Run tests using" set it to "With Intellij Idea"
- open module settings, install scala 2.13 globally
- change db credentials in application-test.properties, application-local.properties
- you are ready to go, follow all todos in project to override logic that was made for demo purpose

#How to run jar
- run gradle task assemble
- run server with command like this `java -jar -Dspring.profiles.active=local projecttemplateartifact-0.0.1-SNAPSHOT.jar`

#Keep in mind, there are some limitations
- Project written with java and scala
 * use java for DB entities, json responses, and validating data (spring doesn't support well scala in these cases)
 * use scala for writing everything else
- You can't use scala code in java, as scala compiles after java

#Troublshooting
- if some magic error occurred, check if there is java file that imports some scala file
