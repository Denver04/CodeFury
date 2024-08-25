document.addEventListener('DOMContentLoaded', function () {
    // Get form and inputs
    const projectForm = document.getElementById('projectForm');
    const inputProjectName = document.getElementById('inputProjectName');
    const inputBugTitle = document.getElementById('inputBugTitle');
    const inputBugDescription = document.getElementById('inputBugDescription');
    const confirmCheck = document.getElementById('gridCheck1');
  
    // Handle form submission
    projectForm.addEventListener('submit', function (event) {
      event.preventDefault(); // Prevent form from submitting the traditional way
  
      // Check all fields are filled and checkbox is checked
      const isValid =
        inputProjectName.value.trim() !== '' &&
        inputBugTitle.value.trim() !== '' &&
        inputBugDescription.value.trim() !== '' &&
        confirmCheck.checked;
  
      if (isValid) {
        // Simulate saving the bug report
        const bugReport = {
          projectName: inputProjectName.value,
          bugTitle: inputBugTitle.value,
          bugDescription: inputBugDescription.value,
          confirmed: confirmCheck.checked,
        };
  
        console.log('Bug Report Submitted:', bugReport);
  
        // Show success alert
        alert('Bug report submitted successfully!');
  
        // Reset the form after submission
        projectForm.reset();
  
        // Close the modal
        const modalElement = document.getElementById('staticBackdrop');
        const modal = bootstrap.Modal.getInstance(modalElement);
        modal.hide();
      } else {
        // Show error alert if validation fails
        alert('Please fill all fields and confirm the information.');
      }
    });
  });

  