# JavaFX Application

This is a JavaFX application developed using Java version 21. The application is designed to provide a user interface with various functionalities, including user login and account management.

## Features

- User authentication
- Create account functionality
- Password recovery options
- User dashboard

## Requirements

- Java Development Kit (JDK) 21
- Maven (for dependency management and building)

## Git Clone url
- gh repo clone AustinFSSE/FinalProjectSDEV200

## Build, Run & Test
- mvn clean install
- mvn javafx:run
- mvn test

## UML & Flowchart
![image](https://github.com/user-attachments/assets/bdfffa80-55c4-4be1-a3d5-e921ca0c7237)


![image](https://github.com/user-attachments/assets/e66e7847-82dc-4df3-a62f-01cbea988786)



## Dependencies

The following dependencies are required for the project:

```xml
<dependencies>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-controls</artifactId>
        <version>21</version>
    </dependency>
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-core</artifactId>
        <version>5.4.0</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-fxml</artifactId>
        <version>21</version>
    </dependency>
    <dependency>
        <groupId>org.xerial</groupId>
        <artifactId>sqlite-jdbc</artifactId>
        <version>3.41.2.2</version>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <version>${junit.version}</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine</artifactId>
        <version>${junit.version}</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.2</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.testfx</groupId>
        <artifactId>testfx-core</artifactId>
        <version>4.0.16-alpha</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.testfx</groupId>
        <artifactId>testfx-junit5</artifactId>
        <version>4.0.16-alpha</version>
        <scope>test</scope>
    </dependency>
</dependencies>
