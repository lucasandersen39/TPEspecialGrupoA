import dotenv from "dotenv";
dotenv.config();
import app from "./app.js";
import connectDB from "./utils/db.js";

const PORT = process.env.PORT || 5000;

// Conectar a MongoDB
connectDB();

app.listen(PORT, () => {
  console.log(`Example app listening on port ${PORT}`);
});
