# Tech Stack
- Java 17
- Postgresql (Docker container)
- JWT

  # Run project
  - docker-compose up -d
 
# APIs
- `http://localhost:8080/auth/createUser` POST
```json
{
    "name": "Furkan",
    "username": "furkan",
    "password": "pass",
    "authorities": ["USER"]
}
```
- `http://localhost:8080/auth/generateToken` POST --> Generates token
```json
{
    "username": "furkan",
    "password": "pass"
}
```
- `http://localhost:8080/auth/user` GET --> Add token above to Authorization key of the header of the request
