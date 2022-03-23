# Azure Function with Java Spring

This is an example a Spring Cloud Azure Function with a little more implementation and tests

## How to build and run the sample

### You'll need the following dependencies

Java
Maven
Terraform

### To build the Java code

From the terminal/console run:

```bash
mvn package
```

### To run the function

```bash
mvn clean && mvn package && mvn azure-functions:run
```

### To deploy the function

1. Deploy with Maven

```bash
mvn azure-functions:deploy
```

2. Deploy with Terraform

```bash
az login
cd infrastructure
terraform apply
```
