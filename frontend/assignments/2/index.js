let user = JSON.parse(localStorage.getItem('user'));

const socket = io("http://localhost:5000");

socket.on('connect', () => {
    console.log('Connected with socket ID:', socket.id);
});

console.log(socket.id);

socket.emit("storeSocketId", user)

let selectedChat;

//////////////////////// Set users profile and name on home page ////////////////

const profileImage = document.querySelectorAll(".profile-image");
profileImage.forEach(image => {
    image.src = user.img;
})

const userName = document.querySelector(".user-name");
const nameHandle = document.querySelector(".name-handle");

const displayNameUserName = document.createElement("p");
displayNameUserName.innerText = user.name;
const handleUserName = document.createElement("p");
handleUserName.innerText = user.handle;

userName.appendChild(displayNameUserName);
userName.appendChild(handleUserName);

const displayNameNameHandle = document.createElement("p");
displayNameNameHandle.innerText = user.name;
const handleNameHandle = document.createElement("p");
handleNameHandle.innerText = user.handle;

nameHandle.appendChild(displayNameNameHandle);
nameHandle.appendChild(handleNameHandle);

/////////////////////////////////////////////////////////////////////////////////

const mobileMessage = document.querySelector("#mobile-message");
mobileMessage.addEventListener("click", (event) => {
    event.preventDefault();
    const activeUsersMobile = document.querySelector(".active-users");
    const rightPanel = document.querySelector(".rightPanel");
    activeUsersMobile.style.display = "block";
    rightPanel.style.display = "none";
})


const button = document.getElementById("profile-icon");

button.addEventListener("click", () => {
    const leftPanel = document.getElementById("left");
    leftPanel.style.width = "280px";
    leftPanel.style.zIndex = "1";
    leftPanel.style.position="fixed";
})

const button1 = document.getElementById("profile-icon-mobile");

button1.addEventListener("click", () => {
    const leftPanel = document.getElementById("left");
    leftPanel.style.width = "0px";
    leftPanel.style.zIndex = "0";
    leftPanel.style.position="static";
})

// Selecting input and button elements
const tweetInput = document.querySelector('.post-input');
const addPostButtons = document.querySelectorAll('.tweet-btn');

// Function to toggle post button's disabled property
function toggleButtonState(input,button) {
    if (input.value.trim() !== '') {
        button.removeAttribute('disabled');
        button.classList.add('active-button'); // Add a class to indicate button is active
        button.classList.remove('inactive-button'); // Remove a class to indicate button is inactive
      } else {
        button.setAttribute('disabled', 'disabled');
        button.classList.remove('active-button'); // Remove a class to indicate button is active
        button.classList.add('inactive-button'); // Add a class to indicate button is inactive
      }
}

// Event listener for input field
tweetInput.addEventListener('input', () => {
    toggleButtonState(tweetInput, addPostButtons[0]);
});

