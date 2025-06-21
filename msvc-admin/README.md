## Microservicio de Administración de Tarifas y Cuentas bancarias

Este microservicio gestiona las tarifas del sistema y las cuentas bancarias, permitiendo crear, modificar, eliminar y actualizarlos respectivamente.

# Endpoints de Tarifas

## Obtener todas las tarifas disponibles

### **GET**: `/api/admin/tarifas`

- **Response Success: `(200 OK)`**

**Ejemplo de respuesta**:

```json
[
  {
    "tipo_tarifa": "PREMIUM",
    "monto": 1600.50,
    "fechaVigencia": "2025-07-26"
  },
  {
    "tipo_tarifa": "BASICA",
    "monto": 2000.00,
    "fechaVigencia": "2025-05-01"
  }
]
```
--- 
## Obtener tarifa por ID

### **GET**: `/api/admin/tarifas/{id}`

**Parámetros URL**: `id`: ID de la tarifa (Long)

Ejemplo de endpoint:
> /api/admin/tipo_tarifas/1

- **Response Success: `(200 OK)`**

**Ejemplo de respuesta**:

```json
{
  "tipo_tarifa": "BASICA",
  "monto": 2000.00,
  "fechaVigencia": "2025-05-01"
}
```

- **Response Error: `(404 Not Found)`**

---

## Obtener tarifa por tipo

### **GET**: `/api/admin/tarifas/tipo/{tipoTarifa}`

**Parámetros URL**: `tipoTarifa`: Tipo de la tarifa (String)

Ejemplo de endpoint:
> /api/admin/tipo_tarifas/tipo/PREMIUM

*(No es case sensitive)*

- **Response Success: `(200 OK)`**

**Ejemplo de respuesta**:
```json
{
  "tipo_tarifa": "BASICA",
  "monto": 2000.00,
  "fechaVigencia": "2025-05-01"
}
```

- **Response Error: `(404 Not Found)`**

--- 

## Crear Tarifa

### **POST** `/api/admin/tarifas`

**Ejemplo de request:**:

``` json
{
  "tipo_tarifa": "PREMIUM",
  "monto": 2000.00,
  "fechaVigencia": "2025-05-03"
}
```

- **Response Success: `(201 Created)`**

``` json
{
  "tipo_tarifa": "PREMIUM",
  "monto": 2000.00,
  "fechaVigencia": "2025-05-03"
}
```

- **Response Error: `(400 Bad Request)`**

``` json
{
    "timestamp": "2025-06-13T10:30:45.123Z",
    "status": 400,
    "error": "Bad Request",
    "message": "El monto debe ser positivo",
    "path": "/api/admin/tarifas"
}
```
---
 
## Modificar Tarifa existente

### **PUT** `/api/admin/tarifas/{id}`

**Parámetros URL**: `id`: ID de la tarifa (Long)

Ejemplo de endpoint:
> /api/admin/tipo_tarifas/1

**Ejemplo de request:**

``` json
{
  "tipo_tarifa": "PREMIUM",
  "monto": 2000.00,
  "fechaVigencia": "2025-05-03"
}
```

- **Response Success: `(200 OK)`**

``` json
{
  "tipo_tarifa": "PREMIUM",
  "monto": 2000.00,
  "fechaVigencia": "2025-05-03"
}
```

- **Response Error: `(404 Not Found)`**

``` json
{
    "timestamp": "2025-06-13T10:35:45.123Z",
    "status": 404,
    "error": "Not Found",
    "message": "Tarifa no encontrada con ID: 1",
    "path": "/api/admin/tarifas/1"
}
```
---

## 3. Eliminar Tarifa existente

### **DELETE** `/api/admin/tarifas/{id}`

**Parámetros URL**: `id`: ID de la tarifa (Long)

Ejemplo de endpoint:
> /api/admin/tipo_tarifas/1

- **Response Success: `(200 OK)`**

``` json
{
  "tipo_tarifa": "PREMIUM",
  "monto": 2000.00,
  "fechaVigencia": "2025-05-03"
}
```

Muestra la tarifa eliminada *si el id existe*. 

- **Response Error: `(404 Not Found)`**

