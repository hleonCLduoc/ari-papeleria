# Proyecto de Microservicios - DSY1103 (Microservicio Clientes)

## Descripcion del Proyecto
Este proyecto corresponde al microservicio de gestion de clientes, desarrollado con Spring Boot bajo una arquitectura de microservicios. Aplica el principio de "Base de datos por Microservicio", asegurando el aislamiento y la independencia de los datos. Provee una API RESTful para realizar operaciones sobre los clientes del sistema.

## Equipo de Desarrollo
* Hugo Leon
* Ana Farmer
* Dayanne Millar

## Funcionalidades Implementadas
* Arquitectura en capas basada en el patron CSR (Controller, Service, Repository).
* Persistencia de datos utilizando MySQL, Spring Data JPA e Hibernate.
* Migraciones de base de datos automatizadas mediante Flyway (creacion de esquema e insercion de datos iniciales).
* Validaciones de entrada de datos con Bean Validation (JSR 380).
* Manejo centralizado de excepciones mediante @ControllerAdvice y creacion de DTOs de error personalizados.
* Uso de ResponseEntity para el retorno estructurado y estandarizado de codigos HTTP.
* Documentacion interactiva de los endpoints integrada mediante Swagger / OpenAPI.

## Pasos para ejecutar la aplicacion
1. Clonar el repositorio en el entorno local.
2. Abrir el gestor de base de datos MySQL (por ejemplo, MySQL Workbench) y ejecutar el comando: CREATE DATABASE db_clientes;
3. Configurar las credenciales de base de datos (usuario y contrasena) en el archivo src/main/resources/application.properties.
4. Ejecutar el proyecto desde el entorno de desarrollo (IntelliJ IDEA) o mediante Maven. Flyway ejecutara automaticamente los scripts de migracion.
5. Abrir el navegador y acceder a la interfaz de Swagger para probar los endpoints en la ruta correspondiente (por ejemplo: http://localhost:8082/swagger-ui/index.html).
