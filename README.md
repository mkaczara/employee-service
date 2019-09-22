# employee-service


### Build and run
To build project use:
```
mvn clean install
```
To run service after building the project use:
```
docker run -p 8080:8080 -t mkaczara/employee-service:0.0.1-SNAPSHOT
```

### Verify if service works
Simply visit the following address:
```
http://localhost:8080/api/v1/health
```
or use cURL:
```
curl -i -X GET \
 'http://localhost/api/v1/health'
```
In both cases the response should look as follows:
```json
{
  "status": "UP"
}
```

### Sample requests
#### Get all employees
```
curl -i -X GET \
 'http://localhost/api/v1/employee'
```
#### Get single employee 
by id = 5
```
curl -i -X GET \
 'http://localhost/api/v1/employee/5'
```
#### Add new employee
```
curl -i -X POST \
   -H "Content-Type:application/json" \
   -d \
'{
  "firstName": "John",
  "lastName": "Doe",
  "gender": 0,
  "age": 43,
  "addresses": [{
    "streetAddress": "886 Gregory Ave.",
    "city": "San Jose",
    "postCode": "95112",
    "state": "CA"
  }]
}' \
 'http://localhost/api/v1/employee'
```
#### Update the employee
 with id = 11 and its address with id = 12
```
curl -i -X PUT \
   -H "Content-Type:application/json" \
   -d \
'{
  "id": 11,
  "firstName": "John",
  "lastName": "Doe",
  "gender": 0,
  "age": 45,
  "addresses": [{
    "id": 12,
    "streetAddress": "886 Gregory Ave.",
    "city": "San Jose",
    "postCode": "95113",
    "state": "CA"
  }]
}' \
 'http://localhost/api/v1/employee'
```
#### Delete the employee 
with id = 11
```
curl -i -X DELETE \
 'http://localhost/api/v1/employee/11'
```
#### Get average age
using aggregate API 
```
curl -i -X GET \
 'http://localhost/api/v1/employee/aggregate/avg/age'
```
#### Get 404 error code
to check how handler works (assuming employee with id = 1024 doesn't exist)
```
curl -i -X GET \
 'http://localhost/api/v1/employee/1024'
```

### Sample data sources
Employees' first & last names: http://random-name-generator.info/random

Addresses: https://www.randomlists.com/california-address