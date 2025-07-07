# Microservicio ChatBot - Grupo A

Este módulo expone una API RESTful para gestionar un chatbot, desarrollada con **NodeJS + MongoDB**.

## Desarrolladores
  
  - Dardo Camaño


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


### Envia un mensaje al chat

- **Método:** `POST`
- **Ruta:** `/api/chat/chatbot`
- **Descripción:** Envia un mensaje y un id de usuario.
- **Body (JSON):** `UsuarioRequestDTO`


## 📦 Estructura esperada de datos
  Formato JSON

### UsuarioRequestDTO (entrada)
```json
{
  "idUser": 2,
  "message": payload,
}
```
---

## Documentación de `docker-compose.yml` para msvc-chatbot

Este archivo `docker-compose.yml` define la configuración de los servicios necesarios para ejecutar el microservicio de chatbot y su base de datos MongoDB.

## Servicios

### 1. node-app-chat

- **Descripción:** Servicio principal de la aplicación Node.js para el chatbot.
- **Build:**
  - `context`: `.` (directorio raíz del proyecto)
  - `dockerfile`: `nodeDocker/Dockerfile`
- **Puertos:** 
  - `3010:3010` (exponiendo el puerto 3010 del contenedor al host)
- **Variables de entorno:**
  - `NODE_ENV=production`
  - `PORT=3010`
- **Volúmenes:**
  - `.:/app` (monta el código fuente para desarrollo)
  - `/app/node_modules` (evita sobrescribir las dependencias instaladas)
- **Restart policy:** `unless-stopped` (reinicia el contenedor a menos que se detenga manualmente)
- **Red:** `msvc_network`
- **Dependencias:** Depende del servicio `mongo` (asegura que MongoDB esté disponible antes de iniciar)

### 2. mongo

- **Descripción:** Servicio de base de datos MongoDB.
- **Build:**
  - `context`: `./mongoDocker`
  - `dockerfile`: `Dockerfile`
- **Logging:** 
  - `driver: "none"` (no muestra logs en consola ni en `docker logs`)
- **Restart policy:** `unless-stopped`
- **Volúmenes:**
  - `./mongo-data:/data/db` (persistencia de datos de MongoDB)
- **Puertos:** 
  - `27017:27017` (exponiendo el puerto estándar de MongoDB)
- **Variables de entorno:**
  - `ME_CONFIG_MONGODB_ADMINUSERNAME=root`
  - `ME_CONFIG_MONGODB_ADMINPASSWORD=example`
  - `ME_CONFIG_BASICAUTH=false`
- **Red:** `msvc_network`

## Volúmenes

- **mongo-data:** Volumen persistente para los datos de MongoDB.

## Redes

- **msvc_network:** Red bridge personalizada para la comunicación entre servicios.


> **Nota:**  
> El archivo está preparado para desarrollo y producción. El volumen de código permite hot-reload en desarrollo, y la política de reinicio
---

# Uso de Groq en el Microservicio de Chatbot

## ¿Qué es *Groq*? 

Groq es una plataforma que ofrece modelos de lenguaje avanzados (LLM) para tareas de procesamiento de lenguaje natural, como generación de texto, chatbots, completado de frases, etc. En este microservicio, se utiliza el SDK oficial de Groq (`groq-sdk`) para interactuar con estos modelos y generar respuestas automáticas a los mensajes de los usuarios.

## ¿Dónde y cómo se utiliza Groq?

### 1. Instalación y Configuración

El SDK de Groq se importa y configura en el archivo [`chat.service.js`](src/services/chat.service.js):

```js
import Groq from "groq-sdk";
import dotenv from "dotenv";
dotenv.config();

const groq = new Groq({
  apiKey: process.env.GROQ_API_KEY,
});
```
> **Nota:**  
> Se utiliza la variable de entorno GROQ_API_KEY para autenticar las peticiones al servicio de Groq que tiene validez por **24hs**


---



