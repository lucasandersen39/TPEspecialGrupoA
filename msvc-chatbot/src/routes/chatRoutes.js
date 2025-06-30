import express from "express";
import * as chatController from "../controllers/chat.controller.js";
const router = express.Router();

router.post("/chatbot", chatController.getGroqChatCompletion);
router.get("/historial/:id", chatController.getChatByUserId);

export default router;
