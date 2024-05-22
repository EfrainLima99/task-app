# Task-app
this application is a proyect built using Spring Boot MVC and Mybatis

## Requirements

If you want to run this project, you need the following requirements:

* Docker and Docker compose (latest version)
* java 8
* DBeaver (or another)
* some IDE or VS Code

## setup

1. Navigate to the project folder in your terminal.
2. Enter the test folder.
3. Execute the following command:
```bash
$ docker compose up -d
```
4. Open DBeaver (or another database management tool), select the MySQL Connector, and establish a connection using the following credentials:
```
Database name = task_db
Root password = XpAc3x1SB3tT3r1nT3RneT
```
5. execute the following Query: 
```sql
    CREATE TABLE Tasks(
	    id VARCHAR(36) PRIMARY KEY,
	    taskName TEXT,
	    taskDescription TEXT,
	    initDate TEXT,
	    isCloseed TEXT
    );
```
6. Open the task folder with IntelliJ or another IDE or code editor and run the application.

