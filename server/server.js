const express = require("express");
const mongoose = require("mongoose");
const cors = require("cors");

const app = express();
const PORT = 3000;

// Middleware
app.use(cors());
app.use(express.json());
app.use(express.static("public"));

// MongoDB Connection
mongoose
  .connect("mongodb://127.0.0.1:27017/student_feedback") // Use correct database name
  .then(() => console.log("Connected to MongoDB"))
  .catch((err) => console.error("Error connecting to MongoDB:", err));

// Mongoose Schemas
const FeedbackSchemaA = new mongoose.Schema({
  feedback: String,
  output: Number,
});

const FeedbackSchema = new mongoose.Schema({
  feedback: String,
  output: Number,
  one: Number,
  two: Number,
  three: Number,
  four: Number,
  five: Number,
});

// Mongoose Models
const FeedbackA = mongoose.model("FeedbackA", FeedbackSchemaA, "column_a"); // Schema for pie chart
const FeedbackB = mongoose.model("FeedbackB", FeedbackSchema, "column_b");
const FeedbackC = mongoose.model("FeedbackC", FeedbackSchema, "column_c");
const FeedbackD = mongoose.model("FeedbackD", FeedbackSchema, "column_d");
const FeedbackE = mongoose.model("FeedbackE", FeedbackSchema, "column_e");

// API Endpoints
app.get("/api/data/column_a", async (req, res) => {
  try {
    const data = await FeedbackA.find();
    res.json(data);
  } catch (error) {
    res.status(500).json({ message: "Error retrieving column_a data", error });
  }
});

app.get("/api/data/column_b", async (req, res) => {
  try {
    const data = await FeedbackB.find();
    res.json(data);
  } catch (error) {
    res.status(500).json({ message: "Error retrieving column_b data", error });
  }
});

app.get("/api/data/column_c", async (req, res) => {
  try {
    const data = await FeedbackC.find();
    res.json(data);
  } catch (error) {
    res.status(500).json({ message: "Error retrieving column_c data", error });
  }
});

app.get("/api/data/column_d", async (req, res) => {
  try {
    const data = await FeedbackD.find();
    res.json(data);
  } catch (error) {
    res.status(500).json({ message: "Error retrieving column_d data", error });
  }
});

app.get("/api/data/column_e", async (req, res) => {
  try {
    const data = await FeedbackE.find();
    res.json(data);
  } catch (error) {
    res.status(500).json({ message: "Error retrieving column_e data", error });
  }
});

// Start Server
app.listen(PORT, () => {
  console.log(`Server running at http://localhost:${PORT}`);
});
