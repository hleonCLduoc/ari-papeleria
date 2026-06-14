# Proyecto de Microservicios - DSY1103 (Microservicio Catalogo)

## Descripcion del Proyecto
Este proyecto corresponde al microservicio de gestion de catalogo orientado a productos de libreria, desarrollado con Spring Boot. Cumple con la premisa de mantener una base de datos exclusiva para el servicio, exponiendo la informacion de los productos a traves de una API RESTful segura e independiente.

## Equipo de Desarrollo
* Hugo Leon
* Ana Farmer
* Dayanne Millar

## Funcionalidades Implementadas
* Arquitectura estructurada mediante el patron de diseno CSR (Controller, Service, Repository).
* Conexion y persistencia de datos en entorno MySQL utilizando Spring Data JPA.
* Integracion de Flyway para el versionamiento de la base de datos, incluyendo la generacion de la tabla con campos específicos (sku, estado) y la carga de datos semilla (10 productos de libreria).
* Implementacion de Bean Validation para garantizar la integridad de los datos recibidos.
* Intercepcion global de errores con @ControllerAdvice para respuestas controladas y seguras.
* Respuestas estandarizadas con ResponseEntity, garantizando el uso correcto de los codigos de estado HTTP.
* Integracion con Springdoc OpenAPI (Swagger) para la visualizacion y prueba directa de los endpoints.

## Pasos para ejecutar la aplicacion
1. Clonar el repositorio en el entorno local.
2. Abrir el gestor de base de datos MySQL y crear la base de datos en blanco ejecutando: CREATE DATABASE db_catalogo;
3. Ajustar los parametros de conexion (usuario y contrasena de MySQL) dentro del archivo src/main/resources/application.properties.
4. Iniciar la aplicacion desde el IDE. Al arrancar, Flyway detectara la base de datos vacia, construira la estructura e insertara el stock inicial de productos automaticamente.
5. Ingresar a la interfaz interactiva de Swagger desde el navegador (por ejemplo: http://localhost:8081/swagger-ui/index.html) para visualizar y consumir la API.