``` json
{
    "timestamp": "2025-06-13T10:40:45.123Z",
    "status": 404,
    "error": "Not Found",
    "message": "Tarifa no encontrada con ID: 1",
    "path": "/api/admin/tarifas/1"
}
```
---

## 4. Actualizar Precios

### **PUT** `/api/admin/tarifas/actualizar-precios/{id}`

**Parámetros URL**: `id`: ID de la tarifa (Long)

Ejemplo de endpoint:
> /api/admin/tipo_tarifas/1

**Ejemplo de request:**

``` json
{
  "tipo_tarifa": "PREMIUM",
  "monto": 2000.00,
  "fechaVigencia": "2025-05-03"
}
```

- **Response Success: `(200 OK)`**

``` json
{
  "tipo_tarifa": "PREMIUM",
  "monto": 2000.00,
  "fechaVigencia": "2025-05-03"
}
```

- **Response Error: `(404 Not Found)`**

``` json
{
    "timestamp": "2025-06-13T10:35:45.123Z",
    "status": 404,
    "error": "Not Found",
    "message": "Tarifa no encontrada con ID: 1",
    "path": "/api/admin/tarifas/1"
}
``` 
---

---
# Endpoints de Tipo Tarifa

## Obtener todos los tipos de tarifa

### **GET**: `/api/admin/tipo_tarifas`

- **Response Success: `(200 OK)`**

**Ejemplo de respuesta**:
``` json
[
    { "tipo_tarifa": "PREMIUM" } ,
    { "tipo_tarifa": "BASICA" }
]
``` 
---
## Obtener tipo tarifa por ID

### **GET**: `/api/admin/tipo_tarifas/{id}`

**Parámetros URL**: `id`: ID del tipo de tarifa (Long)

- **Response Success: `(200 OK)`**

**Ejemplo de respuesta**:
``` json 
{ "tipo_tarifa": "PREMIUM" }
``` 

- **Response Error: `(404 Not Found)`**
``` json
{
    "timestamp": "2025-06-13T10:35:45.123Z",
    "status": 404,
    "error": "Not Found",
    "message": "Tipo tarifa no encontrada con ID: 1",
    "path": "/api/admin/tipo_tarifas/1"
}
```
--- 
## Obtener tipo tarifa por tipo

### **GET**: `/api/admin/tipo_tarifas/tipo/{tipo}`

**Parámetros URL**: `tipo`: Tipo de la tarifa (String)

Ejemplo:
> /api/admin/tipo_tarifas/tipo/PREMIUM

- **Response Success: `(200 OK)`**

**Ejemplo de respuesta**:
``` json 
{ "tipo_tarifa": "PREMIUM" }
``` 

- **Response Error: `(404 Not Found)`**
``` json
{
    "timestamp": "2025-06-13T10:35:45.123Z",
    "status": 404,
    "error": "Not Found",
    "message": "Tipo tarifa no encontrada con ID: 1",
    "path": "/api/admin/tipo_tarifas/1"
}
```
--- 
## Crear Tipo Tarifa

### **POST** `/api/admin/tipo_tarifas`

**Ejemplo de request**:
``` json 
{ "tipo_tarifa": "PREMIUM" }
``` 

- **Response Success: `(201 Created)`**
``` json 
{ "tipo_tarifa": "PREMIUM" }
``` 

- **Response Error: `(400 Bad Request)`**
``` json 
{ 
    "timestamp": "2025-06-13T10:30:45.123Z", 
    "status": 400, 
    "error": "Bad Request", 
    "message": "El campo tipo_tarifa no puede estar vacío", 
    "path": "/api/admin/tipo_tarifas" 
}
``` 
---

## Modificar Tipo Tarifa existente

### **PUT** `/api/admin/tipo_tarifas/{id}`

**Parámetros URL**: `id`: ID del tipo de tarifa (Long)

Ejemplo de endpoint:
> /api/admin/tipo_tarifas/1

**Ejemplo de request**:
``` json 
{ "tipo_tarifa": "PREMIUM_PLUS" }
``` 

- **Response Success: `(200 OK)`**
```json
{ "tipo_tarifa": "PREMIUM_PLUS" }
```

