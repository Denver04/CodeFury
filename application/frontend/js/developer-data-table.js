fetch("./data/bug_reports.json")
  .then((response) => {
    if (!response.ok) {
      throw new Error("Network response was not ok");
    }
    return response.json();
  })
  .then((data) => {
    // JSON data in the `data` variable
    new DataTable("#developer-data-table", {
      data: data,
      columns: [
        { data: "bugId" },
        { data: "title" },
        { data: "description" },
        {
          data: "severity",
          render: function (data, type) {
            if (type === "display") {
              if (data === "Critical")
                return `<div class="d-flex justify-content-center"><span class="w-100 badge text-bg-danger rounded-pill p-2">${data}</span></div>`;
              else if (data === "High")
                return `<div class="d-flex justify-content-center"><span class="w-100 badge rounded-pill p-2" style="background-color:#FF6600">${data}</span></div>`;
              else if (data === "Medium")
                return `<div class="d-flex justify-content-center"><span class="w-100 badge text-bg-warning rounded-pill p-2 text-white">${data}</span></div>`;
              else if (data === "Low")
                return `<div class="d-flex justify-content-center"><span class="w-100 badge text-bg-info rounded-pill p-2 text-white">${data}</span></div>`;
            }

            return data;
          },
        },
        {
          data: "status",
          render: function (data, type) {
            if (type === "display") {
              if (data === "In Progress")
                return `<div class="d-flex justify-content-center">
                    <select name="status" id="status" class="w-150 text-sm p-1 rounded-pill text-center">
                        <option value=${data} class="text-bg-warning text-white">In-Progress</option>
                        <option value="open" class="text-bg-primary">Open</option>
                        <option value="closed" class="text-bg-success">Closed</option>
                    </select>
                    </div>`;
              else if (data === "Open")
                return `<div class="d-flex justify-content-center">                              
                    <select name="status" id="status" class="w-150 text-sm p-1 rounded-pill text-center">
                        <option value=${data} class="text-bg-primary">Open</option>
                        <option value="in-progress" class="text-bg-warning text-white">In-Progress</option>
                        <option value="closed" class="text-bg-success">Closed</option>
                    </select>
                    </div>`;
              else if (data === "Closed")
                return `<div class="d-flex justify-content-center">                              
                    <select name="status" id="status" class="w-150 text-sm p-1 rounded-pill text-center">
                        <option value=${data} class="text-bg-success">Closed</option>
                        <option value="open" class="text-bg-primary">Open</option>
                        <option value="in-progress" class="text-bg-warning text-white">In-Progress</option>
                    </select>
                    </div>`;
            }
            return data;
          },
        },
        {
          data: "createdOn",
          render: function (data, type) {
            if (type === "display") {
              const optionsDate = {
                day: "2-digit",
                month: "2-digit",
                year: "numeric",
              };
              const optionsTime = {
                hour: "2-digit",
                minute: "2-digit",
                second: "2-digit",
                hour12: true,
                timeZone: "Asia/Kolkata",
              };

              // Create a Date object from the input string
              const date = new Date(data);

              // Format the date part
              const dateFormatter = new Intl.DateTimeFormat(
                "en-GB",
                optionsDate
              );
              const formattedDate = dateFormatter.format(date);

              // Format the time part in India time zone
              const timeFormatter = new Intl.DateTimeFormat(
                "en-GB",
                optionsTime
              );
              const formattedTime = timeFormatter.format(date);

              // Return formatted date and time
              const _date = formattedDate; // format: dd-mm-yyyy
              const _time = formattedTime; // format: hh:mm:ss AM/PM
              return `<div>${_date}</div> <div>${_time}</div>`;
            }
            return data;
          },
        },
        { data: "project" },
      ],
    });
  })
  .catch((error) => {
    console.error("There was a problem with the fetch operation:", error);
  });
