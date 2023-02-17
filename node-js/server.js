const express = require('express');

const app = express();
const PORT = 8080;

const transaction = require("./routes/transaction");
app.use("/transaction", transaction)


app.listen(PORT, (error) => {
    if (!error)
        console.log("Server is Successfully Running, and App is listening on port " + PORT)
    else
        console.log("Error occurred, server can't start", error);
}
);
