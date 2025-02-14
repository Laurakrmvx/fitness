const API_URL = "http://localhost:8080";

// Function to fetch and display Users
async function fetchUsers() {
    const response = await fetch(`${API_URL}/users`);
    const users = await response.json();
    const usersTableBody = document.getElementById("usersTableBody");
    usersTableBody.innerHTML = users.map(user =>
        `<tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.age}</td>
            <td>${user.weight}</td>
            <td>${user.height}</td>
            <td class="action-buttons">
                <button onclick="editUser(${user.id}, '${user.name}', ${user.age}, ${user.weight}, ${user.height})">Edit</button>
                <button onclick="deleteUser(${user.id})">Delete</button>
            </td>
        </tr>`
    ).join('');
}

// Function to add a User
async function addUser() {
    const name = document.getElementById("name").value;
    const age = document.getElementById("age").value;
    const weight = document.getElementById("weight").value;
    const height = document.getElementById("height").value;

    await fetch(`${API_URL}/users`, {
        method: "POST",
        headers: { "Content-Type": "text/plain" },
        body: `${name},${age},${weight},${height}`
    });

    alert("User added!");
    fetchUsers();
}

// Function to update a User
async function updateUser() {
    const id = document.getElementById("userId").value;
    const name = document.getElementById("name").value;
    const age = document.getElementById("age").value;
    const weight = document.getElementById("weight").value;
    const height = document.getElementById("height").value;

    await fetch(`${API_URL}/users`, {
        method: "PUT",
        headers: { "Content-Type": "text/plain" },
        body: `${id},${name},${age},${weight},${height}`
    });

    alert("User updated!");
    fetchUsers();
}

// Function to delete a User
async function deleteUser(id) {
    await fetch(`${API_URL}/users?id=${id}`, { method: "DELETE" });
    alert("User deleted!");
    fetchUsers();
}

// Function to edit a User
function editUser(id, name, age, weight, height) {
    document.getElementById("userId").value = id;
    document.getElementById("name").value = name;
    document.getElementById("age").value = age;
    document.getElementById("weight").value = weight;
    document.getElementById("height").value = height;
}

// Function to fetch and display Workouts
async function fetchWorkouts() {
    const response = await fetch(`${API_URL}/workouts`);
    const workouts = await response.json();
    const workoutsTableBody = document.getElementById("workoutsTableBody");
    workoutsTableBody.innerHTML = workouts.map(workout =>
        `<tr>
            <td>${workout.id}</td>
            <td>${workout.user_id}</td>
            <td>${workout.type}</td>
            <td>${workout.duration}</td>
            <td>${workout.calories_burned}</td>
            <td>${workout.date}</td>
            <td class="action-buttons">
                <button onclick="editWorkout(${workout.id}, ${workout.user_id}, '${workout.type}', ${workout.duration}, ${workout.calories_burned}, '${workout.date}')">Edit</button>
                <button onclick="deleteWorkout(${workout.id})">Delete</button>
            </td>
        </tr>`
    ).join('');
}

// Function to add a Workout
async function addWorkout() {
    const userId = document.getElementById("workoutUserId").value;
    const type = document.getElementById("type").value;
    const duration = document.getElementById("duration").value;
    const calories = document.getElementById("calories").value;
    const date = document.getElementById("date").value;

    await fetch(`${API_URL}/workouts`, {
        method: "POST",
        headers: { "Content-Type": "text/plain" },
        body: `${userId},${type},${duration},${calories},${date}`
    });

    alert("Workout added!");
    fetchWorkouts();
}

// Function to update a Workout
async function updateWorkout() {
    const id = document.getElementById("workoutId").value;
    const userId = document.getElementById("workoutUserId").value;
    const type = document.getElementById("type").value;
    const duration = document.getElementById("duration").value;
    const calories = document.getElementById("calories").value;
    const date = document.getElementById("date").value;

    await fetch(`${API_URL}/workouts`, {
        method: "PUT",
        headers: { "Content-Type": "text/plain" },
        body: `${id},${userId},${type},${duration},${calories},${date}`
    });

    alert("Workout updated!");
    fetchWorkouts();
}

// Function to delete a Workout
async function deleteWorkout(id) {
    await fetch(`${API_URL}/workouts?id=${id}`, { method: "DELETE" });
    alert("Workout deleted!");
    fetchWorkouts();
}

// Initialize data on load
window.onload = function() {
    fetchUsers();
    fetchWorkouts();
}
