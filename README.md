# quarkus-memory-leak
A memory leak analysis

## This project is based on a Quarkus quickstarts project
Github repository with [getting-started](https://github.com/quarkusio/quarkus-quickstarts/tree/main/getting-started)

Tag [1.12.1](https://github.com/quarkusio/quarkus-quickstarts/tree/1.12.1.Final/getting-started) 
contains the memory leak under investigation

Tag [1.12.2](https://github.com/quarkusio/quarkus-quickstarts/blob/1.12.2.Final/getting-started/pom.xml)
has the issue fixed!

[Issue reference](https://github.com/quarkusio/quarkus/pull/15546)

# Changes to base project
1. Remove files to the minimum
   
2. Log something to expose the issue
   * For this example we use the default logger `org.jboss.logging.Logger`
   * Quarkus uses [JBoss Log Manager](https://quarkus.io/guides/logging) 
     and the JBoss Logging facade.
     
3. Create a load test
    * For this example we used [jmeter-java-dsl](https://github.com/abstracta/jmeter-java-dsl)
    * check BasicLoadTest.java

# The code
## Build the code with the issue
`cd code`

`mvn clean install`

## Build the code with the issue fixed
`mvn clean install -P withoutIssue`

## Start application
`java -jar target/quarkus-app/quarkus-run.jar`

[Open your browser and validate](http://localhost:8080/hello)

## Reduce heap memory to speed up things
`java -Xms100m -Xmx100m -jar target/quarkus-app/quarkus-run.jar`

# The test written using [jmeter-java-dsl](https://github.com/abstracta/jmeter-java-dsl)
## run tests
`cd load-test`

`mvn test`

## check the report for performance comparisons
`google-chrome html-report/index.html`



