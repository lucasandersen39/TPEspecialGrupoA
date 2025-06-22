 
# Microservicio: Gestión Viajes
Este proyecto implementa un servicio RESTful que permite gestionar los viajes realizados por usuarios en monopatines, obtener estadísticas de uso, y calcular costos según tarifas vigentes.


## Descripción
Este microservicio forma parte de un sistema más amplio para la gestión de monopatines. Se enfoca en:
- Registrar, modificar y eliminar viajes.
- Obtener reportes de uso y estadísticas.
- Calcular costos según tarifas externas proporcionadas por otro microservicio (`msvc-admin`).

## Requisitos Previos
- **Java Development Kit (JDK):** Versión 21 o superior.
- **Maven:** Versión 3.9.x.
- **MySQL:** Como sistema de base de datos.
- **IntelliJ IDEA:** IDE recomendado para desarrollo.
- **Postman:** Para probar los endpoints proporcionados (opcional).

El servidor estará disponible en [http://localhost:8007]

## Endpoints
### Viajes

| Método | URL | Descripción |
| --- | --- | --- |
| GET | `/api/viaje` | Listar todos los viajes. |
| GET | `/api/viaje/{id}` | Obtener un viaje por su ID. |
| POST | `/api/viaje` | Crear un nuevo viaje. |
| PUT | `/api/viaje/{id}` | Modificar un viaje existente. |
| DELETE | `/api/viaje/{id}` | Eliminar un viaje por su ID. |
| POST | `/api/viaje/costo` | Calcular el costo de un viaje. |
| GET | `/api/viaje/facturacion` | Obtener facturación entre dos fechas. |
#### Ejemplo de Request - Crear Viaje (POST `/api/viaje`)
``` json
{
  "idUsuario": 1,
  "idMonopatin": "MP1234",
  "fechaInicio": "2023-01-01 10:00:00",
  "fechaFin": "2023-01-01 11:00:00",
  "kmRecorridos": 5.0,
  "costoTotal": 20.0
}
```
### Estadísticas de Monopatines

| Método | URL | Descripción |
| --- | --- | --- |
| GET | `/api/viaje/pausas` | Obtener tiempos pausados por monopatín. |
| GET | `/api/viaje/monopatines/mas-viajes` | Monopatines con más viajes en un período. |
### Estadísticas de Usuarios

| Método | URL | Descripción |
| --- | --- | --- |
| POST | `/api/viaje/usuarios/mas-viajes` | Usuarios con más viajes en un período. |
| POST | `/api/viaje/estadisticas-uso` | Obtener estadísticas de uso de monopatines |
#### Ejemplo de Request - Usuarios con Más Viajes (POST `/api/viaje/usuarios/mas-viajes`)
``` json
[
  { "idUsuario": 1 }
]
```
## Manejo de Excepciones
El servicio incluye excepciones personalizadas para una mejor gestión de errores.

| Excepción | Código HTTP | Mensaje |
| --- | --- | --- |
| `IllegalArgumentException` | 400 | Parámetros inválidos en la solicitud. |
| `ViajeNotFoundException` | 404 | "No se encontró el viaje con el ID proporcionado." |
| `ViajeCreationException` | 400 | "Error al intentar crear el viaje." |
| `ViajeUpdateException` | 400 | "Error al intentar actualizar el viaje." |
| `ViajeDeletionException` | 500 | "No se pudo eliminar el viaje debido a un problema." |
Ejemplo de respuesta para un error `404`:
``` json
{
    "timestamp": "2025-06-13T10:35:45.123Z",
    "status": 404,
    "error": "Not Found",
    "message": "No se encontró el viaje con ID: 1",
    "path": "/api/viaje/1"
}
```

## Tecnologías Usadas
- **Java 21**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **Hibernate**
- **MySQL**
- **Jakarta Validation**
- **Lombok**
- **Feign Client** (para comunicación con otros microservicios)
