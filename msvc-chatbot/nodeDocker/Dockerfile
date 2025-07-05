# Usa la imagen oficial de Node.js 22.9.0
FROM node:22.9.0-alpine

# Establece el directorio de trabajo en el contenedor
WORKDIR /app

# Copia los archivos de dependencias primero (aprovecha caché de Docker)
COPY ../package*.json ./

# Instala dependencias (omite opcionales y ajusta para producción)
RUN npm ci --omit=optional --omit=dev


# Copia el resto del código de la aplicación
COPY ../src ./src

# Expone el puerto que usa la aplicación (ej: 3000)
EXPOSE 3010

# Comando para iniciar el microservicio (ajusta según tu entrypoint)
CMD ["npm", "start"]
