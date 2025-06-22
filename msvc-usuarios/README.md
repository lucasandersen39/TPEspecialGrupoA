
# Microservicio Usuario - Grupo A

Este módulo expone una API RESTful para gestionar usuarios, desarrollada con **Spring Boot**. Incluye operaciones para listar, buscar, crear, modificar, eliminar y cambiar el estado de usuarios.

## 🌐 Base URL

```
/api/usuario
```
---

## Endpoints

### Listar todos los usuarios

- **Método:** `GET`
- **Ruta:** `/api/usuario`
- **Descripción:** Devuelve una lista de todos los usuarios registrados.
- **Respuesta:** `200 OK`
- **Retorno:** `List<UsuarioResponseDTO>`

### Buscar usuario por ID

- **Método:** `GET`
- **Ruta:** `/api/usuario/{id}`
- **Descripción:** Devuelve los datos del usuario con el ID especificado.
- **Parámetros:**
  - `id`: ID del usuario (Long)
- **Respuesta:** `200 OK` o `404 Not Found`
- **Retorno:** `Optional<UsuarioResponseDTO>`

### Crear nuevo usuario

- **Método:** `POST`
- **Ruta:** `/api/usuario`
- **Descripción:** Crea un nuevo usuario a partir de los datos enviados.
- **Body (JSON):** `UsuarioRequestDTO`
- **Respuesta:** `202 Accepted`
- **Retorno:** `Optional<UsuarioResponseDTO>`

### Modificar usuario existente

- **Método:** `PUT`
- **Ruta:** `/api/usuario/{id}`
- **Descripción:** Modifica un usuario existente.
- **Parámetros:**
  - `id`: ID del usuario (Long)
- **Body (JSON):** `UsuarioRequestDTO`
- **Respuesta:** `202 Accepted`
- **Retorno:** `Optional<UsuarioResponseDTO>`

### Eliminar usuario

- **Método:** `DELETE`
- **Ruta:** `/api/usuario/{id}`
- **Descripción:** Elimina al usuario con el ID especificado.
- **Parámetros:**
  - `id`: ID del usuario (Long)
- **Respuesta:** `200 OK`

### Cambiar estado del usuario (activo/inactivo)

- **Método:** `PUT`
- **Ruta:** `/api/usuario/estado/{id}`
- **Descripción:** Cambia el estado (activo/inactivo) del usuario indicado.
- **Parámetros:**
  - `id`: ID del usuario (Long)
- **Respuesta:** `200 OK`

---

## 📦 Estructura esperada de datos

### UsuarioRequestDTO (entrada)
```json
{
  "nombre": "Federico",
  "apellido": "Cordeiro",
  "email": "fede@example.com",
  "telefono": "1134567890",
  "nombreUsuario": "fedeCorde",
  "x": 34.56,
  "y": -58.44
}
```

### UsuarioResponseDTO (salida)
```json
{
    "nombre": "Federico",
    "apellido": "Cordeiro",
    "email": "fede@example.com",
    "telefono": "1134567890",
    "tipoUsuario": "Basico",
    "nombreUsuario": "fedeCorde",
    "x": 34.56,
    "y": -58.44,
    "dinero": 0.0,
    "fechaAlta": "2025-06-13T21:03:02.9920655",
    "estado": true
}
```

---

## Requisitos

- Java 17+
- Spring Boot 3.5.0
- Maven
- Dependencias:
  - Spring Web
  - Spring Data JPA
  - Jakarta Validation
  - Lombok

---

## Desarrolladores

- Cordeiro, Federico
- Pablo, Piliavsky
