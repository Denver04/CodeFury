(function ($) {
    "use strict";

    // Spinner
    var spinner = function () {
        setTimeout(function () {
            if ($('#spinner').length > 0) {
                $('#spinner').removeClass('show');
            }
        }, 1);
    };
    spinner();
    
    
    // Back to top button
    $(window).scroll(function () {
        if ($(this).scrollTop() > 300) {
            $('.back-to-top').fadeIn('slow');
        } else {
            $('.back-to-top').fadeOut('slow');
        }
    });
    $('.back-to-top').click(function () {
        $('html, body').animate({scrollTop: 0}, 1500, 'easeInOutExpo');
        return false;
    });


    // Sidebar Toggler
    $('.sidebar-toggler').click(function () {
        $('.sidebar, .content').toggleClass("open");
        return false;
    });


    // Progress Bar
    $('.pg-bar').waypoint(function () {
        $('.progress .progress-bar').each(function () {
            $(this).css("width", $(this).attr("aria-valuenow") + '%');
        });
    }, {offset: '80%'});


    // Calender
    $('#calender').datetimepicker({
        inline: true,
        format: 'L'
    });


    // Testimonials carousel
    $(".testimonial-carousel").owlCarousel({
        autoplay: true,
        smartSpeed: 1000,
        items: 1,
        dots: true,
        loop: true,
        nav : false
    });


    // Multiple Bar Chart
    var ctx1 = $("#multiple-bar-chart").get(0).getContext("2d");
    var myChart1 = new Chart(ctx1, {
        type: "bar",
        data: {
            labels: ["Project-1", "Project-2", "Project-3"],
            datasets: [{
                    label: "Critical",
                    data: [10, 9, 24],
                    backgroundColor: "rgba(220,53,69,0.8)"
                },
                {
                    label: "High",
                    data: [8, 23, 10],
                    backgroundColor: "rgba(255,102,0,0.8)"
                },
                {
                    label: "Medium",
                    data: [12, 25, 9],
                    backgroundColor: "rgba(255,193,7,0.8)"
                },
                {
                    label: "Low",
                    data: [22,28,20],
                    backgroundColor: "rgba(13,202,240,0.8)"
                }
            ]
            },
        options: {
            responsive: true
        }
    });

    // Doughnut Chart
    var ctx6 = $("#doughnut-chart").get(0).getContext("2d");
    var myChart6 = new Chart(ctx6, {
        type: "doughnut",
        data: {
            labels: ["Open Bugs", "Closed Bugs", "In Progress Bugs"],
            datasets: [{
                backgroundColor: [
                    "rgba(13,110,253,0.7)",
                    "rgba(23,123,76,0.7)",
                    "rgba(255,193,7,0.7)"
                ],
                data: [16, 15, 17]
            }]
        },
        options: {
            responsive: true
        }
    });

    
})(jQuery);

