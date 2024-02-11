const button = document.querySelector(".profile-icon");

button.addEventListener("click", () => {
    console.log("Clicked!!");
    const leftPanel = document.getElementById("left");
    leftPanel.style.width = "280px";
    leftPanel.style.zIndex = "1";
    leftPanel.style.position="fixed";
})


// Function to create a new post element
function createPostElement(tweetText) {
    // Replace hashtags with span elements to style them
    tweetText = tweetText.replace(/#(\w+)/g, '<span class="hashtag">#$1</span>');
    // Create elements
    const postDiv = document.createElement("div");
    postDiv.classList.add("each-post");
    const postContent = `
        <img src="images/profile pic.png" alt="" id="profile-image" />
        <div class="post-content-right">
        <div class="post-upper">
            <div class="post-owner-handle">
            <p>Nitesh Gupta <span>@nik_hck</span></p>
            </div>
            <a href="">
            <img src="icons/three_dots.svg" alt="" id="three-dots" />
            </a>
        </div>
        <div class="post-middle">
            <p>${tweetText}</p>
        </div>
        <div class="post-lower">
            <div class="post-actions-links">
            <a href="">
                <img src="icons/comment.svg" alt="" />
            </a>
            <a href="">
                <img src="icons/retweet.svg" alt="" />
            </a>
            <button class="like-button">
                <img src="icons/heart.svg" alt="" id="heart" />
                <span class="like-count"></span>
            </button>
            <a href="">
                <img src="icons/stats.svg" alt="" />
            </a>
            <a href="">
                <img src="icons/bookmark-post.svg" alt="" />
            </a>
            </div>
            <a href="">
            <img src="icons/share.svg" alt="" />
            </a>
        </div>
        </div>
    `;
    postDiv.innerHTML = postContent;
    return postDiv;
}

// Selecting input and button elements
const tweetInput = document.querySelector('.post-input');
const addPostButtons = document.querySelectorAll('.tweet-btns');

// Function to toggle button's disabled property
function toggleButtonState() {
    if (tweetInput.value.trim() !== '') {
        addPostButtons.forEach(function(button) {
            button.removeAttribute('disabled');
            button.classList.add('active-button'); 
            button.classList.remove('inactive-button'); 
        });
      } else {
        addPostButtons.forEach(function(button) {
            button.setAttribute('disabled', 'disabled');
            button.classList.remove('active-button'); 
            button.classList.add('inactive-button'); 
        });
      }
}

// Event listener for input field
tweetInput.addEventListener('input', toggleButtonState);

// Event listener for the add post button
addPostButtons.forEach(function(button) {
    button.addEventListener('click', function() {
        const tweetText = tweetInput.value.trim();
        // If tweet text is not empty
        if (tweetText !== '') {
            const postsContainer = document.querySelector('.posts');
            const newPost = createPostElement(tweetText);
            postsContainer.appendChild(newPost);
            tweetInput.value = "";
            // Disable the buttons again
            addPostButtons.forEach(function(button) {
                button.setAttribute('disabled', 'disabled');
                button.classList.remove('active-button');
                button.classList.add('inactive-button');
            });
            // Selecting all like buttons
            const likeButtons = document.querySelectorAll('.like-button');
            console.log(likeButtons);

            // Adding click event listener to each like button
            likeButtons.forEach(function(button) {
                button.addEventListener('click', function(event) {
                    event.preventDefault(); 
                    // Toggle like status
                    if (button.classList.contains('liked')) {
                        button.classList.remove('liked');
                        const likeCount = button.querySelector('.like-count');
                        likeCount.textContent = '';
                        const img = document.getElementById("heart");
                        img.src = "icons/heart.svg";
                    } else {
                        button.classList.add('liked');
                        console.log("clicked")
                        const likeCount = button.querySelector('.like-count');
                        likeCount.textContent = 1;
                        const img = document.getElementById("heart");
                        img.src = "icons/heart_full.svg";
                    }
                });
            });
        }
    });
});

