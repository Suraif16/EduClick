document.onreadystatechange = function (){

    if ( document.readyState === 'complete' ){
        LoadTUserName();
        sendServerDataTeacher();

    }

}

const sendServerDataTeacher = function (){

    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){
            completeLog( this );
        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/teacherEntrancePageNewsFeedsLoad" , true);
    httpreq.send();


    function completeLog( httpreq ){

        let jsonLoginResponse = JSON.parse(httpreq.responseText);

        if( jsonLoginResponse.serverResponse === "null Session" || jsonLoginResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonLoginResponse.serverResponse === "Allowed") {

            let countOne = jsonLoginResponse.NewsFeedsDetails.length - 1;
            for (i = 0 ; i <= countOne ; i++){

               LoadNewsfeeds(jsonLoginResponse.NewsFeedsDetails[i].Date,jsonLoginResponse.NewsFeedsDetails[i].Time,jsonLoginResponse.NewsFeedsDetails[i].Caption);

            }
            let countTwo = jsonLoginResponse.TeacherName.length - 1;
            for (i = 0 ; i <= countTwo ; i++){

                LoadTeacherDetails(jsonLoginResponse.TeacherName[i].firstName,jsonLoginResponse.TeacherName[i].lastName,jsonLoginResponse.TeacherName[i].UserID);

            }


        }else{
            alert("something went wrong!!!");
        }

    }
console.log("aaaa");

}
function LoadNewsfeeds(Date, Time, Caption){
    console.log(Caption);
    console.log(Date);
    console.log(Time);

}
function LoadTeacherDetails(firstName, lastName, userID){
    console.log(firstName);
    console.log(lastName);
    console.log(userID);
}




const LoadTUserName = function (){

    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){
            completeLogin( this );
        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/teacherProfileNameLoad" , true);
    httpreq.send();

    function completeLogin( httpreq ){

        let jsonLoginResponse = JSON.parse(httpreq.responseText);

        if( jsonLoginResponse.serverResponse === "null Session" || jsonLoginResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonLoginResponse.serverResponse === "Allowed") {

            const headerName = document.getElementById("headerUserName");
            headerName.innerHTML = jsonLoginResponse.FullName;
            console.log(headerName);

        }else{
            alert("something went wrong!!!");
        }

    }


}

