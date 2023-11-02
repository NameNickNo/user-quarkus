# User-quarkus project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

## Running DB

Run docker container with property:
```shell script
version: "3.8"
services:
  postgres-bank:
    image: postgres:15.1
    environment:
      POSTGRES_DB: user-db
      POSTGRES_USER: user
      POSTGRES_PASSWORD: strong
    volumes: 
      - ./postgres:/var/lib/postgresql/data
    ports:
      - "5429:5432"
```
## Creating table script (For 1st time running!)

Create data in Database console (not migration script)
```shell script
create sequence if not exists user_id_seq;
create table if not exists user_table (
  id bigint primary key DEFAULT nextval('user_id_seq'),
  name varchar(100) not null ,
  email varchar(100) not null unique,
  password varchar(100) not null );
insert into user_table (name, email, password) values ('name', 'email@mail.ru', 'passwordD1');
```
## Running the application in dev mode

```shell script
./mvnw compile quarkus:dev
```

Default request
```shell script
http://localhost:8080/user
```

Swagger UI
```shell script
http://localhost:8080/q/swagger-ui/
```
