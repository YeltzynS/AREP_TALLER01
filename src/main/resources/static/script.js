document.getElementById("getWorkout").addEventListener("click", () => {
    let type = document.getElementById("type").value;
    let level = document.getElementById("level").value;
 
    fetch(`/api/workout?type=${type}&level=${level}`)
        .then(response => response.json())
        .then(data => {
            let list = document.getElementById("workoutList");
            list.innerHTML = ""; // Limpiar lista anterior
            
            if (data.workout.length === 0) {
                list.innerHTML = "<li>No se encontraron ejercicios</li>";
                return;
            }

            data.workout.forEach(exercise => {
                let li = document.createElement("li");
                li.textContent = exercise;
                list.appendChild(li);
            });
        })
        .catch(error => console.error("Error:", error));
});
