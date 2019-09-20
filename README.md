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
curl http://localhost:8080/api/v1/health
```
In both cases the response should look as follows:
```json
{
  "status": "UP"
}
```