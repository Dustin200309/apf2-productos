# APF2 — API REST Gestión de Productos

Continuación del APF1. Java 17, Spring Boot 3.5.5, Maven, Spring Data JPA, H2 en archivo y Spring Security HTTP Basic.

## Ejecución

1. Instala Java 17 y Maven.
2. Define variables de entorno `APF2_ADMIN_PASSWORD` y `APF2_USER_PASSWORD` con contraseñas distintas de **12 caracteres o más**. Ejemplo en Linux/macOS: `export APF2_ADMIN_PASSWORD='CambiarClaveAdmin123!'` y `export APF2_USER_PASSWORD='CambiarClaveUsuario123!'`. No subas claves reales a GitHub.
3. Ejecuta `mvn spring-boot:run`.
4. La base H2 se almacena en `./data/productosdb.mv.db`, persiste al reiniciar y está excluida de Git.

## Autorización

- `GET /api/v1/productos` y `GET /api/v1/productos/{id}`: USER o ADMIN.
- `POST /api/v1/productos`, `PUT /api/v1/productos/{id}`, `DELETE /api/v1/productos/{id}`: solo ADMIN.
- `GET /swagger-ui.html`: solo ADMIN.
- Sin credenciales: 401. USER intentando modificar: 403.
- Autenticación HTTP Basic: `curl -u admin:'TU_CLAVE' http://localhost:8080/api/v1/productos`.

## Ejemplo de creación

`curl -u admin:'TU_CLAVE' -H 'Content-Type: application/json' -d '{"nombre":"Mouse","precio":45.50,"stock":10}' http://localhost:8080/api/v1/productos`

## Verificación

`mvn test` y `mvn spring-boot:run`. Para verificar persistencia, crea un producto, detén y reinicia la aplicación y consulta el mismo ID.

**Nota:** HTTP Basic debe usarse detrás de HTTPS en despliegues reales. El ejemplo es académico/local; no se incluyen credenciales predeterminadas. Los usuarios iniciales se crean solo cuando las variables de entorno están configuradas y no existen ya.

## GitHub

Crea o actualiza un repositorio público, sube únicamente el código fuente y README, y verifica el enlace desde una ventana privada del navegador. No subir `data/`, `.env`, contraseñas ni archivos de compilación.
