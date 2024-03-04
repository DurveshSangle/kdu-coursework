const loginBtn = document.querySelector("#loginBtn");
const username = document.querySelector("#username");
const password = document.querySelector("#password");

if (loginBtn) {
    loginBtn.addEventListener("click",async () => {
        await fetch("http://localhost:5000/api/login", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: username.value,
                password: password.value
            })
        })
        .then(response => {
            if (!response.ok) {
                alert("Invalid username or password !!");
                password.value = '';
                return;
            }
            return response.json();
        })
        .then(data => {
            console.log(data);
            localStorage.setItem('user', JSON.stringify(data)); 
            // setTimeout(() => window.location.href = "http://127.0.0.1:5500/index.html", 3000);
            window.location.href = "http://127.0.0.1:5500/index.html"
        });
    })
}