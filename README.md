##Description

Microservice for searching if the cities are connected. This application uses in build static file 
for the input but can be extended to implement other repositories like reading from DB etc.

##Usage

Supports one single get operation

```
http://localhost:8080/connected?origin={originCity}&destination={destinationCity}
```

##Build

###Compile
```
mvn clean compile
```

###test
```
mvn clean test
```

###package
Build the war file in the traget directory
```
mvn clean package
```

##Testing
This application has been tested with unit tests for each individual class by mocking services.

###CitySearchControllerTest
Tests all the valid and invalid inputs of the request params and asserts if expected responses are returned.
This test class uses mock service implementation

###LocalFileBasedCityServiceTest 
This test tests the expected responses by loading the city_test.txt file from the respources


### Results with Actual service run

```
http://localhost:8080/connected?origin=Boston&destination=Trenton

no
```

```
http://localhost:8080/connected?origin=Boston&destination=newark

yes
```