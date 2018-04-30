# Todo

![alt tag](https://travis-ci.org/carloslimasis/todo-list.svg?branch=master)
[![codecov](https://codecov.io/gh/carloslimasis/todo-list/branch/master/graph/badge.svg)](https://codecov.io/gh/carloslimasis/todo-list)

This is a todo list application.

## Requirements

* Maven
* Java 8
* Docker
* Docker Compose

## How It Works

Generates the jar

```sh
$ mvn clean install -Dmaven.test.skip=true
```

Up the services with docker compose

```sh
$ docker-compose up
```

Then, the Todo List can be accessed on:

```
http://localhost:4200
```

The API documentation can be accessed on:

```
http://localhost:8086/swagger-ui.html
```
