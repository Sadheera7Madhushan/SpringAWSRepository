// hide text after 5 seconds
function hideNotification() {
    setTimeout(function () {
        let selector = document.getElementById("notification");
        if (selector) {
            selector.style.display = 'none';
        }
    }, 5000); // 10000 milliseconds = 10 seconds
}

// Call the function when the page loads
window.onload = hideNotification;

// set current user in top bar
/*document.addEventListener("DOMContentLoaded", function () {
    const user = `[[${user}]]`;
    console.log("User: " + user);
    localStorage.setItem("user", user);
    let username = localStorage.getItem("user");
    const userParagraph = document.getElementById("username");
    userParagraph.textContent = username;
});*/
