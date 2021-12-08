const loadStudentEducationalPosts = function (){
    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){
            completePostLoad( this ); /*This is where we get the response when the request was successfully sent and a successfully response is received */
        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/student/studentEducationalPostLoad" , true);
    httpreq.send();

    function completePostLoad( httpreq ){

        let jsonLoginResponse = JSON.parse(httpreq.responseText);



        if( jsonLoginResponse.serverResponse === "null Session" || jsonLoginResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonLoginResponse.serverResponse === "Allowed") {
            console.log("math me paththen awa bosa")

            /*let count = jsonLoginResponse.EPostContent.length - 1;
            for(i=0 ; i<=count ; i++){
                console.log(jsonLoginResponse.EPostContent[i].ePost)
            }*/
            console.log(jsonLoginResponse.EPostContent.length)

            /*for( i = 0 ; i < jsonLoginResponse.EPostContent.length ; i++){
                let htmlString =
                    '        <div class="post">    ' +
                    '<div class="postContentContainer">\n' +
                    '                <div class="postData">\n' +
                    '                       <div class="postMessage">' + jsonLoginResponse.EPostContent[i].eWork.Caption +

                    '                       </div>' +
                    '                    <div class="postPicture">\n' +
                    '                    </div>\n' +
                    '                    <div class="postContentContainer">\n' +
                    '                        <div class="postAnswerButton">\n' +
                    '                            <div class="answerButton" >\n' +
                    '                                <input type="button" value="Answer" class="answerShowButton" onclick="showAnswers(4)">\n' +
                    '                            </div>\n' +
                    '                        </div>\n' +
                    '                        <div class="postContentContainer">\n' +
                    '                            <div style="display:none;" class="answersInPost" id="answersInPost4" >\n' +
                    '                                <div class="singleAnswer">\n' +
                    '                                    <div class="textAnswers" id="myComment">\n' +
                    '                                        This is my Answer !!!!!!!!!\n' +
                    '                                    </div>\n' +
                    '                                    <div class="pictureAnswers">\n' +
                    '                                        <a href="#">\n' +
                    '                                            <img src="../Resources/Images/answers2.jpg">\n' +
                    '                                        </a>\n' +
                    '                                    </div>\n' +
                    '                                    <div class="Marks">\n' +
                    '                                        Your marks is 50%\n' +
                    '                                    </div>\n' +
                    '                                </div>\n' +
                    '                            </div>\n' +
                    '                        </div>\n' +
                    '                        <div style="display: none" class="Marks" id="ans4" onclick="showAnswers(4)">\n' +
                    '                            <div class="attachment">\n' +
                    '                                <label class="attachmentLabel">\n' +
                    '                                    <img id="attachmentImage" src="../Resources/Icons/outline_attach_file_white_24dp.png">\n' +
                    '                                    <input type="file" accept="image/!*">\n' +
                    '                                </label>\n' +
                    '                                <input id="comment" type="text" placeholder="   Comments...">\n' +
                    '                                <input type="button" value="Post" class="answerShowButton">\n' +
                    '                                <input type="button" value="Delete Answer" class="answerShowButton">\n' +
                    '                            </div>\n' +
                    '                        </div>\n' +
                    '                    </div>\n' +
                    '                </div>\n' + +
                    '            </div>'+
                '                   </div>'

                postContents.innerHTML += htmlString;
            }*/






        }else{
            alert("something went wrong!!!");
        }

    }
}