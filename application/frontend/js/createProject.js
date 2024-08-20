document.addEventListener("DOMContentLoaded", function () {
    // Load team members from JSON file
    fetch("./data/teammembers.json")
      .then((response) => response.json())
      .then((data) => {
        const dropdownMenu = document.getElementById("teamMembersDropdown");
  
        // Add developers to the dropdown menu
        data.developers.forEach((dev) => {
          const listItem = document.createElement("li");
          listItem.innerHTML = `<a class="dropdown-item" href="#" data-value="${dev.id}">${dev.name}</a>`;
          dropdownMenu.appendChild(listItem);
        });
  
        // Add testers to the dropdown menu
        data.testers.forEach((tester) => {
          const listItem = document.createElement("li");
          listItem.innerHTML = `<a class="dropdown-item" href="#" data-value="${tester.id}">${tester.name}</a>`;
          dropdownMenu.appendChild(listItem);
        });
      })
      .catch((error) => console.error("Error loading team members:", error));
  
    // Set minimum start date to 2 days after the current date
    const startDateInput = document.getElementById("startDate");
    const currentDate = new Date();
    const minDate = new Date(currentDate.setDate(currentDate.getDate() + 2))
      .toISOString()
      .split("T")[0];
    startDateInput.setAttribute("min", minDate);
  
    const selectedMembersList = document.getElementById("selectedMembersList");
    const selectedMembers = [];
  
    // Handle dropdown item click
    document.addEventListener("click", function (event) {
      if (event.target.classList.contains("dropdown-item")) {
        event.preventDefault();
        const memberName = event.target.textContent;
        const memberId = event.target.getAttribute("data-value");
  
        if (!selectedMembers.some((member) => member.id === memberId)) {
          selectedMembers.push({ id: memberId, name: memberName });
  
          // Add member to the UI list
          const listItem = document.createElement("li");
          listItem.className =
            "list-group-item d-flex justify-content-between align-items-center";
          listItem.textContent = memberName;
  
          // Remove button for each selected member
          const removeButton = document.createElement("button");
          removeButton.className = "btn btn-close btn-sm btn-danger";
          removeButton.addEventListener("click", function () {
            // Remove from the UI list
            selectedMembersList.removeChild(listItem);
  
            // Remove from the selected members array
            const index = selectedMembers.findIndex(
              (member) => member.id === memberId
            );
            selectedMembers.splice(index, 1);
          });
  
          listItem.appendChild(removeButton);
          selectedMembersList.appendChild(listItem);
        }
      }
    });
  
    // Handle form submission and store data in JSON
    const projectForm = document.getElementById("projectForm");
    projectForm.addEventListener("submit", function (event) {
      event.preventDefault();
  
      if (selectedMembers.length > 0) {
        // Create JSON object
        const formData = new FormData(projectForm);
        const projectData = {
          projectName: formData.get("projectName"),
          startDate: formData.get("startDate"),
          teamMembers: selectedMembers,
          status: "in-progress",
        };
  
        // Convert JSON object to string and log it
        const jsonString = JSON.stringify(projectData, null, 2);
        console.log("Project Data:", jsonString);
  
        // Optionally, you can send the JSON data to a server or save it in local storage
  
        // Example: Save to local storage
        // localStorage.setItem('projectData', jsonString);
  
        // Example: Send to a server (uncomment and update URL as needed)
        // fetch('/saveProject', {
        //     method: 'POST',
        //     headers: {
        //         'Content-Type': 'application/json'
        //     },
        //     body: jsonString
        // });
  
         
  
        alert("Information saved");
        projectForm.reset(); // Optionally reset the form after submission
        selectedMembersList.innerHTML = ""; // Clear the selected members list
        selectedMembers.length = 0; // Clear the array of selected members
  
        // Close the modal
        const modal = bootstrap.Modal.getInstance(
          document.getElementById("staticBackdrop")
        );
        modal.hide();
      } else {
        alert("Please select at least one team member.");
      }
    });
  });
  