// controllers/chat.controller.js
import * as chatService from "../services/chat.service.js"; // Importar el servicio de chat

const getGroqChatCompletion = async (req, res) => {
  const { userId, message } = req.body;
  if (!userId || !message) {
    return res.status(400).json({ error: "userId y message son requeridos" });
  }
  try {
    const context =
      "Recuerda que eres un chatbot de asistencia" +
      " técnica para un sistema de gestión de monopatines eléctricos. Tu objetivo es ayudar" +
      " a los usuarios a resolver problemas relacionados con el sistema," +
      "proporcionando respuestas claras y concisas. Si no puedes responder a una" +
      "pregunta, indica que no tienes la información necesaria." +
      "Mensaje: ";

    const chatCompletion = await chatService.getGroqChatCompletion(
      context + message
    );
    if (
      !chatCompletion ||
      !chatCompletion.choices ||
      chatCompletion.choices.length === 0
    ) {
      return res
        .status(404)
        .json({ error: "No se pudo obtener la respuesta del chat" });
    }
    // // Guardar el mensaje del usuario y la respuesta del chat
    const savedUserChat = await saveChatMessage(userId, message, "user");
    // // Aquí podrías guardar también la respuesta del chast si es necesario
    const savedChatbot = await saveChatMessage(
      userId,
      chatCompletion.choices[0].message.content,
      "assistant"
    );
    res.json(chatCompletion.choices[0].message.content || "");
  } catch (error) {
    console.error("Error al obtener respuesta del chat:", error);
    res.status(500).json({ error: "No se pudo obtener la respuesta del chat" });
  }
};

const saveChatMessage = async (userId, message, role) => {
  try {
    const chat = await chatService.saveChatMessage(userId, message, role);
    if (!chat) {
      return false;
    }
    return chat;
  } catch (error) {
    console.error("Error al guardar mensaje:", error);
    res.status(500).json({ error: "No se pudo guardar el mensaje" });
  }
};

const getChatByUserId = async (req, res) => {
  const { id } = req.params;

  try {
    const chatHistory = await chatService.getChatHistoryByUser(id);
    if (!chatHistory || chatHistory.length === 0) {
      return res
        .status(404)
        .json({ message: "No se encontraron chats para este usuario" });
    }
    res.json(chatHistory);
  } catch (error) {
    console.error("Error al obtener chats:", error);
    res.status(500).json({ error: "No se pudo obtener el historial" });
  }
};

export { getChatByUserId, getGroqChatCompletion };
