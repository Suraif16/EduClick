let addClassroomFormStatus = true; /*if it is false the addClassroomForm is hidden*/
const addClassroomForm = document.getElementById("addNewsFeedForm");
function showAddClassroomFrom(){
    if(addClassroomFormStatus){
        addClassroomForm.style.display = "none";
        addClassroomFormStatus = false;
    }else {
        addClassroomForm.style.display = "flex";
        addClassroomFormStatus = true;
    }
}




const sendServerData = function (){
    let textMsg = document.getElementById("addNewsFeedFormTextArea").value;


    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){
        if (this.readyState === 4 && this.status === 200){
            completeLogin( this ) /*This is where we get the response when the request was successfully sent and a successfully response is received */
        }
    }

    httpreq.open( "POST" , "/EduClick_war_exploded/AdminPost" , true);
    httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
    httpreq.send("textMsg=" + textMsg );


}

const postQuestionsMessages = function (){
    sendServerData();
    showAddClassroomFrom();
}