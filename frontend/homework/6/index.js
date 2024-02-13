const express = require("express");

const app = express();
const postApi = require("./routes/api/posts")


app.use(express.json())
app.use("/api/v1", postApi);


app.listen(5000, () => {
    console.log("Application started on port 5000");
})