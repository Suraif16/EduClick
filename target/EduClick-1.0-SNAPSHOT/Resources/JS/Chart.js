    console.log("send signal");

    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function () {

        if (this.readyState === 4 && this.status === 200) {
            complete(this);
        }

    }

    httpreq.open("GET", "/EduClick_war_exploded/Chart", true);
    httpreq.send();

    function complete(httpreq) {
        let jsonResponse = JSON.parse(httpreq.responseText);
        var xValues =[];//registaiondateValues
        var yValues =[];//teacherValues
        var studentsValues =[];
        var usersValues =[];

        for( i=0; i< jsonResponse.chartDetails.length; i++ ){
            xValues.push(jsonResponse.chartDetails[i].registrationDate);
            yValues.push(jsonResponse.chartDetails[i].countTeacher);
            studentsValues.push(jsonResponse.chartDetails[i].countStudent);
            usersValues.push(jsonResponse.chartDetails[i].countStudent+jsonResponse.chartDetails[i].countTeacher);

            console.log(jsonResponse.chartDetails[i].countTeacher);
            console.log(jsonResponse.chartDetails[i].registrationDate);


        }


        new Chart("myChart", {
            type: "line",
            data: {
                labels: xValues,
                datasets: [{
                    fill: false,
                    lineTension: 0,
                    backgroundColor: "rgba(0,0,255,1.0)",
                    borderColor: "rgba(0,0,255,0.1)",
                    data: yValues
                }]
            },
            options: {
                legend: {display: false},
                scales: {
                    yAxes: [{ticks: {min: 0, max:30}}],
                }
            }
        });

        new Chart("myChart1", {
            type: "line",
            data: {
                labels: xValues,
                datasets: [{
                    fill: false,
                    lineTension: 0,
                    backgroundColor: "rgba(0,0,255,1.0)",
                    borderColor: "rgba(0,0,255,0.1)",
                    data: studentsValues
                }]
            },
            options: {
                legend: {display: false},
                scales: {
                    yAxes: [{ticks: {min: 0, max:30}}],
                }
            }
        });

        new Chart("myChart2", {
            type: "line",
            data: {
                labels: xValues,
                datasets: [{
                    fill: false,
                    lineTension: 0,
                    backgroundColor: "rgba(0,0,255,1.0)",
                    borderColor: "rgba(0,0,255,0.1)",
                    data: usersValues
                }]
            },
            options: {
                legend: {display: false},
                scales: {
                    yAxes: [{ticks: {min: 0, max:30}}],
                }
            }
        });

    }












