# Endpoints
## User registration

POST localhost:8080/user/createuser

Request 
```dockerfile
{
    "firstName":"John",
    "lastName":"james",
    "email":"john@mail.com",
    "password":"password"
}
```


response
- status code : 200
```dockerfile
{
    "firstName":"John",
    "lastName":"james",
    "email":"john@mail.com",
    "password":"password"
}
```
