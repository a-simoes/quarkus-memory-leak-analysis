# quarkus-memory-leak
A memory leak analysis

## This project is based on a Quarkus quickstarts project
Github repository with [getting-started](https://github.com/quarkusio/quarkus-quickstarts/tree/main/getting-started)

Tag [1.12.1](https://github.com/quarkusio/quarkus-quickstarts/tree/1.12.1.Final/getting-started) 
contains the memory leak we want to investigate

Tag [1.12.2](https://github.com/quarkusio/quarkus-quickstarts/blob/1.12.2.Final/getting-started/pom.xml)
has the issue fixed!

[Issue reference](https://github.com/quarkusio/quarkus/pull/15546)

# Changes to base project
1. Log something to expose the issue
   * For this example we use the default logger
   * Internally, Quarkus uses [JBoss Log Manager](https://quarkus.io/guides/logging)
     and the JBoss Logging facade.
    

2. Create a load test
    * For this example we used [jmeter-java-dsl](https://github.com/abstracta/jmeter-java-dsl)
    * check BasicLoadTest.java


3. Check the results


# Build the version with the issue
mvn clean install -DskipTests

# Build the version with the issue fixed
mvn clean install -DskipTests -P withoutIssue

# Start application
java -jar target/quarkus-app/quarkus-run.jar

[Open your browser and validate](http://localhost:8080/hello)

# Reduce heap memory to speed up things
`java -Xms10m -Xmx200m -jar target/quarkus-app/quarkus-run.jar`



