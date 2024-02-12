const http = require('http');
const path = require('path');
const fs = require('fs');
const os = require('os');

//Question 1 : OS Module

function osMethod() {
    const obj = {
        "HostName": os.hostname(),
        "Operating System": os.type(),
        "Architecture": os.arch(),
        "OS Release": os.release(),
        "Uptime": os.uptime(),
        "Number of CPU Cores": os.cpus().length,
        "Total Memory": os.totalmem(),
        "Free Memory": os.freemem(),
        "Current Working Directory": os.tmpdir()
    }
    return obj;
}

function writeJsonToFile(json) {
    fs.writeFile(path.join(__dirname,'os_obj.txt'), json, (err) => {
        if (err) throw err;
        console.log("Json written to file!");
    });
}

const server = http.createServer((req, res) => {
    if (req.url === '/') {
        const json = osMethod();
        writeJsonToFile(JSON.stringify(json));
        fs.readFile(path.join(__dirname, 'os_obj.txt'), (err, content) => {
            if (err) {
                console.error('Error reading file:', err);
                res.writeHead(500, { 'Content-Type': 'text/plain' });
                res.end('Internal Server Error');
            } else {
                res.writeHead(200, { 'Content-Type': 'text/html' });
                res.write("<p>Hello my name is Durvesh!</p>");
                res.write("<p>Here is my system information:</p>");
                res.write("<pre>" + content + "</pre>");
                res.end();
            }
        })
    }
})

const PORT = process.env.PORT || 5000;

server.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
})

//Question 2 : Path Module

function extractFileInfo(filePath) {
    const obj = {
        "extension": path.extname(filePath),
        "baseName": path.basename(filePath),
        "directory": path.dirname(filePath)
    }
    return obj;
}

function processFilePaths(filePaths) {
    let objArray = [];
    for (let filePath of filePaths) {
        objArray.push(extractFileInfo(filePath));
    }
    return objArray;
}

const filePaths = [
    'dir1/dir2/file1.txt',
    'dir1/dir3/file2.txt',
    'dir1/dir3/file3.md',
    'dir4/file4.jpg',
    'dir4/file5.pdf'
];
  
const filePathsInfo = processFilePaths(filePaths);

for (let info of filePathsInfo) {
    console.log(info);
}