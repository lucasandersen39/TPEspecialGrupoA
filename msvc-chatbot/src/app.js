import express from "express";
import morgan from "morgan";
import cors from "cors";
import chatRoutes from "./routes/chatRoutes.js";

const app = express();

// Middlewares
app.use(cors());
app.use(morgan("dev"));
app.use(express.json());

// Rutas
app.use("/api/chat", chatRoutes);

// Manejo de errores
app.use((err, req, res, next) => {
  console.error(err.stack);
  res.status(500).json({ error: "Error interno del servidor" });
});

export default app;