- **Response Error: `(404 Not Found)`**
``` json
{
    "timestamp": "2025-06-13T10:35:45.123Z",
    "status": 404,
    "error": "Not Found",
    "message": "Tipo tarifa no encontrada con ID: 1",
    "path": "/api/admin/tipo_tarifas/1"
}
```
---

## Eliminar Tipo Tarifa existente

### **DELETE** `/api/admin/tipo_tarifas/{id}`

**Parámetros URL**: `id`: ID del tipo de tarifa (Long)

Ejemplo de endpoint:
> /api/admin/tipo_tarifas/tipo/PREMIUM

- **Response Success: `(204 No Content)`**

- **Response Error: `(404 Not Found)`**
```json
{
    "timestamp": "2025-06-13T10:40:45.123Z",
    "status": 404,
    "error": "Not Found",
    "message": "Tipo de tarifa no encontrado con ID: 1",
    "path": "/api/admin/tipo_tarifas/1"
}
```
---

---

# Endpoints de Cuentas

## Obtener todas las cuentas

### **GET**: `/api/admin/cuentas`

- **Response Success: `(200 OK)`**

**Ejemplo de respuesta**:
``` json 
[
    {
        "entidad_bancaria": "mercado pago",
        "numero_cuenta": 2965,
        "saldo": 1500.0,
        "id_titular": "7"
    },
    {
        "entidad_bancaria": "cuenta dni",
        "numero_cuenta": 2966,
        "saldo": 100.0,
        "id_titular": "1"
    }
]
``` 
---
## Obtener cuenta por ID

### **GET**: `/api/admin/cuentas/{id}`

**Parámetros URL**: `id`: ID de la cuenta (Long)

Ejemplo de endpoint:
> /api/admin/cuentas/1

- **Response Success: `(200 OK)`**

**Ejemplo de respuesta**:
``` json 
{ 
    "entidad_bancaria": "Banco Río", 
    "numero_cuenta": 123456, 
    "saldo": 50000.00, 
    "id_titular": "30123456" 
}
``` 

- **Response Error: `(404 Not Found)`**

---
## Obtener cuentas por entidad bancaria

### **GET**: `/api/admin/cuentas/entidad_bancaria/{entidad_bancaria}`

**Parámetros URL**: `entidad_bancaria`: Nombre de la entidad bancaria (String)

Ejemplo de endpoint:
> /api/admin/cuentas/entidad_bancaria/mercado_pago

Con query params para las entidades que tienen espacio en blanco:
> /api/admin/cuentas?entidad_bancaria=mercado+pago  


- **Response Success: `(200 OK)`**

**Ejemplo de respuesta**:
```json 
[
  {
    "entidad_bancaria": "mercado_pago",
    "numero_cuenta": 2966,
    "saldo": 100.0,
    "id_titular": "1"
  },
  {
    "entidad_bancaria": "mercado pago",
    "numero_cuenta": 2965,
    "saldo": 1500.0,
    "id_titular": "7"
  }
]
``` 

---
## Obtener cuenta por número de cuenta

### **GET**: `/api/admin/cuentas/numero_cuenta/{numero_cuenta}`

**Parámetros URL**: `numero_cuenta`: Número de la cuenta (Integer)

Ejemplo de endpoint:
> /api/admin/cuentas/numero_cuenta/123456

- **Response Success: `(200 OK)`**

**Ejemplo de respuesta**:
```json 
{ 
  "entidad_bancaria": "Banco Río", 
  "numero_cuenta": 123456, 
  "saldo": 50000.00, 
  "id_titular": "DNI30123456"
}
``` 

---
## Obtener cuenta por titular

### **GET**: `/api/admin/cuentas/id_titular/{id_titular}`

**Parámetros URL**: `id_titular`: ID del titular de la cuenta (String)

Ejemplo de endpoint:
> /api/admin/cuentas/id_titular/DNI30123456

- **Response Success: `(200 OK)`**

**Ejemplo de respuesta**:
``` json 
{ 
    "entidad_bancaria": "Banco Río", 
    "numero_cuenta": 123456, 
    "saldo": 50000.00, 
    "id_titular": "DNI30123456" 
}
``` 

---
## Crear Cuenta

### **POST** `/api/admin/cuentas`

