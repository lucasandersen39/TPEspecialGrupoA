import mongoose from "mongoose";

/**
 * @description Esquema de Mongoose para almacenar mensajes de chat.
 * @property {String} userId - ID del usuario que envió el mensaje.
 * @property {String} role - Rol del mensaje, puede ser "user" o "assistant".
 * @property {String} message - Contenido del mensaje de chat.
 * @property {Date} timestamp - Marca de tiempo del mensaje, por defecto es la fecha y hora actuales.
 */
const chatSchema = new mongoose.Schema({
  userId: {
    type: String,
    required: true,
  },
  role: {
    type: String,
    enum: ["user", "assistant"],
    default: "user",
  },
  message: {
    type: String,
    required: true,
  },
  timestamp: {
    type: Date,
    default: Date.now,
  },
});

/**
 * @description Modelo de Mongoose para los mensajes de chat.
 * Este modelo se utiliza para interactuar con la colección de mensajes de chat en la base de datos.
 */
const Chat = mongoose.model("Chat", chatSchema);

/**
 * @description Exporta el modelo de chat para su uso en otras partes de la aplicación.
 */
export default Chat;
