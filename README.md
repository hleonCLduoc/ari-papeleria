# ✏️ Ari-Papelería - E-Commerce (Arquitectura de Microservicios)

## 📖 Descripción del Proyecto
Este proyecto corresponde a la plataforma backend de **Ari-Papelería**, una tienda online especializada en la venta de artículos de escritorio, oficina y papelería. El sistema está desarrollado bajo una estricta **Arquitectura de Microservicios** utilizando Spring Boot. 

Para garantizar un alto nivel de aislamiento, escalabilidad y flexibilidad, el proyecto aplica el principio de "Base de datos por Microservicio". Además, implementa el patrón de diseño **CSR** (Controller-Service-Repository), comunicación síncrona entre servicios mediante peticiones REST, y un manejo robusto de excepciones y trazabilidad.

## 👥 Equipo de Desarrollo
* **Estudiante 1:** Hugo León (Scrum Master / Backend Developer)
* **Estudiante 2:** [Nombre de tu compañero/a] (Backend Developer)
* **Estudiante 3:** [Nombre de tu compañero/a] (Backend Developer)
* **Enlace a tablero de gestión:** [Pega aquí el enlace a tu Trello/GitHub Project]

---

## ⚙️ Funcionalidades Implementadas

El ecosistema de **Ari-Papelería** se compone de 6 microservicios independientes, cada uno operando en su propio puerto y con su propia base de datos aislada:

1. **Microservicio de Catálogo (Puerto `8081`):** 
   * Gestión completa (CRUD) del inventario de la papelería (cuadernos, lápices, resmas, etc.). 
   * Base de datos independiente: `db_catalogo`.
2. **Microservicio de Clientes (Puerto `8082`):** 
   * Registro y administración de los usuarios que compran en la tienda.
   * Base de datos independiente: `db_clientes`.
3. **Microservicio de Pedidos (Puerto `8083`):** 
   * **(Orquestador Principal)** Procesa las compras de artículos. 
   * Implementa comunicación síncrona mediante **Feign Client / WebClient** para consultar disponibilidad de stock en el Catálogo y validar usuarios en Clientes.
   * Base de datos independiente: `db_pedidos`.
4. **Microservicio de Promociones (Puerto `8084`):** 
   * Gestión de códigos de descuento (ej: "LIBRERIA20") aplicables a las compras.
   * Base de datos independiente: `db_promociones`.
5. **Microservicio de Reseñas (Puerto `8085`):** 
   * Permite a los clientes calificar los artículos de papelería, previa validación de que efectivamente hayan comprado el producto.
   * Base de datos independiente: `db_resenas`.
6. **Microservicio de Notificaciones (Puerto `8086`):** 
   * Encargado de enviar alertas y comprobantes de compra al usuario final.
   * Base de datos independiente: `db_notificaciones`.

### 🛡️ Características Técnicas Transversales
* **Patrón Arquitectónico:** Implementación estricta de Controller, Service y Repository en todos los módulos.
* **Validaciones:** Uso de *Bean Validation* (JSR 380) como `@NotNull`, `@NotBlank` y `@Positive` en Modelos y DTOs para proteger la integridad de las compras.
* **Manejo de Excepciones:** Respuestas estandarizadas usando `@ControllerAdvice` (`GlobalExceptionHandler`) y el uso obligatorio de `ResponseEntity`.
* **Trazabilidad:** Integración de logs estructurados mediante **SLF4J**.

---

## 🚀 Pasos Detallados para Ejecutar la Aplicación

Sigue estas instrucciones para levantar el ecosistema completo de Ari-Papelería en un entorno local:

### 1. Requisitos Previos
* **Java:** Versión 17 o superior.
* **IDE:** IntelliJ IDEA, Eclipse o VS Code.
* **Base de Datos:** Motor MySQL instalado y corriendo en el puerto `3306`.
* **Herramienta REST:** Postman (para pruebas de integración).

### 2. Configuración de Base de Datos
Gracias a la configuración automatizada en los archivos `application.properties` (`createDatabaseIfNotExist=true`), **no es necesario crear las bases de datos manualmente**. Spring Boot se encargará de generar los esquemas de las 6 bases de datos correspondientes al arrancar cada módulo.

*Asegúrese de que el usuario de MySQL tenga la contraseña configurada correctamente en el `application.properties` de cada proyecto.*

*Crear las bd en su motor de bd, usamos para las pruebas MySQL Workbench
* CREATE DATABASE db_catalogo;
* CREATE DATABASE db_clientes;
* CREATE DATABASE db_pedidos;
* CREATE DATABASE db_promociones;
* CREATE DATABASE db_resenas;
* CREATE DATABASE db_notificaciones;

### 3. Ejecución de los Microservicios
Para evitar colisiones de puertos y garantizar que los servicios de orquestación puedan comunicarse con sus proveedores de datos, levante los microservicios desde su IDE en el siguiente orden:

1. Ejecutar el módulo de Catálogo (Iniciará en puerto 8081).
2. Ejecutar el módulo de Clientes (Iniciará en puerto 8082).
3. Ejecutar el módulo de Pedidos (Iniciará en puerto 8083).
4. Ejecutar el módulo de Promociones (Iniciará en puerto 8084).
5. Ejecutar el módulo de Reseñas (Iniciará en puerto 8085).
6. Ejecutar el módulo de Notificaciones (Iniciará en puerto 8086).

### 4. Pruebas de Integración
En la raíz de este repositorio se incluye la colección de pruebas `AriPapeleria_Postman_Collection.json`. 
1. Importe este archivo en su aplicación Postman.
2. Ejecute las peticiones en orden para visualizar el flujo completo: registro de un nuevo
