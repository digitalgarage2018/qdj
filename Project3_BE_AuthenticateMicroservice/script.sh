# Server building.
mvn clean
mvn install -DskipTests
# Start the server.
java -jar target/AccountMicroservice-0.0.1-SNAPSHOT.jar
