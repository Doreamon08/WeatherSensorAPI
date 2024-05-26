## Project Description

This java project simulates the operation of the weather sensor using RestAPI. [Client](https://github.com/Doreamon08/RestAPIConsumer)

### Stack of Technologies Used
1. **Java 17**
2. **Spring Boot 3.1.4**
   - Spring Boot Starter Data JPA
   - Spring Boot Starter Thymeleaf
   - Spring Boot Starter Web
   - Spring Boot Starter Test
3. **PostgreSQL**
4. **Hibernate Validator 8.0.1.Final**
5. **ModelMapper 3.1.1**
6. **Maven**
7. **RestAPI**
8. **Jackson**

### Implemented Features

#### SensorController:
1. **Register a new sensor**:
   - Route: `POST /sensors/registration`
   - Description: Allows users to register a new sensor by providing sensor details in the request body. Performs validation using `SensorValidator` and handles validation errors by throwing a `SensorNotCreatedException`.
   - Response: Returns HTTP status 200 (OK) if the sensor registration is successful.

2. **Handle sensor registration errors**:
   - Description: Handles `SensorNotCreatedException` thrown during sensor registration. Responds with a `SensorErrorResponse` containing the error message and timestamp.
   - Response: Returns HTTP status 400 (Bad Request) with a JSON error response.
  
#### MeasurementsController:
1. **Get all measurements**:
   - Route: `GET /measurements`
   - Description: Retrieves a list of all measurements stored in the system.
   - Response: Returns a list of `MeasurementDTO` objects.

2. **Get count of rainy days**:
   - Route: `GET /measurements/rainyDaysCount`
   - Description: Calculates and returns the count of rainy days based on the measurements.
   - Response: Returns an integer representing the count of rainy days.

3. **Add a new measurement**:
   - Route: `POST /measurements/add`
   - Description: Allows users to add a new measurement to the system by providing measurement details in the request body. Performs validation using `MeasurementValidator` and handles validation errors by throwing a `MeasurementNotCreatedException`.
   - Response: Returns HTTP status 200 (OK) if the measurement addition is successful.

4. **Handle measurement addition errors**:
   - Description: Handles `MeasurementNotCreatedException` thrown during measurement addition. Responds with a `MeasurementErrorResponse` containing the error message and timestamp.
   - Response: Returns HTTP status 400 (Bad Request) with a JSON error response.

#### PeopleController:
1. **Get all people**:
   - Route: `GET /people`
   - Description: Retrieves a list of all people stored in the system.
   - Response: Returns a list of `PersonDTO` objects.

2. **Get a specific person**:
   - Route: `GET /people/{id}`
   - Description: Retrieves information about a specific person based on the provided ID.
   - Response: Returns a `PersonDTO` object representing the requested person.

3. **Create a new person**:
   - Route: `POST /people`
   - Description: Allows users to create a new person in the system by providing person details in the request body. Performs validation and handles validation errors by throwing a `PersonNotCreatedException`.
   - Response: Returns HTTP status 200 (OK) if the person creation is successful.

4. **Handle person not found errors**:
   - Description: Handles `PersonNotFoundException` thrown when a requested person is not found. Responds with a `PersonErrorResponse` indicating that the person with the provided ID was not found.
   - Response: Returns HTTP status 404 (Not Found) with a JSON error response.

5. **Handle person creation errors**:
   - Description: Handles `PersonNotCreatedException` thrown during person creation. Responds with a `PersonErrorResponse` containing the error message and timestamp.
   - Response: Returns HTTP status 400 (Bad Request) with a JSON error response.
  
  ### Installation Instructions

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/yourrepository.git
   cd yourrepository
   ```

2. **Configure the database**:
   - Ensure PostgreSQL is installed and running.
   - Create a database for the project.
   - Update the database configuration in `application.properties`.

3. **Build the project**:
   ```bash
   mvn clean install
   ```

4. **Run the project**:
   ```bash
   mvn spring-boot:run
   ```
