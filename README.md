# ✏️ Ari-Papelería - E-Commerce (Arquitectura de Microservicios)

#### 📖 Descripción del Proyecto
Este proyecto corresponde a la plataforma backend de **Ari-Papelería**, una tienda online especializada en la venta de artículos de escritorio, oficina y papelería. El sistema está desarrollado bajo una estricta **Arquitectura de Microservicios** utilizando Spring Boot.

Para garantizar un alto nivel de aislamiento, escalabilidad y flexibilidad, el proyecto aplica el principio de "Base de datos por Microservicio". Además, implementa el patrón de diseño **CSR** (Controller-Service-Repository), comunicación síncrona entre servicios mediante peticiones REST (Feign Client), y un ecosistema transversal moderno que incluye un API Gateway con seguridad JWT, Service Discovery (Eureka) y pruebas unitarias aisladas.

#### 👥 Equipo de Desarrollo
*   **Estudiante 1:** Hugo León (Scrum Master / Backend Developer)
*   **Estudiante 2:** Ana Farmer (Backend Developer)
*   **Estudiante 3:** Dayanne Millar (Backend Developer)

---

#### ⚙️ Arquitectura y Componentes del Sistema
La arquitectura actual ya no expone las APIs directamente, sino que gestiona el tráfico mediante orquestación. El ecosistema se compone de los siguientes elementos principales:

**1. Componentes Transversales y Orquestación:**
*   **Servidor Eureka (Puerto 8761):** Actúa como *Service Registry* (Directorio de Servicios). Permite que los microservicios se registren dinámicamente al encenderse y otorga tolerancia a fallos.
*   **API Gateway (Puerto 8080):** Es la única puerta de entrada pública para los clientes. Intercepta las peticiones, valida la seguridad (Tokens JWT) y enruta el tráfico dinámicamente hacia los microservicios usando el balanceador de carga (`lb://`).

**2. Microservicios de Dominio (Service Clients):**
Cada servicio cuenta con su propia base de datos aislada para cumplir con la regla de oro de los microservicios:
1.  **Microservicio de Catálogo (Puerto 8081):** Gestión del inventario (cuadernos, lápices, etc.). (BD: `db_catalogo`).
2.  **Microservicio de Clientes (Puerto 8082):** Registro y administración de usuarios. (BD: `db_clientes`).
3.  **Microservicio de Pedidos (Puerto 8083):** Orquestador principal de compras. Usa Feign Client para verificar stock y clientes. (BD: `db_pedidos`).
4.  **Microservicio de Promociones (Puerto 8084):** Gestión de códigos de descuento. (BD: `db_promociones`).
5.  **Microservicio de Reseñas (Puerto 8085):** Calificaciones de artículos comprados. (BD: `db_resenas`).
6.  **Microservicio de Notificaciones (Puerto 8086):** Alertas y comprobantes de compra. (BD: `db_notificaciones`).

---

#### 🛡️ Características Técnicas Transversales
*   **Seguridad Stateless (JWT):** Implementación de Spring Security con JSON Web Tokens para proteger los endpoints. El API Gateway actúa como filtro validador sin depender de sesiones en memoria.
*   **Testing y Calidad de Código:** Cobertura de la lógica de negocio mediante pruebas unitarias con **JUnit 5** y aislamiento de dependencias usando simulacros con **Mockito** (Patrón AAA: Arrange, Act, Assert).
*   **Validaciones:** Uso de *Bean Validation* (JSR 380) en Modelos y DTOs (`@NotNull`, `@NotBlank`, etc.) para proteger la integridad de los datos.
*   **Manejo de Excepciones Globales:** Uso de `@ControllerAdvice` y `ResponseEntity` para estandarizar las respuestas de error (400, 401, 403, 404, 500) en formato JSON.
*   **Trazabilidad:** Integración de logs estructurados mediante **SLF4J**.

---

#### 🚀 Pasos Detallados para Ejecutar la Aplicación
Sigue estas instrucciones para levantar el ecosistema completo de Ari-Papelería en un entorno local:

##### 1. Requisitos Previos
*   **Java:** Versión 17 o superior.
*   **IDE:** IntelliJ IDEA, Eclipse o VS Code.
*   **Base de Datos:** Motor MySQL instalado y corriendo en el puerto 3306.
*   **Herramienta REST:** Postman.

##### 2. Configuración de Base de Datos
Gracias a la propiedad `spring.jpa.hibernate.ddl-auto=update`, **no es necesario crear los esquemas manualmente**. Spring Boot generará las 6 bases de datos al arrancar. *(Asegúrate de que el usuario root/contraseña de MySQL coincida en los `application.yml` o `properties` de cada servicio)*.

##### 3. Orden de Ejecución (¡Importante!)
Para que el descubrimiento dinámico y el enrutamiento funcionen, levanta los proyectos desde tu IDE estrictamente en este orden:
1.  **Ejecutar `ms-eureka-server` (Puerto 8761).** Espera a que termine de iniciar.
2.  **Ejecutar la batería de Microservicios:** Catálogo (8081), Clientes (8082), Pedidos (8083), Promociones (8084), Reseñas (8085) y Notificaciones (8086). 
    * *Nota: Puedes verificar en `http://localhost:8761` que todos aparezcan registrados con estado UP.*
3.  **Ejecutar `ms-api-gateway` (Puerto 8080).**

##### 4. Pruebas de Integración en Postman
En la raíz de este repositorio se incluye la colección de pruebas `AriPapeleria_Postman_Collection.json`.
1.  Importe este archivo en su aplicación Postman.
2.  **Autenticación Inicial:** Realice una petición `POST` a `http://localhost:8080/auth/login` con sus credenciales para obtener el Token JWT.
3.  **Ejecución de Flujo:** Copie el token, configúrelo como *Bearer Token* en la pestaña d
