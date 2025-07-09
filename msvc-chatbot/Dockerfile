FROM node:22.9.0-alpine
WORKDIR /app
COPY msvc-chatbot/package*.json ./
RUN npm install
RUN npm ci --omit=optional --omit=dev
COPY msvc-chatbot/src ./src

EXPOSE 3010
CMD ["npm", "start"]