**Ejemplo de request**:
``` json 
{ 
    "entidad_bancaria": "Banco Río", 
    "numero_cuenta": 123456, 
    "saldo": 50000.00, 
    "id_titular": "DNI30123456" 
}
``` 

- **Response Success: `(201 Created)`**
``` json 
{ 
    "entidad_bancaria": "Banco Río", 
    "numero_cuenta": 123456, 
    "saldo": 50000.00, 
    "id_titular": "DNI30123456" 
}
``` 

- **Response Error: `(400 Bad Request)`**
``` json 
{ 
    "timestamp": "2025-06-13T10:30:45.123Z", 
    "status": 400, 
    "error": "Bad Request", 
    "message": "El saldo debe ser positivo", 
    "path": "/api/admin/cuentas" 
}
``` 

---
## Modificar Cuenta

### **PUT** `/api/admin/cuentas/{id}`

**Parámetros URL**: `id`: ID de la cuenta (Long)

Ejemplo de endpoint:
> /api/admin/cuentas/1

**Ejemplo de request**:
``` json 
{ 
    "entidad_bancaria": "Banco Río", 
    "numero_cuenta": 123456, 
    "saldo": 75000.00, 
    "id_titular": "DNI30123456" 
}
``` 

- **Response Success: `(200 OK)`**
``` json 
{ 
    "entidad_bancaria": "Banco Río", 
    "numero_cuenta": 123456, 
    "saldo": 75000.00, 
    "id_titular": "DNI30123456" 
}
``` 

- **Response Error: `(404 Not Found)`**
```  json 
{ 
    "timestamp": "2025-06-13T10:40:45.123Z", 
    "status": 404, 
    "error": "Not Found", 
    "message": "Cuenta no encontrada con ID: 1", 
    "path": "/api/admin/cuentas/1" 
}
``` 

---
## Eliminar Cuenta

### **DELETE** `/api/admin/cuentas/{id}`

**Parámetros URL**: `id`: ID de la cuenta (Long)

Ejemplo de endpoint:
> /api/admin/cuentas/1

- **Response Success: `(200 OK)`**
``` json 
{ 
    "entidad_bancaria": "Banco Río", 
    "numero_cuenta": 123456, 
    "saldo": 50000.00, 
    "id_titular": "DNI30123456" 
}
``` 

- **Response Error: `(404 Not Found)`**
``` json 
{ 
    "timestamp": "2025-06-13T10:40:45.123Z", 
    "status": 404, 
    "error": "Not Found", 
    "message": "Cuenta no encontrada con ID: 1", 
    "path": "/api/admin/cuentas/1" 
}
```
---

---

## Manejo de Excepciones

### BusinessValidationException

- **Tarifa con monto inválido:**

``` json
{
    "timestamp": "2025-06-13T10:50:45.123Z",
    "status": 400,
    "error": "Bad Request",
    "message": "El monto debe ser positivo",
    "path": "/api/admin/tarifas"
}
```

### ResourceNotFoundException

- **Tarifa no encontrada:**

``` json
{
    "timestamp": "2025-06-13T10:40:45.123Z",
    "status": 404,
    "error": "Not Found",
    "message": "Tarifa no encontrada con ID: 1",
    "path": "/api/admin/tarifas/1"
}
```
---

## Consideraciones

### Tarifas
- Todas las fechas deben estar en formato ISO 8601 (YYYY-MM-DD)
- Los montos deben ser positivos
- Los tipos de tarifa deben existir en la bla tipo_tarifa
- Las fechas de vigencia deben ser actuales o futuras
- Se requiere autenticación para todos los endpoints
### TipoTarifa
- El tipo de tarifa a agregar, modificar no debe existir previamente
- No se pueden eliminar tipos de tarifa que tengan tarifas asociadas al mismo
- Las modificaciones del tipo de tarifa se propagarán en cascada a todos los elementos que esten asociados al mismo
### Cuentas
- El número de cuenta debe ser único
- El id del titular debe ser único
- El saldo debe ser 0 o positivo

---

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

---

## Requisitos

- Java Development Kit 21
- Maven 3.9.x
- IDE recomendado: IntelliJ IDEA

---

## Tecnologías

- Java 21
- Spring Boot
- Spring Data JPA
- Lombok
- Jakarta Validation
