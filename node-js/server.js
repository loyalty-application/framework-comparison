const express = require('express');
const cors = require("cors");

const app = express();
const PORT = 8080;

app.use(express.json());

// const transaction = require("./routes/transaction");
// app.use("/transaction", transaction)


app.listen(PORT, (error) => {
    if (!error)
        console.log("Server is Successfully Running, and App is listening on port " + PORT)
    else
        console.log("Error occurred, server can't start", error);
});

app.post("/transaction", (req, res) => {
    totalAmount = 0
    for (const key in req.body["transactions"]) {
        if(req.body["transactions"][key]["merchant"] == "A") {
            totalAmount += req.body["transactions"][key]["amount"]
        }
    }
    res.status(200).json({totalAmount})
});

app.post("/transaction/file", (req, res) => {
    
})