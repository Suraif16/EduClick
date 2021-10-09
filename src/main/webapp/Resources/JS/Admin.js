//document.getElementById("demo").innerHTML = 5;

document.onreadystatechange = function (){
    if ( document.readyState === 'complete' ){
        /* when the document is loaded and complete this function will run*/
        console.log("page loaded");
        sendServerData();
        console.log("page complete");
    }
}
const sendServerData = function (){

    let httpReq = new XMLHttpRequest();
    httpReq.onreadystatechange = function () {

        if (this.readyState === 4 && this.status === 200) {
            completecount(this) /*This is where we get the response when the request was successfully sent and a successfully response is received */
        }

    }
    httpReq.open( "GET" , "/EduClick_war_exploded/Admin" , true);
    httpReq.send();


    function completecount( httpreq ) {
        let jsonLoginResponse = JSON.parse(httpreq.responseText);

        //document.getElementById("demo").innerHTML = jsonLoginResponse.Teacher;
        document.getElementById("count1").innerHTML = jsonLoginResponse.Teacher;
        console.log(jsonLoginResponse.Teacher);
        document.getElementById("count2").innerHTML = jsonLoginResponse.TeacherReg;
        console.log(jsonLoginResponse.TeacherReg);
        document.getElementById("count3").innerHTML = jsonLoginResponse.Student;
        console.log(jsonLoginResponse.Student);
        document.getElementById("count4").innerHTML = jsonLoginResponse.StudentReg;
        console.log(jsonLoginResponse.StudentReg);
    }

}