// Function to create a new post element
function createPostElement(post) {
    // Replace hashtags with span elements to style them
    let tweetText = post.content.replace(/#(\w+)/g, '<span class="hashtag">#$1</span>');
    // Create elements
    const postDiv = document.createElement("div");
    postDiv.classList.add("each-post");

    // Create elements
    const imgProfile = document.createElement('img');
    imgProfile.src = post.img;
    imgProfile.alt = '';
    imgProfile.classList.add('profile-image');

    const divPostContentRight = document.createElement('div');
    divPostContentRight.classList.add('post-content-right');

    const divPostUpper = document.createElement('div');
    divPostUpper.classList.add('post-upper');

    const divPostOwnerHandle = document.createElement('div');
    divPostOwnerHandle.classList.add('post-owner-handle');

    const pOwnerHandle = document.createElement('p');
    pOwnerHandle.textContent = post.userName;
    const spanHandle = document.createElement('span');
    spanHandle.textContent = post.userHandle;
    pOwnerHandle.appendChild(spanHandle);
    divPostOwnerHandle.appendChild(pOwnerHandle);

    const aThreeDots = document.createElement('a');
    aThreeDots.href = '';
    const imgThreeDots = document.createElement('img');
    imgThreeDots.src = 'icons/three_dots.svg';
    imgThreeDots.alt = '';
    imgThreeDots.id = 'three-dots';
    aThreeDots.appendChild(imgThreeDots);

    divPostUpper.appendChild(divPostOwnerHandle);
    divPostUpper.appendChild(aThreeDots);

    const divPostMiddle = document.createElement('div');
    divPostMiddle.classList.add('post-middle');
    const pTweetText = document.createElement('p');
    pTweetText.textContent = tweetText;
    const imgTweet = document.createElement("img");
    imgTweet.src = post.imageContent;

    divPostMiddle.appendChild(pTweetText);
    if(post.imageContent)divPostMiddle.appendChild(imgTweet);

    const divPostLower = document.createElement('div');
    divPostLower.classList.add('post-lower');

    const divPostActionsLinks = document.createElement('div');
    divPostActionsLinks.classList.add('post-actions-links');

    const aComment = document.createElement('a');
    aComment.href = '';
    const imgComment = document.createElement('img');
    imgComment.src = 'icons/comment.svg';
    imgComment.alt = '';
    aComment.appendChild(imgComment);

    const aRetweet = document.createElement('a');
    aRetweet.href = '';
    const imgRetweet = document.createElement('img');
    imgRetweet.src = 'icons/retweet.svg';
    imgRetweet.alt = '';
    aRetweet.appendChild(imgRetweet);

    const buttonLike = document.createElement('button');
    buttonLike.classList.add('like-button');
    const imgHeart = document.createElement('img');
    imgHeart.src = 'icons/heart.svg';
    imgHeart.alt = '';
    imgHeart.id = 'heart';
    const spanLikeCount = document.createElement('span');
    spanLikeCount.classList.add('like-count');
    spanLikeCount.textContent = '0'; 
    buttonLike.appendChild(imgHeart);
    buttonLike.appendChild(spanLikeCount);

    buttonLike.addEventListener('click', () => {
        const currentLikes = parseInt(spanLikeCount.textContent);
        const isLiked = buttonLike.classList.contains('liked');

        if (isLiked) {
            // If already liked, decrement the like count and remove the 'liked' class
            spanLikeCount.textContent = currentLikes - 1;
            buttonLike.classList.remove('liked');
            imgHeart.src = 'icons/heart.svg'
        } else {
            // If not liked, increment the like count and add the 'liked' class
            spanLikeCount.textContent = currentLikes + 1;
            buttonLike.classList.add('liked');
            imgHeart.src = 'icons/heart_full.svg'
        }
    }); 

    const aStats = document.createElement('a');
    aStats.href = '';
    const imgStats = document.createElement('img');
    imgStats.src = 'icons/stats.svg';
    imgStats.alt = '';
    aStats.appendChild(imgStats);

    const aBookmarkPost = document.createElement('a');
    aBookmarkPost.href = '';
    const imgBookmarkPost = document.createElement('img');
    imgBookmarkPost.src = 'icons/bookmark-post.svg';
    imgBookmarkPost.alt = '';
    aBookmarkPost.appendChild(imgBookmarkPost);

    divPostActionsLinks.appendChild(aComment);
    divPostActionsLinks.appendChild(aRetweet);
    divPostActionsLinks.appendChild(buttonLike);
    divPostActionsLinks.appendChild(aStats);
    divPostActionsLinks.appendChild(aBookmarkPost);

    const aShare = document.createElement('a');
    aShare.href = '';
    const imgShare = document.createElement('img');
    imgShare.src = 'icons/share.svg';
    imgShare.alt = '';
    aShare.appendChild(imgShare);

    divPostLower.appendChild(divPostActionsLinks);
    divPostLower.appendChild(aShare);

    divPostContentRight.appendChild(divPostUpper);
    divPostContentRight.appendChild(divPostMiddle);
    divPostContentRight.appendChild(divPostLower);

    // Append elements to the document
    postDiv.appendChild(imgProfile);
    postDiv.appendChild(divPostContentRight);
    return postDiv;
}

////////////////// Infinite Scrolling of posts ///////////////////////

// Function to show loading indicator
function showLoadingIndicator() {
    const loadingIndicator = document.getElementById('loadingIndicator');
    loadingIndicator.style.display = 'flex';
}
  
// Function to hide loading indicator
function hideLoadingIndicator() {
    const loadingIndicator = document.getElementById('loadingIndicator');
    loadingIndicator.style.display = 'none';
}

let page = 1;
let pageSize = 5;
let loading = false; // Flag to prevent multiple simultaneous requests

// Function to fetch more posts
async function fetchPosts() {
    if (loading) return; // If already loading, do nothing
    loading = true;

    // Make a request to the backend API
    await fetch(`http://localhost:5000/api/posts?page=${page}&pageSize=${pageSize}`)
        .then(response => response.json())
        .then(data => {
            const posts = data.posts;

            // Render the fetched posts (you need to implement this)
            // renderPosts(posts);
            let postsContainer = document.querySelector('.posts');
            posts.forEach(post => {
                const newPost = createPostElement(post);
                postsContainer.appendChild(newPost);
            });
            
            // Update page count for next request
            page++;

            loading = false; // Reset loading flag
        })
        .catch(error => {
            console.error('Error fetching posts:', error);
            loading = false; // Reset loading flag
        });
}



// Function to check if user has scrolled to the bottom of the page
function checkScroll() {
    const scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
    const windowHeight = window.innerHeight;
    const scrollHeight = document.documentElement.scrollHeight;

    if (scrollTop + windowHeight >= scrollHeight - 50) {
        fetchPosts(); // Fetch more posts when near the bottom
    }
}

// Event listener for scrolling
window.addEventListener('scroll', checkScroll);

// Initial fetch of posts
fetchPosts();


///////////////////////// Create new posts /////////////////////////////////

// Event listener for the add post button
addPostButtons.forEach(function(button) {
    button.addEventListener('click', function() {
        let tweetText = tweetInput.value.trim();
        if (tweetText === '') {
            alert("Post content is empty !!");
            return;
        }

        fetch('http://localhost:5000/api/posts', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                content: tweetText,
                user: user
            })  
        })
        .then(response => response.json())
        .then(data => {
            console.log('New post created:', data.post);
        })
        .catch(error => {
            console.error('Error creating post:', error);
        });

        tweetInput.value = "";
        addPostButtons.forEach(function(button) {
            button.setAttribute('disabled', 'disabled');
            button.classList.remove('active-button'); // Remove a class to indicate button is active
            button.classList.add('inactive-button'); // Add a class to indicate button is inactive
        });

        let postsContainer = document.querySelector('.posts');
        postsContainer.innerHTML = '';

        console.log("cleared the posts section");
        page = 1;
        pageSize = 5;
        fetchPosts();
        console.log("filled the posts section");
    });
});

