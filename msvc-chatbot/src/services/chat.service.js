import Chat from "../models/chat.model.js";
import Groq from "groq-sdk";
import dotenv from "dotenv";

// Cargar las variables de entorno desde el archivo .env
dotenv.config();

// Configurar el cliente de Groq con la clave de API
const groq = new Groq({
  apiKey: process.env.GROQ_API_KEY,
});

// Función para obtener la respuesta del modelo Groq
const getGroqChatCompletion = async (message) => {
  try {
    const chatCompletion = await groq.chat.completions.create({
      messages: [
        {
          role: "user",
          content: message,
        },
      ],
      model: process.env.GROQ_MODEL || "llama-3.3-70b-versatile",
      temperature: 1,
      max_completion_tokens: 1024,
      top_p: 1,
      stream: false,
      stop: null,
    });
    return chatCompletion;
  } catch (error) {
    throw error;
  }
};

// Función para guardar un mensaje de chat en la base de datos
const saveChatMessage = async (userId, message, role) => {
  try {
    const chat = new Chat({
      userId: userId,
      role: role,
      message,
    });
    const savedChat = await chat.save();
    return savedChat;
  } catch (error) {
    throw error;
  }
};

// Función para obtener el historial de chat por ID de usuario
const getChatHistoryByUser = async (userId) => {
  return await Chat.find({ userId })
    .sort({ timestamp: 1 })
    .populate("userId", "name email"); // si se quiere mostrar nombre/email
};

export { saveChatMessage, getChatHistoryByUser, getGroqChatCompletion };
