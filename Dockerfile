# Step 1: Build the application with Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Step 2: Run the application
FROM eclipse-temurin:17-jdk
WORKDIR /app
# Copy the JAR file built in step 1
COPY --from=build /app/target/*.jar app.jar

# Expose port (Coolify will inject $PORT automatically)
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]

