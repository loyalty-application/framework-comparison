const express = require('express');
const multer = require('multer');
const csv = require('fast-csv');
const app = express();
const PORT = 8080;

app.use(express.json());
const upload = multer({ dest: 'uploads/' });


app.listen(PORT, (error) => {
    if (!error)
        console.log("Server is Successfully Running, and App is listening on port " + PORT)
    else
        console.log("Error occurred, server can't start", error);
});

app.post("/transaction", (req, res) => {
    totalAmount = 0
    for (const key in req.body["transactions"]) {
        if (req.body["transactions"][key]["merchant"] == "A") {
            totalAmount += req.body["transactions"][key]["amount"]
        }
    }
    res.status(200).json({ totalAmount })
});

app.post("/transaction/file", upload.single('file'), async (req, res) => {
    sum = 0
    await csv.parseFile(req.file.path)
        .on("data", function (data) {
            if (data[2] == 'A') {
                sum += parseFloat(data[5])
            }
        })
        .on("end", function () {
            res.json({ sum });
        })
})