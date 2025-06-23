# API-GATEWAY

## Descripción

API-Gateway es un servicio que actúa como punto de entrada para las solicitudes de los clientes, dirigiéndolas a los servicios backend correspondientes. Este servicio se encarga de la autenticación, autorización y enrutamiento de las solicitudes.

## Librerías y herramientas utilizadas
- **WebFlux**: Para manejar las solicitudes de manera reactiva.
- **Spring cloud gateway**: Para enrutar las solicitudes a los servicios backend. 
Para la versión 3.5 de Spring Boot y 21 de java, se utiliza la versión 2025.0.0 de Spring Cloud Gateway.

## Configuración
Para configurar el API-Gateway, se debe definir el puerto en el que escucha el servicio del api-gateway (Por defecto el 8080) y se deben definir las rutas y filtros en el archivo `application.yml`. Aquí se especifican los servicios backend a los que se dirigirán las solicitudes y las reglas de enrutamiento.
## Ejemplo de configuración
```yaml
server:
  port: 8080
  
spring:
  application:
    name: api-gateway
  cloud:
    gateway:
      routes:
        - id: service1
          uri: lb://service1
          predicates:
            - Path=/service1/**
```

## Servicios y rutas disponibles en la aplicación

Las rutas definidas para la aplicación **Aplicación para Monopatines Eléctricos** se describen a continuación:

- **Servicio de Monopatines**:
    - Ruta: `api/monopatines`
    - Descripción: Este servicio maneja las operaciones relacionadas con los monopatines eléctricos, como la creación, actualización y eliminación de monopatines.
    - Métodos:
        - `GET /api/monopatines`: Obtiene la lista de monopatines.
        - `POST /api/monopatines`: Crea un nuevo monopatín.
        - `PUT /api/monopatines/{id}`: Actualiza un monopatín existente.
        - `DELETE /api/monopatines/{id}`: Elimina un monopatín.
    - Ejemplo:
    ```
    http://localhost:8080/api/monopatines
    ```
  
- **Servicio de Usuarios**:
    - Ruta: `api/usuarios`
    - Descripción: Maneja las operaciones relacionadas con los usuarios, como el registro.
    - Métodos:
        - `GET /api/usuarios`: Obtiene la lista de usuarios.
        - `POST /api/usuarios`: Registra un nuevo usuario.
        - `PUT /api/usuarios/{id}`: Actualiza un usuario existente.
        - `DELETE /api/usuarios/{id}`: Elimina un usuario.
    - Ejemplo:
    ```
    http://localhost:8080/api/usuarios
    ```
- **Servicio de paradas**:
    - Ruta: `api/paradas`
    - Descripción: Maneja las operaciones relacionadas con las paradas de monopatines.
    - Métodos:
        - `GET /api/paradas`: Obtiene la lista de paradas.
        - `POST /api/paradas`: Crea una nueva parada.
        - `PUT /api/paradas/{id}`: Actualiza una parada existente.
        - `DELETE /api/paradas/{id}`: Elimina una parada.
    - Ejemplo:
    ```
    http://localhost:8080/api/paradas
    ```
- **Servicio de Administracion
    - Ruta: `api/administracion`
    - Descripción: Maneja las operaciones de administración, como la gestión de tarifas y de cuentas del sistema.
    - Métodos:
        - `GET /api/administracion`: Obtiene la lista de administradores.
        - `POST /api/administracion`: Crea un nuevo administrador.
        - `PUT /api/administracion/{id}`: Actualiza un administrador existente.
        - `DELETE /api/administracion/{id}`: Elimina un administrador.
    - Ejemplo:
    ```
    http://localhost:8080/api/administracion
    ```