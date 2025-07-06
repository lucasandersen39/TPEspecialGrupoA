import mongoose from "mongoose";

/**
 * @description Conecta a la base de datos MongoDB utilizando Mongoose.
 * @throws {Error} Si ocurre un error al conectar a la base de datos.
 * @returns {Promise<void>} Promesa que se resuelve cuando la conexión es exitosa.
 **/
const connectDB = async () => {
  try {
    await mongoose.connect(process.env.MONGO_URI);
    console.log("MongoDB conectado");
  } catch (error) {
    console.error("Error de conexión a MongoDB:", error);
    process.exit(1);
  }
};

export default connectDB;