//////////////////////// MessagesBTN in NavBar //////////////////////////////////

const messagesBtn = document.getElementById("messages");

const messageSection = document.querySelector(".message-section");
const activeUsersPane = document.querySelector(".active-users");
const userChats = document.querySelector(".user-chats");
const rightPanel = document.querySelector(".rightPanel");

messagesBtn.addEventListener("click", (event) => {
    event.preventDefault();
    messageSection.style.width = "100%";
    rightPanel.style.display = "none";
    activeUsersPane.style.display = "block";
    if (window.innerWidth > 414) {
        userChats.style.display = "block";
    } 
});

/////////////////////////// Function to modify conversations window for each user ////////////////

// Function to switch users based on socket ID
function switchUser(socketSelf,socketOther) {
    fetch(`http://localhost:5000/api/chats/${socketSelf}/${socketOther}`)
        .then(response => response.json())
        .then(data => {
            console.log(data);
            createChatWindow(data, socketOther);
        })
        .catch(error => console.error('Error fetching chat messages:', error));
}
/////////////////// Send message (DM)  //////////////////

function formatRelativeTime(messageTime) {
    const currentTime = new Date();
    
    // Parse hours, minutes, and period from the messageTime string
    const [time, period] = messageTime.split(' ');
    const [hour, minutes] = time.split(':');
    
    // Convert hours and minutes to numbers
    let messageHour = parseInt(hour);
    const messageMinutes = parseInt(minutes);

    // Adjust message hour if it's PM
    if (period === 'PM') {
        messageHour = messageHour%12 + 12; // Add 12 hours to convert to 24-hour format
    }

    // Calculate the difference between the current time and message time
    const currentHour = currentTime.getHours();
    const currentMinutes = currentTime.getMinutes();
    const minutesDifference = (currentHour - messageHour) * 60 + (currentMinutes - messageMinutes);

    // Calculate relative time based on the difference
    if (minutesDifference < 1) {
        return 'Just now';
    } else if (minutesDifference < 10) {
        return minutesDifference + ' minute' + (minutesDifference === 1 ? '' : 's') + ' ago';
    } else {
        // If the message was sent more than 10 minutes ago, return the original time format
        return messageTime;
    }
}

