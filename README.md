# dvdrental-db-automation

Database testing project using PostgreSQL DVD Rental sample database.

## 🛠 Tech Stack

| Tool | Purpose |
|------|---------|
| Java 17 | Programming language |
| PostgreSQL | Database |
| JDBC | Database connectivity |
| DBUtils | Result set processing |
| Lombok | Boilerplate reduction |
| Maven | Build tool |

## 📋 What's Covered

- Direct SQL queries via JDBC
- Bean mapping with DBUtils
- CSV file generation
- Excel report generation
- CRUD operations on DVD Rental database

## 🚀 How to Run

Configure `src/main/resources/dbconnection.properties`:
```properties
server=localhost
port=5432
user = postgres
password = 54321 
```

Then run:
```bash
mvn compile exec:java
```
