API Automation with Rest Assured
Overview
      This repository demonstrates how to automate API testing using the Rest Assured library. It includes examples for CRUD operations,
      authentication, and parameterized testing using data providers. The tests are written in Java and executed with TestNG.

Features
          Automated testing for RESTful APIs.
          CRUD operations: Create, Read, Update, Delete.
          Data-driven testing with TestNG data providers.
          Integration with external configuration files for dynamic data management.
          Comprehensive response validation including status codes, headers, and JSON payload.
          Token-based authentication examples.
Technologies Used
          Java 8+: Programming language.
          Rest Assured: For REST API automation.
          TestNG: Test framework.
          Maven: Build and dependency management.
          Log4j: Logging framework.
          JSONPath: For parsing and validating JSON responses.

Getting Started
  Prerequisites
  Ensure the following tools are installed on your system:
      Java 8+
      Maven
      A compatible IDE (e.g., IntelliJ IDEA, Eclipse)
      An API to test (mock server or real API).

Project Structure
    ├── src
    │   ├── test
    │   │   ├── java
    │   │   │   ├── api
    │   │   │   │   ├── endPoints       # REST API endpoint methods
    │   │   │   │   ├── payLoad         # POJO classes for request bodies
    │   │   │   │   ├── tests            # TestNG test cases
    │   │   │   │   ├── utilities       # Utility classes (e.g., property loader)
    │   │   ├── resources    
    │   │   |   │   ├── routes.properties
    │   │   │   │   
    │   │   │   │      
    │   │   │   │   
    ├──Logs
    ├──Reports
    ├──testData
    ├──userTest.xml                      #testNg xml file
    ├── pom.xml                          # Maven dependencies
    ├── README.md                        # Documentation



Setting Up
    Clone the Repository
    git clone https://github.com/your-repo-name.git
    cd your-repo-name

Install Dependencies Use Maven to download required dependencies:
    mvn clean install

Configure Properties Update the src/main/resources/config.properties file with API details:

properties
    base_url=http://example.com/api

Run Tests Execute the test suite using Maven:
    mvn test

Writing Tests
    Example Test: Create User
    Here’s an example of a test case to create a new user:
    @Test(priority = 1, dataProvider = "newUserData", dataProviderClass = DataProviders1.class)
    public void test_createUser(String id, String username, String firstName, String lastName, String email, String password, String phone) {
        // Set up payload
        User user = new User();
        user.setId(Integer.parseInt(id));
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(phone);
    
        // Send request and validate response
        Response response = userEndpoints2.createUser(user);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("username"), username);
    }

Data-Driven Testing with TestNG
    Use a DataProviders1 class for parameterized tests:
    @DataProvider(name = "newUserData")
      public Object[][] provideUserData() {
          return new Object[][] {
              {"1010", "testuser1", "John", "Doe", "john.doe@example.com", "password123", "1234567890"},
              {"1011", "testuser2", "Jane", "Smith", "jane.smith@example.com", "password123", "9876543210"}
          };
      }


Maven Dependencies
    Add the following dependencies to your pom.xml:
    <dependencies>
        <!-- Rest Assured -->
        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>rest-assured</artifactId>
            <version>5.3.0</version>
        </dependency>
        
        <!-- TestNG -->
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>7.7.0</version>
            <scope>test</scope>
        </dependency>
    
        <!-- JSON Parsing -->
        <dependency>
            <groupId>com.jayway.jsonpath</groupId>
            <artifactId>json-path</artifactId>
            <version>2.7.0</version>
        </dependency>
    
        <!-- Logging -->
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-core</artifactId>
            <version>2.20.0</version>
        </dependency>
    </dependencies>


Running Tests in CI/CD
    Integrate with CI/CD pipelines like Jenkins or GitHub Actions:
    Use mvn test as the test command.
    Generate a report using plugins like Surefire or Allure.


Contributing
    Fork the repository.

    Create a feature branch:
      git checkout -b feature-name

    Commit changes:
      git commit -m "Add new feature"

    Push to the branch:
      git push origin feature-name
      Open a Pull Request.

Contact
For questions or feedback, please open an issue.
