# 🚌 Microservicio de Paradas - Grupo A

Este microservicio gestiona las **paradas** dentro del sistema de transporte, permitiendo operaciones CRUD a través de una API REST. Forma parte de una arquitectura basada en microservicios para la gestión integral de una red de transporte.

---
## Documentacion Swagger
http://localhost:8003/swagger-ui/

## 🔧 Tecnologías utilizadas

- Java 17+ / Java 21 / Java 24 (según configuración)
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate Validator
- MySQL
- Maven
- Lombok

---

## 📦 Estructura del Proyecto

```plaintext
msvc-paradas
├── controllers
│   └── ParadaController.java
├── entities
│   └── Parada.java
├── repositories
│   └── ParadaRepository.java
└── services   
    ├── dto
    │   └── parada
    │       ├── paradaRequestDTO
    │       │   └── ParadaRequestDTO.java
    │       └── paradaResponseDTO
    │           └── ParadaResponseDTO.java
    └── ParadaService.java / ParadaServiceImpl.java
```


---

## 📌 Endpoints disponibles

### 🔍 GET `/api/parada`
Retorna una lista de todas las paradas registradas.

### 🔍 GET `/api/parada/{id}`
Busca una parada por su ID.

### ➕ POST `/api/parada`
Crea una nueva parada.  
**Body (JSON):**
```json
{
  "nombre": "Parada Central",
  "x": -34.12345,
  "y": -58.67890
}
```
#### 📌 Si ya existe una parada con esas coordenadas `(x, y)`, la creación es rechazada.

### ✏️ PUT `/api/parada/{id}`
Modifica los datos de una parada existente.

### ❌ DELETE `/api/parada/{id}`
Elimina una parada por su ID.

### 📍 GET /api/parada/coordenadas?x={x}&y={y}
Busca una parada por sus coordenadas x e y.

**Respuesta (JSON):**
```json
{
"nombre": "Parada Central",
"x": -34.12345,
"y": -58.6789
}
```

### 📍 GET /api/parada/monopatinesCercanos/x/{x}/y/{y}
Busca una la lista de monopatines que se encuentran en la parada mas cercana.

**Respuesta (JSON):**
```json
{
  "parada": {
    "nombre": "Parada Central",
    "x": -34.12345,
    "y": -58.6789
  },
  "monopatines": [
    {
      "idMonopatin": "1",
      "estado": 1,
      "idParada": 1,
      "kmRecorridos": 12.5,
      "tiempoUsado": 45.0
    },
    {
      "idMonopatin": "2",
      "estado": 2,
      "idParada": 1,
      "kmRecorridos": 20.3,
      "tiempoUsado": 80.5
    }
  ]
}
```