import express from "express";
import * as chatController from "../controllers/chat.controller.js";
const router = express.Router();

/**
 * @description Rutas para manejar las operaciones de chat.
 **/
router.post("/chatbot", chatController.getGroqChatCompletion);

/**
 * @description Rutas para manejar el historial de chat.
 * @param {string} id - ID del usuario para obtener su historial de chat.
 **/
router.get("/historial/:id", chatController.getChatByUserId);

export default router;
