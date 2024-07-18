# cachedapi
Make APIs performant by caching with caffeine

## Caffeine Caching

- The API uses [Caffeine](https://github.com/ben-manes/caffeine) caching to store and retrieve frequently accessed data
- This reduces the load on the database and improves the response time of the API

### Caffeine configuration
- You can find the caffeine configuration class under the `cache` package
- The AlbumCache is the cache name and is configured with an initial capacity of 50 and maximum capacity of 250
- Change the cache eviction policy accordingly to your application needs

## Prerequisites
- Java 21
- Spring Boot 3 / Spring 6
- PostgreSQL
- Caffeine
- Maven

## Getting Started

### Clone the repository
```
git clone https://github.com/5hrey45/cachedapi.git
cd cachedapi
```

### PostgreSQL Setup
- Create a database in PostgreSQL and dump some initial data
- You can find both the schema.sql and data.sql under sql_scripts
- Update the application.properties file in the `src/main/resources` directory with your PostgreSQL database credentials
```
spring.datasource.url=jdbc:postgresql://localhost:5432/album
spring.datasource.username=postgres
spring.datasource.password=root
```

#### Uncomment to enable logging of Hibernate JPA for debugging

```
# Add logging configs for SQL statements
# log SQL statements
logging.level.org.hibernate.SQL=debug
# log values for SQL statements
logging.level.org.hibernate.orm.jdbc.bind=trace
```

### Build the project
```
mvn clean install
```

### Run the application
```
mvn spring-boot:run
```

## Testing with Postman
- Import the Postman collection available at `postman_collection.json` into Postman
- Use the collection to test the API endpoints
