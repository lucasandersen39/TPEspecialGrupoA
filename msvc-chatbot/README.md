# Microservicio ChatBot - Grupo A

Este módulo expone una API RESTful para gestionar un chatbot, desarrollada con **NodeJS + MongoDB**.

## 🌐 Base URL

```
/api/chat
```
---

## Endpoints

### Historial de chats de usuario por ID

- **Método:** `GET`
- **Ruta:** `/api/chat/historial/{id}`
- **Descripción:** Devuelve los datos del usuario con el ID especificado.
- **Parámetros:**
  - `id`: ID del usuario (Long)
- **Respuesta:** `200 OK` o `404 Not Found`
- **Retorno:** 

### Envia un mensaje al chat

- **Método:** `POST`
- **Ruta:** `/api/chat/chatbot`
- **Descripción:** Envia un mensaje y un id de usuario.
- **Body (JSON):** `UsuarioRequestDTO`
- **Respuesta:** `202 Accepted`
- **Retorno:** 

## 📦 Estructura esperada de datos

### UsuarioRequestDTO (entrada)
```json
{
  "idUser": 2,
  "message": payload,
}
```
---

## Requisitos


---

## Desarrolladores

- Dardo Camaño


