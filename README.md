# What is it?
Spring boot template for new backend project

#What included?
- scala support
- implemented security with pac4j
- everything is covered with tests

#How to start?
- open project with intellij idea IDE
- Go to settings of IDE, find option "Run tests using" set it to "With Intellij Idea"
- let gradle to download all dependencies
- open module settings, install scala 2.13 globally
- change db credentials in application-test.properties, application-local.properties
- you are ready to go, follow all todos in project to override logic that was made for demo purpose


#How to run jar
- run gradle task assemble
- run server with command like this `java -jar -Dspring.profiles.active=local projecttemplateartifact-0.0.1-SNAPSHOT.jar`
