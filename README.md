# API FORO HUB
## _API que gestiona topicos de un foro sobre cursos TI_

[![N|Solid](https://cldup.com/dTxpPi9lDf.thumb.png)](https://nodesource.com/products/nsolid)

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

Esta es una API REST que gestiona los topicos de un foro, en donde los usuarios deben registrarse para poder crear, actualizar, eliminar, ver todos o un topico en especial sobre cursos TI. Tanto los usuarios, topicos y cursos estaran alojados en una base de datos.

## Caracteristicas
- Implementacion de operaciones CRUD sobre los topicos
- Conexion con una base de datos, en este caso MySQL
- Esta dividida en tres capas principales (web, dominio y persistencia)
- Implementa inyeccion de dependencias por constructor a traves de interfaces para evitar un alto acoplamiento
- Utiliza clases DTO para evitar operar directamente sobre las entidades de la base de datos
- Manejo centralizado de todas las excepciones a traves de Advice Exception Handler
- Validaciones de los datos segun las reglas de negocio
- Conversion de DTOs a entities, y viceversa 
- Implementacion de seguridad mediandte un registro e inicio de sesion proporcionando un token
- Utilizacion JSON Web Token para proteger y validar las solicitudes de los usuarios
- Implementacion de algoritmos de hash para contraseñas

## Tecnologías
Las tecnologias principales que se usaron para el proyecto fueron:
- Java 17 - lenguaje de programacion
- Intellij IDE - editor de codigo
- Spring Boot - framework de Java que simplifica la creación de aplicaciones basadas en Spring
- Spring Web - módulo de Spring Boot que facilita el desarrollo de aplicaciones web, especialmente servicios REST.
- Spring Data JPA - subproyecto de Spring que simplifica el acceso a bases de datos usando JPA
- Spring Validation / Jakarta Validation - sistema de validación de datos basado en anotaciones 
- Spring Security - framework de seguridad para aplicaciones Java
- JSON Web Token - token que se utiliza principalmente para la autenticación y autorización en aplicaciones
- Lombok - librería Java que reduce el código repetitivo
- Maven - sistema de gestión de proyectos y dependencias para Java.
- Flyway - herramienta de migración de bases de datos
- MySQL - sistema de gestión de bases de datos relacional 
- Insomnia - herramienta para realizar test unitarios

## Endpoints
### Para registrar un usuario
##### POST: `http://localhost:8080/foro-hub/api/v1/auth/signup`
Request:
```
{
	"nombre": "Nicolas Orellano",
	"email": "nico@hotmail.com",
	"contrasenia": "1234"
}
```
Response:
```sh
{
	"Mensaje": "Usuario creado exitosamente",
	"Codigo": 201,
	"Status": "Created",
	"Token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJuaWNvZGV2Iiwic3ViIjoiTmljb2xhcyBPcmVsbGFubyIsImF1dGhvcml0aWVzIjoiUk9MRV9VU0VSIiwiaWF0IjoxNzcyMDY0ODAyLCJleHAiOjE3NzIwNjY2MDIsImp0aSI6ImZhMjIxOTNkLWM5OGEtNGUxYi1hOWRjLWQxMWI2YTM0Y2RkMyIsIm5iZiI6MTc3MjA2NDgwMn0.35RTF_kHQNrQL93uanjRJtlBXpO_DtGD26eaHhls2_k"
}
```

### Para iniciar sesion
##### POST: `http://localhost:8080/foro-hub/api/v1/auth/login`
Request:
```
{
	"nombre": "Nicolas Orellano",
	"contrasenia": "1234"
}
```
Response:
```sh
{
	"Codigo": 200,
	"Status": "Ok",
	"Token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJuaWNvZGV2Iiwic3ViIjoiTmljb2xhcyBPcmVsbGFubyIsImF1dGhvcml0aWVzIjoiUk9MRV9VU0VSIiwiaWF0IjoxNzcyMTM5NzAxLCJleHAiOjE3NzIxNDE1MDEsImp0aSI6Ijc2NTFlZmFhLTY5ZTAtNDVhNC1iZjFjLTkxZGI0ZThiODUzZSIsIm5iZiI6MTc3MjEzOTcwMX0.JwEMeTVFOcI3zH9xfCpx4QrcazyRWoLHqcWDXCs_r88",
	"Mensaje": "Login exitoso"
}
```

> A partir de este punto, las solicitudes a la API necesitaran el token generado cuando el usuario se registra por primera
vez o cuando el usuario inicia sesion, en caso que no utilice el token, haya expirado o este mal digitado, se lanzara
una excepcion y no le dara autorizacion para solicitar el recurso.

### Para crear un topico
##### POST: `http://localhost:8080/foro-hub/api/v1/topicos/create`
Request:
```
{
	"titulo": "Titulo 1",
	"mensaje": "Lorem Ipsum is simply dummy text of the printing and \ntypesetting industry. Lorem Ipsum has been the industrys standard",
	 "usuario": {
		 "id": 1,
		 "nombre": "Nicolas Orellano",
		 "email": "nico@hotmail.com"
	 },
	"curso": {
		"id": 4,
		"nombre": "Java",
		"categoria": "Programacion"
	}
}
```
Response:
```sh
{
	"Codigo": 201,
	"Status": "Created",
	"Mensaje": "Tópico creado con éxito"
}
```

### Para mostrar todos los topicos
##### GET: `http://localhost:8080/foro-hub/api/v1/topicos/all`
Response:
```sh
[
	{
		"Titulo": "Titulo 1",
		"Mensaje": "Lorem Ipsum is simply dummy text of the printing and \ntypesetting industry. Lorem Ipsum has been the industrys standard",
		"Fecha de creacion": "2026-02-26T18:05:05.887698",
		"Estado": true,
		"Nombre del autor": "Nicolas Orellano",
		"Nombre del curso": "Java"
	},
	{
		"Titulo": "Titulo 2",
		"Mensaje": "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin",
		"Fecha de creacion": "2026-02-26T18:12:39.315642",
		"Estado": true,
		"Nombre del autor": "Nicolas Orellano",
		"Nombre del curso": "Python"
	}
]
```

### Para mostrar un topico en especifico
##### GET: `http://localhost:8080/foro-hub/api/v1/topicos/2`
Response:
```sh
{
	"Titulo": "Titulo 2",
	"Mensaje": "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin",
	"Fecha de creacion": "2026-02-26T18:12:39.315642",
	"Estado": true,
	"Nombre del autor": "Nicolas Orellano",
	"Nombre del curso": "Python"
}
```

### Para actualizar un topico
##### PUT: `http://localhost:8080/foro-hub/api/v1/topicos/update/1`
Request:
```
{
	"titulo": "Titulo numero 1",
	"mensaje": "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution",
	 "usuario": {
		 "id": 1,
		 "nombre": "Nicolas Orellano",
		 "email": "nico@hotmail.com"
	 },
	"curso": {
		"id": 4,
		"nombre": "Java",
		"categoria": "Programacion"
	}
}
```
Response:
```sh
{
	"Codigo": 200,
	"Status": "Ok",
	"Mensaje": "Tópico actualizado con éxito"
}
```

### Para eliminar un topico
##### DELETE: `http://localhost:8080/foro-hub/api/v1/topicos/delete/1`
Response:
```sh
{
	"Codigo": 200,
	"Status": "Ok",
	"Mensaje": "Tópico eliminado con éxito"
}
```

## Estructura principal del proyecto
```
└── 📁challenge_api_foro_hub
    └── 📁configuration
        └── 📁security
            └── 📁filters
                ├── JwtFilter.java
            ├── SecurityConfig.java
		└── 📁doc
			├── OpenApiConfig.java		
    └── 📁domain
        └── 📁dtos
            └── 📁request
                └── 📁curso
                    ├── CursoRequestDto.java
                └── 📁topico
                    ├── TopicoRequestDto.java
                └── 📁usuario
                    ├── UsuarioAuthRequestDto.java
                    ├── UsuarioLoginRequestDto.java
                    ├── UsuarioRequestDto.java
            └── 📁response
                └── 📁topico
                    ├── TopicoResponseDto.java
                ├── ResponseDto.java
            ├── UsuarioDto.java
        └── 📁services
            └── 📁impl
                ├── TopicoServiceImpl.java
                ├── UsuarioServiceImpl.java
            ├── TopicoService.java
    └── 📁persistence
        └── 📁entities
            ├── CursoEntity.java
            ├── TopicoEntity.java
            ├── UsuarioEntity.java
        └── 📁repositories
            └── 📁impl
                ├── TopicoRepositoryImpl.java
                ├── UsuarioRepositoryImpl.java
            └── 📁jpa
                ├── CursoJpaRepository.java
                ├── TopicoJpaRepository.java
                ├── UsuarioJpaRepository.java
            ├── TopicoRepository.java
            ├── UsuarioRepository.java
    └── 📁util
        └── 📁converters
            └── 📁impl
                ├── CursoConverterImpl.java
                ├── TopicoConverterImpl.java
                ├── UsuarioConverterImpl.java
            ├── CursoConverter.java
            ├── TopicoConverter.java
            ├── UsuarioConverter.java
        ├── JwtUtil.java
        ├── PasswordUtil.java
    └── 📁web
        └── 📁controllers
            ├── AuthController.java
            ├── TopicoController.java
        └── 📁exceptions
            ├── AdviceExceptionHandler.java
            ├── ContraseniaInvalidException.java
            ├── NombreUsuarioInvalidException.java
            ├── TopicoNotFoundException.java
    └── ChallengeApiForoHubApplication.java
```
## Estructura de la base de datos
![Estructura de la Base de Datos](/src/main/resources/img/estructura_bd.jpg)

## Pasos a seguir
- Agregar más roles para que haya usuarios con distintos roles
- Agregar dos entidades más a la base de datos como perfiles y respuestas
- Agregar un mapeador para la conversion de objetos dtos a entities y viceversa
- Agregar paginacion para obtener una cantidad de datos especifica

## License
##### © Nicolas Orellano
