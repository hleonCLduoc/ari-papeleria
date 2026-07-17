# ms-api-gateway

API Gateway para los microservicios de **Ari Papeleria**, construido con **Spring Cloud Gateway (WebFlux)** y seguridad por **JWT**, siguiendo el repositorio de ejemplo del profesor (`juazocar/ms-api-gateway`).

Todo el trafico entra por el gateway en el **puerto 8080** y se enruta al microservicio correspondiente.

## Rutas configuradas

| Ruta que expone el gateway        | Microservicio destino | Puerto |
|-----------------------------------|-----------------------|--------|
| `/api/v1/productos/**`            | ms-catalogo           | 8081   |
| `/api/v1/clientes/**`             | ms-cliente            | 8082   |
| `/api/v1/pedidos/**`              | ms-pedidos            | 8083   |
| `/api/v1/promociones/**`          | ms-promociones        | 8084   |
| `/api/v1/resenas/**`              | ms-resenas            | 8085   |
| `/api/v1/notificaciones/**`       | ms-notificaciones     | 8086   |

> Las rutas conservan el path completo (`/api/v1/...`), asi que tus controladores y tu coleccion de Postman siguen funcionando: solo cambia el puerto **8081..8086** por **8080**.

## Seguridad JWT

Todas las rutas exigen un token JWT valido en el header `Authorization: Bearer <token>`.
La unica ruta publica es `/auth/login`.

### 1) Obtener un token

```
POST http://localhost:8080/auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "admin123"
}
```

Respuesta:

```json
{ "token": "eyJhbGci...", "tipo": "Bearer" }
```

### 2) Llamar a un microservicio a traves del gateway

```
GET http://localhost:8080/api/v1/pedidos
Authorization: Bearer eyJhbGci...
```

Sin token (o con token invalido) el gateway responde **401 Unauthorized**.

## Como ejecutar

Requiere **JDK 17**. Levanta primero los microservicios que vayas a usar y luego el gateway:

```bash
# Windows
mvnw.cmd spring-boot:run

# Linux / Mac
./mvnw spring-boot:run
```

## Pruebas unitarias

```bash
mvnw.cmd test
```

Incluye `JwtServiceTest` (generacion y validacion de tokens).

## Configuracion

Todo esta en `src/main/resources/application.yaml`:
- `jwt.secret` y `jwt.expiration-minutes`: parametros del token.
- `gateway.security.public-paths`: rutas sin autenticacion.
- `spring.cloud.gateway.server.webflux.routes`: las 6 rutas.

**Versiones:** Spring Boot 3.5.4 + Spring Cloud 2025.0.0 (Northfields), Java 17.