function addMsgToChats(chatConversation, message) {
    const msgDiv = document.createElement('div');
    msgDiv.classList.add('each-msg');
    msgDiv.classList.add(message.action);

    const msgContent = document.createElement('div');
    msgContent.classList.add('msg-content');
    msgContent.textContent = message.content;

    const msgTime = document.createElement('div');
    msgTime.classList.add('msg-time');
    
    msgTime.textContent = formatRelativeTime(message.time);
    setInterval(() => {
        msgTime.textContent = formatRelativeTime(message.time);
    }, 60000);

    msgDiv.appendChild(msgContent);
    msgDiv.appendChild(msgTime);

    chatConversation.appendChild(msgDiv);
}

socket.on("addMsg", (message) => {
    if (message.from !== selectedChat) return;
    const chatConversation = document.querySelector(".chat-conversation");
    addMsgToChats(chatConversation, message);
})

function createChatWindow(data, receiver) {
    const chatConversation = document.querySelector('.chat-conversation');
    chatConversation.innerHTML = '';

    // Clear the user name in the top bar
    const userChatsTopBar = document.querySelector('.user-chats-top-bar');
    userChatsTopBar.innerHTML = '';

    const newMessage = document.querySelector(".new-message");
    newMessage.innerHTML = '';

    const backArrow = document.createElement("img");
    backArrow.src = "./icons/back.svg"

    backArrow.addEventListener("click", () => {
        const activeUsersMobile = document.querySelector(".active-users");
        activeUsersMobile.style.display = "block";
        const userChats = document.querySelector(".user-chats");
        userChats.style.display = "none";
    })

    const p = document.createElement("p");
    p.textContent = data.name;

    userChatsTopBar.appendChild(backArrow);
    userChatsTopBar.appendChild(p);

    // Populate the chat conversation with the retrieved messages
    const chats = data.chats;
    chats.forEach(message => {
        const msgDiv = document.createElement('div');
        msgDiv.classList.add('each-msg');
        msgDiv.classList.add(message.action);

        const msgContent = document.createElement('div');
        msgContent.classList.add('msg-content');
        msgContent.textContent = message.content;

        const msgTime = document.createElement('div');
        msgTime.classList.add('msg-time');
        msgTime.textContent = formatRelativeTime(message.time);
        setInterval(() => {
            msgTime.textContent = formatRelativeTime(message.time);
        }, 60000);

        msgDiv.appendChild(msgContent);
        msgDiv.appendChild(msgTime);

        chatConversation.appendChild(msgDiv);
    });

    const newMessageInput = document.createElement('input');
    newMessageInput.setAttribute('type', 'text');
    newMessageInput.setAttribute('placeholder', 'Start a new message');
    newMessageInput.setAttribute('id', 'new-message-input');

    const sendMessageButton = document.createElement('button');
    sendMessageButton.setAttribute('id', 'send-message-btn');
    const sendIcon = document.createElement('img');
    sendIcon.setAttribute('src', './icons/send.svg');
    sendIcon.setAttribute('alt', '');
    sendMessageButton.appendChild(sendIcon);

    newMessageInput.addEventListener("input", () => {
        toggleButtonState(newMessageInput, sendMessageButton);
    });

    sendMessageButton.addEventListener("click", () => {
        const msg = newMessageInput.value;
        socket.emit("sendMsg",{
            content: msg,
            sender: socket.id,
            receiver: receiver,
            time: getCurrentTime()
        })
        addMsgToChats(chatConversation, {
            content: msg,
            time: getCurrentTime(),
            action: "sent"
        })
    })
    newMessage.appendChild(newMessageInput);
    newMessage.appendChild(sendMessageButton);
}

