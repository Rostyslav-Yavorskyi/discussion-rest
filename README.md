## In order to run the app execute the following:

### Docker
1. `git clone https://github.com/Rostyslav-Yavorskyi/discussion-rest.git`
2. `cd discussion-rest/.docker`
3. `docker-compose up`

### Maven

1. `git clone https://github.com/Rostyslav-Yavorskyi/discussion-rest.git`
2. `cd discussion-rest`
3. Install mysql with schema.sql and redis
4. Set environment variables
    - MYSQL_HOST
    - MYSQL_PORT
    - MYSQL_USER
    - MYSQL_PASSWORD
    - REDIS_HOST
    - REDIS_PORT
    - JWT_KEY
5. `./mvnw spring-boot:run`

## Endpoints

### 1. Auth

Default admin: admin@gmail.com:adminpass

`Register: POST http://localhost:8080/auth/register`

Request structure: 
```json
{
    "email": "test@gmail.com",
    "password": "123456",
    "first_name": "Test",
    "last_name": "Test"
}
```

Return structure:
```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2IiwiaWF0IjoxNzIzMjI4MDMyLCJleHAiOjE3MjMzMTQ0MzJ9.3eCdbwK4CxZYohPkqSmlveZfb-kkU4hdPnX8xgI-LO4"
}
```

`Login: POST http://localhost:8080/auth/login`

Request structure:
```json
{
    "email": "test@gmail.com",
    "password": "123456"
}
```

Return structure:
```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNzIzMjI3ODk4LCJleHAiOjE3MjMzMTQyOTh9.J-a4d_Sc5Fux1HlKoNgZmco7_eR0hbdvgmfj3jDblzo"
}
```

### 2. User

`Get me: GET http://localhost:8080/users/me`

Return structure:
```json
{
    "id": 1,
    "email": "test@gmail.com",
    "first_name": "Test",
    "last_name": "Test",
    "role": "USER"
}
```

`Find all users (only for admins): GET http://localhost:8080/users?sort=+role,-id`

`Find all users joined to discussion (only for admins): GET http://localhost:8080/discussions/{id}/users`

`Get user (only for admins): GET http://localhost:8080/users/{id}`

`Update user (only for admins): PUT http://localhost:8080/users/{id}`

Request structure:
```json
{
    "firstName": "Test2",
    "lastName": "Last",
    "role": "ADMIN"
}
```

`Delete user (only for admins): DELETE http://localhost:8080/users/{id}`


### 3. Auditorium

`Create auditorium (only for admins): POST http://localhost:8080/auditoriums`

Request structure:
```json
{
    "number": 107
}
```

Return structure:
```json
{
    "id": 1,
    "number": 107
}
```

`Find all audititoriums (only for admins): GET http://localhost:8080/auditoriums`

`Get auditorium (only for admin): GET http://localhost:8080/auditoriums/{id}`

Return structure:
```json
{
    "id": 1,
    "number": 107
}
```

`Update auditorium (only for admins): PUT http://localhost:8080/auditoriums/{id}`

Request structure:
```json
{
    "number": 107
}
```

`Delete auditorium (only for admins): DELETE http://localhost:8080/auditoriums/{id}`


### 4. Discussion

`Create discussion (only for admins): POST http://localhost:8080/discussions`

Request structure:
```json
{
    "topic": "Discussion Topic",
    "auditorium_id": 1
}
```

Return structure:
```json
{
    "id": 1,
    "topic": "Discussion Topic",
    "auditorium": {
        "id": 1,
        "number": 107
    }
}
```

`Find all discussions: GET http://localhost:8080/discussions`

`Find all discussions I've joined: GET http://localhost:8080/users/discussions`

`Find all discussions a user has joined (only for admins): GET http://localhost:8080/users/{id}/discussions`

`Get discussion: GET http://localhost:8080/discussions/{id}`

`Update discussion: PUT http://localhost:8080/discussions/{id}`

Request structure:
```json
{
    "topic": "New topic",
    "auditorium_id": 2
}
```

`Delete discussion (only for admins): DELETE http://localhost:8080/discussions/{id}`

`Join to discussion: POST http://localhost:8080/discussions/{id}/join`

`Leave from discussion: POST http://localhost:8080/discussions/{id}/leave`