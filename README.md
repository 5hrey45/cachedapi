# cachedapi
Make APIs performant by caching with caffeine

## Table of contents
- [Caffeine Caching](#caffeine-caching)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
  - [Clone the repository](#clone-the-repository)
  - [PostgreSQL Setup](#postgresql-setup)
    - [Enable logging for debugging](#enable-logging-for-debugging)
  - [Build the project](#build-the-project)
  - [Run the application](#run-the-application)
- [Testing with Postman](#testing-with-postman)
- [Caffeine configuration](#caffeine-configuration)
  - [Lazy caching](#lazy-caching)
  - [Write-through caching](#write-through-caching)
  - [Key difference between eviction policies](#key-difference-between-eviction-policies)
  - [Comparison and Use Cases](#comparison-and-use-cases)

## Caffeine Caching

- The API uses [Caffeine](https://github.com/ben-manes/caffeine) caching to store and retrieve frequently accessed data
- This reduces the load on the database and improves the response time of the API

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

#### Enable logging for debugging
- Uncomment to enable logging of Hibernate JPA for debugging
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

## Caffeine configuration
- You can find the caffeine configuration class under the `cache` package
- The AlbumCache is the cache name and is configured with an initial capacity of 50 and maximum capacity of 250
- The entries will be automatically evicted if any of the below conditons are satisfied:
  - The maximum capacity of cache is exceeded
  - Entry is evicted if the specified duration is elapsed since the entry was last accessed
- Change the cache eviction policy accordingly to your application needs

### Lazy caching
- The caching mechanism is lazy caching, also called lazy population and cache-aside, and the cache is only populated
  when an object is actually requested by the application

### Write-through caching
- I've also used write-through caching, which means the cache is updated in real time when the database is updated

### Key difference between eviction policies
#### Expire after write
- This policy evicts an entry from the cache after a fixed duration has passed since the entry was created or last updated
- The timer starts when an entry is added to the cache or when its value is updated
- It's useful for data that becomes stale after a certain period, regardless of whether it's being accessed
- Example use case:
  Imagine you're caching weather data for cities. You want to ensure that the data is never more than 30 minutes old,
  even if it's frequently accessed.

#### Expire after access
- This policy evicts an entry from the cache after a fixed duration has passed since the entry was last accessed (read or updated).
- The timer resets each time the entry is accessed.
- It's useful for keeping frequently accessed data in the cache longer.
- Example use case:
  Let's say you're caching user profiles. You want to keep profiles in the cache as long as they're being accessed regularly,
  but remove them if they haven't been accessed for an hour.

### Comparison and Use Cases

#### Use expire-after-write when:

- Data has a clear expiration time (like weather forecasts, daily statistics).
- You want to ensure data freshness regardless of access patterns.


#### Use expire-after-access when:

- You want to keep frequently accessed data in cache longer.
- You're dealing with data that does not inherently expire but should be removed if not used (like user sessions).

Both policies can be combined with size-based eviction to prevent unbounded growth of the cache:
```
Caffeine.newBuilder()
    .expireAfterAccess(Duration.ofMinutes(10))
    .expireAfterWrite(Duration.ofHours(1))
    .maximumSize(1000)
    .build();
```
This configuration would evict entries after 10 minutes of no access, or after 1 hour regardless of access,
and ensure the cache never exceeds 1000 entries.