function getCurrentTime() {
    const now = new Date();
    let hours = now.getHours();
    let minutes = now.getMinutes();
    const ampm = hours >= 12 ? 'PM' : 'AM';
    hours = hours % 12;
    hours = hours ? hours : 12; // Handle midnight (0 hours)
    minutes = minutes < 10 ? '0' + minutes : minutes; // Add leading zero if minutes are single digit
    const formattedTime = hours + ':' + minutes + ' ' + ampm;
    return formattedTime;
}
///////////////////// Active user addition //////////////////////////////////

function createActiveUsersDiv(user) {
    // Create an individual user div with class "each-user"
    const userDiv = document.createElement('div');
    userDiv.classList.add('each-user');

    //hidden span element to store socketId
    const span = document.createElement("span");
    span.textContent = user.socketId;
    span.style.display = "none";
    userDiv.appendChild(span);

    // Create an image element
    const image = document.createElement('img');
    image.src = user.img;
    image.alt = '';
    image.classList.add('profile-image');

    // Create a paragraph element for the user's name
    const nameParagraph = document.createElement('p');
    nameParagraph.textContent = user.name;

    // Create a span element for the user's handle
    const handleSpan = document.createElement('span');
    handleSpan.textContent = user.handle;

    // Append the image, name paragraph, and handle span to the user div
    userDiv.appendChild(image);
    userDiv.appendChild(nameParagraph);
    userDiv.appendChild(handleSpan);

    //Add event listener to this userDiv
    userDiv.addEventListener("click", () => {
        console.log(window.innerWidth);
        if (window.innerWidth <= 430) {
            console.log("Here");
            const activeUsersMobile = document.querySelector(".active-users");
            activeUsersMobile.style.display = "none";
            const userChats = document.querySelector(".user-chats");
            userChats.style.display = "block";
        }
        selectedChat = user.socketId;
        console.log("JSKANDKJSA");
        switchUser(socket.id,user.socketId);
    })

    // Append the container div to the document body or any other desired parent element
    return userDiv;
}

socket.on("userLoggedIn", (activeUser) => {
    console.log(activeUser);
    const activeUserContainer = document.querySelector(".active-users-list");
    activeUserContainer.appendChild(createActiveUsersDiv(activeUser));
})

socket.on("activeUsers", (activeUsers) => {
    console.log("Here");
    const activeUserContainer = document.querySelector(".active-users-list");
    let cnt = 0;
    activeUsers.forEach(activeUser => {
        if (cnt == 0) {
            selectedChat = activeUser.socketId;
            switchUser(socket.id,activeUser.socketId);
        }
        activeUserContainer.appendChild(createActiveUsersDiv(activeUser));
        cnt++;
    })
})



