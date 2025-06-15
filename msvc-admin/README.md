# Microservicio de Administración de Tarifas

Este microservicio gestiona las tarifas del sistema, permitiendo crear, modificar, eliminar y actualizar precios.

## Tecnologías

- Java 21
- Spring Boot
- Spring Data JPA
- Lombok
- Jakarta Validation

## Endpoints de Tarifas

### Obtener todas las tarifas

Retorna una lista de todas las tarifas disponibles.

**GET**: `/api/admin/tarifas`

**Response Success: (200 OK)**

**Ejemplo de respuesta**:

```json
[
  {
    "tipo_tarifa": "BÁSICA",
    "monto": 500.0
  },
  {
    "tipo_tarifa": "PREMIUM",
    "monto": 250.0
  }
]
```

### Obtener tarifa por ID

Retorna una tarifa específica según su ID.

**GET**: `/api/admin/tarifas/{id}`

**Parámetros URL**: `id`: ID de la tarifa (Long)

**Response Success: (200 OK)**

**Ejemplo de respuesta**:

```json
{
  "tipo_tarifa": "BÁSICA",
  "monto": 500.0
}
```

**Response Error: (404 Not Found)**

### Obtener tarifa por tipo

Retorna una tarifa específica según su tipo.

**GET**: `/api/admin/tarifas/tipo/{tipoTarifa}`

**Parámetros URL**: `tipoTarifa`: Tipo de la tarifa (String)

**Response Success: (200 OK)**

**Ejemplo de respuesta**:
```json
{
  "tipo_tarifa": "PREMIUM",
  "monto": 250.0
}
```

**Response Error: (404 Not Found)**

### Crear Tarifa

Crea una nueva tarifa

**POST** `/api/admin/tarifas`

**Ejemplo de request:**:

``` json
{
    "tipo_tarifa": "PREMIUM",
    "monto": 1500.00
}
```

**Response Success: (201 Created)**

``` json
{
    "tipo_tarifa": "PREMIUM",
    "monto": 1500.00
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

### Modificar Tarifa

**PUT** `/api/admin/tarifas/{id}`

Modifica una tarifa existente

**Parámetros URL**: `id`: ID de la tarifa (Long)

**Ejemplo de request:**

``` json
{
    "tipo_tarifa": "PREMIUM",
    "monto": 2000.00
}
```

**Response Success: (200 OK)**

``` json
{
    "tipo_tarifa": "PREMIUM",
    "monto": 2000.00
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

Elimina una tarifa existente

**DELETE** `/api/admin/tarifas/{id}`

**Parámetros URL**: `id`: ID de la tarifa (Long)

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

Actualiza el precio de la tarifa

**PUT** `/api/admin/tarifas/actualizar-precios/{id}`

**Parámetros URL**: `id`: ID de la tarifa (Long)

**Ejemplo de request:**

``` json
{
    "monto": 2500.00,
}
```

**Response Success: (200 OK)**

``` json
{
    "tipo_tarifa": "PREMIUM",
    "monto": 2500.00,
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

## Consideraciones

- Todas las fechas deben estar en formato ISO 8601 (YYYY-MM-DD)
- Los montos deben ser positivos
- Los tipos de tarifa deben ser únicos
- Las fechas de vigencia deben ser actuales o futuras
- Se requiere autenticación para todos los endpoints

## Instalación

``` bash
# Clonar el repositorio
git clone https://github.com/lucasandersen39/TPEspecialGrupoA.git

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

