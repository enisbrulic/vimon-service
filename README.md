# vimon-service Project

consolidate gitlab pipeline job services.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
# generate gitlab personal access token: user settings -> Access Tokens 
export GITLAB_READ_API="YOUR GITLAB PERSONAL ACCESS TOKEN WITH SCOPE READ_API"
./start
# or
./mvnw compile quarkus:dev 
```

## Available resources
http://localhost:8080/api/jobs



