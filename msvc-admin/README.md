# Microservicio de Administración de Tarifas
Este microservicio gestiona las tarifas del sistema, permitiendo crear, modificar, eliminar y actualizar precios.
## Tecnologías
- Java 21
- Spring Boot
- Spring Data JPA
- Lombok
- Jakarta Validation

## Endpoints
### 1. Crear Tarifa
**POST** `/api/admin/tarifas`

**Request:**
``` json
{
    "tipo_tarifa": "RESIDENCIAL",
    "monto": 1500.00,
    "fechaVigencia": "2025-07-01"
}
```
**Response Success: (201 Created)**
``` json
{
    "id": 1,
    "tipo_tarifa": "RESIDENCIAL",
    "monto": 1500.00,
    "fechaVigencia": "2025-07-01"
}
```
**Response Error: (400 Bad Request)**
``` json
{
    "timestamp": "2025-06-13T10:30:45.123Z",
    "status": 400,
    "error": "Bad Request",
    "message": "El monto debe ser positivo",
    "path": "/api/v1/admin"
}
```

### 2. Modificar Tarifa
**PUT** `/api/admin/tarifas/{id}`

**Request:**
``` json
{
    "id": 1,
    "tipo_tarifa": "RESIDENCIAL_PREMIUM",
    "monto": 2000.00,
    "fechaVigencia": "2025-07-01"
}
```
**Response Success: (200 OK)**
``` json
{
    "id": 1,
    "tipo_tarifa": "RESIDENCIAL_PREMIUM",
    "monto": 2000.00,
    "fechaVigencia": "2025-07-01"
}
```
**Response Error: (404 Not Found)**
``` json
{
    "timestamp": "2025-06-13T10:35:45.123Z",
    "status": 404,
    "error": "Not Found",
    "message": "Tarifa no encontrada con ID: 1",
    "path": "/api/v1/admin/1"
}
```
### 3. Eliminar Tarifa
**DELETE** `/api/admin/tarifas/{id}`

**Response Success: (204 No Content)**

**Response Error: (404 Not Found)**
``` json
{
    "timestamp": "2025-06-13T10:40:45.123Z",
    "status": 404,
    "error": "Not Found",
    "message": "Tarifa no encontrada con ID: 1",
    "path": "/api/v1/admin/1"
}
```
### 4. Actualizar Precios
**PUT** `/api/admin/tarifas/actualizar-precios`

**Request:**
``` json
{
    "id": 1,
    "monto": 2500.00,
    "fechaVigencia": "2025-08-01"
}
```
**Response Success: (200 OK)**
``` json
{
    "id": 1,
    "tipo_tarifa": "RESIDENCIAL_PREMIUM",
    "monto": 2500.00,
    "fechaVigencia": "2025-08-01"
}
```
## Manejo de Excepciones
### BusinessValidationException
**Tarifa con monto inválido:**
``` json
{
    "timestamp": "2025-06-13T10:50:45.123Z",
    "status": 400,
    "error": "Bad Request",
    "message": "El monto debe ser positivo",
    "path": "/api/v1/admin"
}
```
### DuplicateEntityException
**Intento de crear tarifa duplicada:**
``` json
{
    "timestamp": "2025-06-13T10:45:45.123Z",
    "status": 409,
    "error": "Conflict",
    "message": "Ya existe una tarifa con el tipo: RESIDENCIAL",
    "path": "/api/v1/admin"
}
```
### ResourceNotFoundException
**Tarifa no encontrada:**
``` json
{
    "timestamp": "2025-06-13T10:40:45.123Z",
    "status": 404,
    "error": "Not Found",
    "message": "Tarifa no encontrada con ID: 1",
    "path": "/api/v1/admin/1"
}
```
### ValidationException
**Fecha de vigencia inválida:**
``` json
{
    "timestamp": "2025-06-13T10:55:45.123Z",
    "status": 400,
    "error": "Bad Request",
    "message": "La fecha de vigencia debe ser actual o futura",
    "path": "/api/v1/admin"
}
```
## Consideraciones
- Todas las fechas deben estar en formato ISO 8601 (YYYY-MM-DD)
- Los montos deben ser positivos
- Los tipos de tarifa deben ser únicos
- Las fechas de vigencia deben ser actuales o futuras
- Se requiere autenticación para todos los endpoints

## Instalación
``` bash
# Clonar el repositorio
git clone https://github.com/tu-usuario/msvc-admin.git

# Navegar al directorio
cd msvc-admin

# Compilar el proyecto
mvn clean install

# Ejecutar la aplicación
mvn spring-boot:run
```
La aplicación estará disponible en `http://localhost:8005`
## Requisitos
- Java Development Kit 21
- Maven 3.9.x
- IDE recomendado: IntelliJ IDEA

