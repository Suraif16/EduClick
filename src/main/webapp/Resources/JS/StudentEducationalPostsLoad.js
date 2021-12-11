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
        // console.log(jsonLoginResponse.TeacherFullName)



        if( jsonLoginResponse.serverResponse === "null Session" || jsonLoginResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonLoginResponse.serverResponse === "Allowed") {
            console.log("math me paththen awa bosa")

            /*let count = jsonLoginResponse.EPostContent.length - 1;
            for(i=0 ; i<=count ; i++){
                console.log(jsonLoginResponse.EPostContent[i].ePost)
            }*/
            console.log(jsonLoginResponse.EPostContent.length)
            console.log(jsonLoginResponse.TeacherId)

            for( i = 0 ; i < jsonLoginResponse.EPostContent.length ; i++){
                if(jsonLoginResponse.EPostContent[i].ePost.Type == "Messages"){
                    console.log("Image Path of post "+i+" : "+jsonLoginResponse.EPostContent[i].eWork.ImagePath)
                    let htmlString =
                        '<div class="post">' +
                        '            <div class="postContentContainer">' +
                        '                <div class="postProfileSection">' +
                        '                    <a href="/EduClick_war_exploded/userProfileRedirect?userId='+ jsonLoginResponse.TeacherId +'" class="postProfile">' +
                        '                        <div class="postProfileImage">' +
                        '                            <img class="postProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg">' +
                        '                        </div>\n' +
                        '                        <div class="postProfileName" >'+jsonLoginResponse.TeacherFullName+'</div>' +
                        '                        <div class="postTimeAndDate">' +
                                                    jsonLoginResponse.EPostContent[i].ePost.Time+ ' | '+ jsonLoginResponse.EPostContent[i].ePost.Date +
                        '                        </div>\n' +
                        '                        <div class="userOptions">' +
                        '                            <input class="userOptionsButton" type="button" value="    " id="postOption4" onclick="showOptionMenu(4,\'postOption\')">' +
                        '                        </div>' +
                        '                    </a>' +
                        '                </div>' +
                        '            </div>' +
                        '            <div class="postContentContainer">' +
                        '                <div class="postData">' +
                        '                    <div class="postMessage">' +
                        '                        <!--<div>-->' +
                                                    jsonLoginResponse.EPostContent[i].eWork.Caption +
                        '                        <!--</div>-->' +
                        '                    </div>' +
                        '                    <div class="postPicture">' +
                        '                        <!--To only present the message without the picture, comment the part below-->' +
                        '                        <!--<div class="postPictureImageContainer">' +
                        '                            <img class="postPictureImage" src="Icons/seminar-text.jpg">' +
                        '                        </div>-->' +
                        '                    </div>' +
                        '                    <div class="postContentContainer">' +
                        '                        <div class="postAnswerButton">' +
                        '                            <div class="answerButton" >' +
                        '                                <input type="button" value="Answer" class="answerShowButton" onclick="showAnswers(4)">' +
                        '                            </div>' +
                        '                        </div>' +
                        '                        <div class="postContentContainer">' +
                        '                            <div style="display:none;" class="answersInPost" id="answersInPost4" >' +
                        '                                <div class="singleAnswer">' +
                        '                                    <div class="textAnswers" id="myComment">' +
                        '                                        This is my Answer !!!!!!!!!' +
                        '                                    </div>' +
                        '                                        <a href="#">' +
                        '                                            <img src="../Resources/Images/answers2.jpg">' +
                        '                                        </a>' +
                        '                                    </div>' +
                        '                                    <div class="Marks">' +
                        '                                        Your marks is 50%' +
                        '                                    </div>' +
                        '                                </div>' +
                        '                            </div>' +
                        '                        </div>' +
                        '                        <div style="display: none" class="Marks" id="ans4" onclick="showAnswers(4)">' +
                        '                            <div class="attachment">' +
                        '                                <label class="attachmentLabel">' +
                        '                                    <img id="attachmentImage" src="../Resources/Icons/outline_attach_file_white_24dp.png">' +
                        '                                    <input type="file" accept="image/*">' +
                        '                                </label>' +
                        '                                <input id="comment" type="text" placeholder="   Comments...">' +
                        '                                <input type="button" value="Post" class="answerShowButton">' +
                        '                                <input type="button" value="Delete Answer" class="answerShowButton">' +
                        '                            </div>' +
                        '                        </div>' +
                        '                    </div>' +
                        '                </div>' +
                        '            </div>' +
                        '        </div>'

                    postContents.innerHTML += htmlString;
                }
                else if(jsonLoginResponse.EPostContent[i].ePost.Type == "Question"){
                    htmlString =
                        '<div class="post">' +
                        '            <div class="postContentContainer">' +
                        '                <div class="postProfileSection">' +
                        '                    <a href="/EduClick_war_exploded/userProfileRedirect?userId='+ jsonLoginResponse.TeacherId +'" class="postProfile">' +
                        '                        <div class="postProfileImage">' +
                        '                            <img class="postProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg">' +
                        '                        </div>' +
                        '                        <div class="postProfileName" >'+jsonLoginResponse.TeacherFullName+'</div>' +
                        '                        <div class="postTimeAndDate">' +
                                                    jsonLoginResponse.EPostContent[i].ePost.Time+ ' | '+ jsonLoginResponse.EPostContent[i].ePost.Date  +
                        '                        </div>' +
                        '                        <div class="userOptions">' +
                        '                            <input class="userOptionsButton" type="button" value="    " id="postOption5" onclick="showOptionMenu(5,\'postOption\')">' +
                        '                        </div>' +
                        '                    </a>' +
                        '                </div>' +
                        '            </div>' +
                        '            <div class="postContentContainer">' +
                        '                <div class="postData">' +
                        '                    <div class="postMessage">' +
                        '                        <!--<div>-->' +
                                                    jsonLoginResponse.EPostContent[i].eWork.Caption +
                        '                        <!--</div>-->' +
                        '                    </div>\n' +
                        '                    <div class="postPicture">' +
                        '                        <!--                        To only present the message without the picture, comment the part below-->\n' +
                        '                        <div class="postPictureImageContainer">' +
                        '                            <img class="postPictureImage" src="../Resources/Images/EducationalPostImages/' + jsonLoginResponse.EPostContent[i].eWork.ImagePath + '.jpeg">' +
                        '                        </div>' +
                        '                    </div>' +
                        '                    <div class="postContentContainer">' +
                        '                        <div class="postAnswerButton">' +
                        '                            <div class="answerButton" >' +
                        '                                <input type="button" value="Answer" class="answerShowButton" onclick="showAnswers('+jsonLoginResponse.EPostContent[i].ePost.EPostID+')">' +
                        '                            </div>' +
                        '                        </div>' +
                        '                        <div class="postContentContainer">' +
                        '                            <div style="display:none;" class="answersInPost" id="answersInPost'+jsonLoginResponse.EPostContent[i].ePost.EPostID+'" >' +
                        '                                <div class="singleAnswer">' +
                        '                                    <div class="textAnswers" id="myComment">' +
                        '                                        This is my Answer !!!!!!!!!' +
                        '                                    </div>' +
                        '                                    <div class="pictureAnswers">' +
                        '                                        <a href="#">' +
                        '                                            <img src="../Resources/Images/answers2.jpg">' +
                        '                                        </a>' +
                        '                                    </div>' +
                        '                                    <div class="Marks">' +
                        '                                        Your marks is 50%' +
                        '                                    </div>' +
                        '                                </div>' +
                        '                            </div>' +
                        '                        </div>' +
                        '                        <div style="display: none" class="Marks" id="ans'+jsonLoginResponse.EPostContent[i].ePost.EPostID+'" onclick="showAnswers('+jsonLoginResponse.EPostContent[i].ePost.EPostID+')">' +
                        '                            <div class="attachment">' +
                        '                                <label class="attachmentLabel">' +
                        '                                    <img id="attachmentImage" src="../Resources/Icons/outline_attach_file_white_24dp.png">' +
                        '                                    <input type="file" accept="image/*">' +
                        '                                </label>' +
                        '                                <input id="comment" type="text" placeholder="   Comments...">' +
                        '                                <input type="button" value="Post" class="answerShowButton">' +
                        '                                <input type="button" value="Delete Answer" class="answerShowButton">' +
                        '                            </div>' +
                        '                        </div>' +
                        '                    </div>' +
                        '                </div>' +
                        '            </div>' +
                        '        </div>'
                    postContents.innerHTML += htmlString;
                }
            }






        }else{
            alert("something went wrong!!!");
        }

    }
}