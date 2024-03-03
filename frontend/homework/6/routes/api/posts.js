const express = require("express");
const { v4: uuidv4 } = require('uuid');

const posts = require("../../data/Posts");

const router = express.Router();

//API to get all posts
router.get("/post", (req,res) => {
    res.json(posts);
})

//API to create post
router.post("/post", (req, res) => {
    try {
        const post = {
            id: uuidv4(),
            name: req.body.name,
            content: req.body.content
        }
        posts.push(post);
        res.status(201).json({
            id: post.id
        });
    } catch (err) {
        res.status(400).json(err);
    }
})

//API to get post by Id
router.get("/post/:id", (req, res) => {
    const requestId = req.params.id;
    const post = posts.filter(p => p.id == requestId);
    if (post.length == 0) res.status(404).json("Post with given id not found !!");
    res.json(post[0]);
})

module.exports = router