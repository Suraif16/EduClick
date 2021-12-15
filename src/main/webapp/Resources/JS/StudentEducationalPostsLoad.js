const mimeTypeArray = [ "image/apng" , "image/avif" , "image/jpeg" , "image/png" , "image/webp" , "image/bmp" , "image/x-icon" , "image/tiff" , "image/heic" , "image/heif" ];

let reloadStatus = true;
const postDataContent = document.getElementById( "postContents" );
let minEPostId = Infinity;


window.onscroll = function (){

    if ( ( window.innerHeight + window.pageYOffset ) > document.body.offsetHeight && reloadStatus ){

        console.log( "At the bottom!!!" );
        reloadStatus = false;
        loadStudentEducationalPosts( true );


    }

};


const loadStudentEducationalPosts = function ( scrollStatus ){

    let httpreq = new XMLHttpRequest();
    let postContent = "";
    let selectId = null;

    if ( !scrollStatus ){

        selectId = -1;

    }else {

        selectId = minEPostId;

    }

    httpreq.onreadystatechange = function (){

        if ( this.status === 200 && this.readyState === 4 ){

            displayAll( this );

        }

    }

    if ( minEPostId > 1){

        httpreq.open( "POST" , "/EduClick_war_exploded/student/studentEducationalPostLoad" , true );
        httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
        httpreq.send("id=" + selectId );

        }

    const displayAll = function ( httpreq ){

        let jsonResponse = JSON.parse( httpreq.responseText );

        if( jsonResponse.serverResponse === "null Session" || jsonResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonResponse.serverResponse === "Allowed") {

            console.log("New Post load check");


            console.log( jsonResponse.TeacherFullName );

            const ePostsLIst = jsonResponse.ePosts;

            console.log(ePostsLIst)

            for (let i = 0; i < ePostsLIst.length ; i++) {

                if ( ePostsLIst[i].EpostId < minEPostId ){

                    minEPostId = ePostsLIst[i].EpostId;

                }

                if ( ePostsLIst[i].EPtype === "MCQ" ){

                    postContent += displayMcqPost( ePostsLIst[i] );

                }else {

                    if ( ePostsLIst[i].type === "Question" ){

                        postContent += displayEducationalPost( ePostsLIst[i],jsonResponse.TeacherFullName );

                    }else {

                        postContent += displayMessage( ePostsLIst[i],jsonResponse.TeacherFullName );

                    }

                }

            }

            postDataContent.innerHTML += postContent;
            reloadStatus = true;

        }else{
            alert("something went wrong!!!");
        }

    }

}


const displayMessage = function (postData,TeacherFullName){
    if ( postData.imageStatus === "true" ) {

        let post =
            '<div class="post">' +
            '            <div class="postContentContainer">' +
            '                <div class="postProfileSection">' +
            '                    <a href="/EduClick_war_exploded/userProfileRedirect?userId=' + postData.EpostId + '" class="postProfile">' +
            '                        <div class="postProfileImage">' +
            '                            <img class="postProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg">' +
            '                        </div>' +
            '                        <div class="postProfileName" >' + TeacherFullName + '</div>' +
            '                        <div class="postTimeAndDate">' +
            postData.time + ' | ' + postData.date +
            '                        </div>' +
            '                        <div class="userOptions">' +
            '                            <input class="userOptionsButton" type="button" value="    " id="educationalPostOPtion' + postData.EpostId + '" onclick="showOptionMenu(' + postData.EpostId + ',\'educationalPostOPtion\')">' +
            '                        </div>' +
            '                    </a>' +
            '                </div>' +
            '            </div>' +
            '            <div class="postContentContainer">' +
            '                <div class="postData">' +
            '                    <div class="postMessage">' +
            '                        <!--<div>-->' +
            postData.caption +
            '                        <!--</div>-->' +
            '                    </div>\n' +
            '                    <div class="postPicture">' +
            '                        <!--                        To only present the message without the picture, comment the part below-->\n' +
            '                        <div class="postPictureImageContainer">' +
            '                            <img class="postPictureImage" src="../Resources/Images/EducationalPostImages/' + postData.EpostId + '.jpeg">' +
            '                        </div>' +
            '                    </div>' +
            '                    <div class="postContentContainer">' +
            '                        <div class="postAnswerButton">' +
            '                            <div class="answerButton" >' +
            '                                <input type="button" value="Answer" class="answerShowButton" onclick="showAnswers(' + postData.EpostId + ')">' +
            '                            </div>' +
            '                        </div>' +
            '                        <div class="postContentContainer">' +
            '                            <div style="display:none;" class="answersInPost" id="answersInPost' + postData.EpostId + '" >' +
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
            '                        <div style="display: none" class="Marks" id="ans' + postData.EpostId + '" >' +
            '                            <div class="attachment">' +
            '                                <label class="attachmentLabel">' +
            '                                    <img id="attachmentImage" src="../Resources/Icons/outline_attach_file_white_24dp.png">' +
            '                                    <input id="inputImage" type="file" accept="image/!*">' +
            '                                </label>' +
            '                                <input id="comment' + postData.EpostId + '" type="text" placeholder="   Comments...">' +
            '                                <input type="button" value="Post" class="answerShowButton" onclick="submitAnswers(' + postData.EpostId + ')">' +
            '                            </div>' +
            '                        </div>' +
            '                    </div>' +
            '                </div>' +
            '            </div>' +
            '        </div>'
        return post;

    }else if(postData.imageStatus === "false"){
        let post =
            '<div class="post">' +
            '            <div class="postContentContainer">' +
            '                <div class="postProfileSection">' +
            '                    <a href="/EduClick_war_exploded/userProfileRedirect?userId='+ postData.EpostId +'" class="postProfile">' +
            '                        <div class="postProfileImage">' +
            '                            <img class="postProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg">' +
            '                        </div>\n' +
            '                        <div class="postProfileName" >'+TeacherFullName +'</div>' +
            '                        <div class="postTimeAndDate">' +
            postData.time + ' | ' + postData.date +
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
            postData.caption +
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
            '                                <input type="button" value="Answer" class="answerShowButton" onclick="showAnswers('+postData.EpostId+')">' +
            '                            </div>' +
            '                        </div>' +
            '                        <div class="postContentContainer">' +
            '                            <div style="display:none;" class="answersInPost" id="answersInPost' + postData.EpostId + '">' +
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
            '                        <div style="display: none" class="Marks" id="ans' + postData.EpostId + '" >' +
            '                            <div class="attachment">' +
            '                                <label class="attachmentLabel">' +
            '                                    <img id="attachmentImage" src="../Resources/Icons/outline_attach_file_white_24dp.png">' +
            '                                    <input id="inputImage" type="file" accept="image/!*">' +
            '                                </label>' +
            '                                <input id="comment' + postData.EpostId + '" type="text" placeholder="   Comments...">' +
            '                                <input type="button" value="Post" class="answerShowButton">' +
            '                            </div>' +
            '                        </div>' +
            '                    </div>' +
            '                </div>' +
            '            </div>' +
            '        </div>'

        return post;
    }
}

const displayEducationalPost = function (postData,TeacherFullName){

    if ( postData.imageStatus === "true" ) {

        let post =
            '<div class="post">' +
            '            <div class="postContentContainer">' +
            '                <div class="postProfileSection">' +
            '                    <a href="/EduClick_war_exploded/userProfileRedirect?userId=' + postData.EpostId + '" class="postProfile">' +
            '                        <div class="postProfileImage">' +
            '                            <img class="postProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg">' +
            '                        </div>' +
            '                        <div class="postProfileName" >' + TeacherFullName + '</div>' +
            '                        <div class="postTimeAndDate">' +
            postData.time + ' | ' + postData.date +
            '                        </div>' +
            '                        <div class="userOptions">' +
            '                            <input class="userOptionsButton" type="button" value="    " id="educationalPostOPtion' + postData.EpostId + '" onclick="showOptionMenu(' + postData.EpostId + ',\'educationalPostOPtion\')">' +
            '                        </div>' +
            '                    </a>' +
            '                </div>' +
            '            </div>' +
            '            <div class="postContentContainer">' +
            '                <div class="postData">' +
            '                    <div class="postMessage">' +
            '                        <!--<div>-->' +
            postData.caption +
            '                        <!--</div>-->' +
            '                    </div>\n' +
            '                    <div class="postPicture">' +
            '                        <!--                        To only present the message without the picture, comment the part below-->\n' +
            '                        <div class="postPictureImageContainer">' +
            '                            <img class="postPictureImage" src="../Resources/Images/EducationalPostImages/' + postData.EpostId + '.jpeg">' +
            '                        </div>' +
            '                    </div>' +
            '                    <div class="postContentContainer">' +
            '                        <div class="postAnswerButton">' +
            '                            <div class="answerButton" >' +
            '                                <input type="button" value="Answer" class="answerShowButton" onclick="showAnswers(' + postData.EpostId + ')">' +
            '                            </div>' +
            '                        </div>' +
            '                        <div class="postContentContainer">' +
            '                            <div style="display:none;" class="answersInPost" id="answersInPost' + postData.EpostId + '" >' +
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
            '                        <div style="display: none" class="Marks" id="ans' + postData.EpostId + '" >' +
            '                            <div class="attachment">' +
            '                                <label class="attachmentLabel">' +
            '                                    <img id="attachmentImage" src="../Resources/Icons/outline_attach_file_white_24dp.png">' +
            '                                    <input id="inputImage" type="file" accept="image/!*">' +
            '                                </label>' +
            '                                <input id="comment' + postData.EpostId + '" type="text" placeholder="   Comments...">' +
            '                                <input type="button" value="Post" class="answerShowButton" onclick="submitAnswers(' + postData.EpostId + ')">' +
            '                            </div>' +
            '                        </div>' +
            '                    </div>' +
            '                </div>' +
            '            </div>' +
            '        </div>'
        return post;

    }else if(postData.imageStatus === "false"){
        let post =
            '<div class="post">' +
            '            <div class="postContentContainer">' +
            '                <div class="postProfileSection">' +
            '                    <a href="/EduClick_war_exploded/userProfileRedirect?userId='+ postData.EpostId +'" class="postProfile">' +
            '                        <div class="postProfileImage">' +
            '                            <img class="postProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg">' +
            '                        </div>\n' +
            '                        <div class="postProfileName" >'+TeacherFullName +'</div>' +
            '                        <div class="postTimeAndDate">' +
            postData.time + ' | ' + postData.date +
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
            postData.caption +
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
            '                                <input type="button" value="Answer" class="answerShowButton" onclick="showAnswers('+postData.EpostId+')">' +
            '                            </div>' +
            '                        </div>' +
            '                        <div class="postContentContainer">' +
            '                            <div style="display:none;" class="answersInPost" id="answersInPost' + postData.EpostId + '">' +
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
            '                        <div style="display: none" class="Marks" id="ans' + postData.EpostId + '" >' +
            '                            <div class="attachment">' +
            '                                <label class="attachmentLabel">' +
            '                                    <img id="attachmentImage" src="../Resources/Icons/outline_attach_file_white_24dp.png">' +
            '                                    <input id="inputImage" type="file" accept="image/!*">' +
            '                                </label>' +
            '                                <input id="comment' + postData.EpostId + '" type="text" placeholder="   Comments...">' +
            '                                <input type="button" value="Post" class="answerShowButton">' +
            '                            </div>' +
            '                        </div>' +
            '                    </div>' +
            '                </div>' +
            '            </div>' +
            '        </div>'

        return post;
    }



}
    /*let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){
            completePostLoad( this ); /!*This is where we get the response when the request was successfully sent and a successfully response is received *!/
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

            let postContents =  document.getElementById("postContents")

            /!*let count = jsonLoginResponse.EPostContent.length - 1;
            for(i=0 ; i<=count ; i++){
                console.log(jsonLoginResponse.EPostContent[i].ePost)
            }*!/
            /!*console.log(jsonLoginResponse.EPostContent.length)
            console.log(jsonLoginResponse.TeacherId)*!/

            /!*for( i = 0 ; i < jsonLoginResponse.EPostContent.length ; i++){
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
                        '                        <div style="display: none" class="Marks" id="ans4" >' +
                        '                            <div class="attachment">' +
                        '                                <label class="attachmentLabel">' +
                        '                                    <img id="attachmentImage" src="../Resources/Icons/outline_attach_file_white_24dp.png">' +
                        '                                    <input id="inputImage" type="file" accept="image/!*">' +
                        '                                </label>' +
                        '                                <input id="comment'+jsonLoginResponse.EPostContent[i].ePost.EPostID+'" type="text" placeholder="   Comments...">' +
                        '                                <input type="button" value="Post" class="answerShowButton">' +
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
                        '                        <div style="display: none" class="Marks" id="ans'+jsonLoginResponse.EPostContent[i].ePost.EPostID+'" >' +
                        '                            <div class="attachment">' +
                        '                                <label class="attachmentLabel">' +
                        '                                    <img id="attachmentImage" src="../Resources/Icons/outline_attach_file_white_24dp.png">' +
                        '                                    <input id="inputImage" type="file" accept="image/!*">' +
                        '                                </label>' +
                        '                                <input id="comment'+jsonLoginResponse.EPostContent[i].ePost.EPostID+'" type="text" placeholder="   Comments...">' +
                        '                                <input type="button" value="Post" class="answerShowButton" onclick="submitAnswers('+jsonLoginResponse.EPostContent[i].ePost.EPostID+')">' +
                        '                            </div>' +
                        '                        </div>' +
                        '                    </div>' +
                        '                </div>' +
                        '            </div>' +
                        '        </div>'
                    postContents.innerHTML += htmlString;
                }

            }*!/



        }else{
            alert("something went wrong!!!");
        }

    }*/

const submitAnswers = function(EPostId){

    let comment = "comment"+EPostId;
    let images = document.getElementById( "inputImage" ).files;
    let answers = document.getElementById(comment).value;

    console.log("Answer is : "+answers);
    console.log(images)

    if ( answers === "" && images.length === 0 ){

        console.log("empty")

    }else{
        console.log("Else nm hehehehehe")

        let isAllImageValid = false;

        for ( let i = 0; i < images.length; i++ ) {

            isAllImageValid = isImageAccepted( images[i].type )
            if ( !isAllImageValid ){
                console.log("break");
                break;

            }

        }

        if ( isAllImageValid ){

            let httpreq = new XMLHttpRequest();
            let formData = new FormData();
            httpreq.onreadystatechange = function(){

                if ( this.readyState === 4 && this.status === 200 ){

                    completeAnswersSend( this );


                }

            }

            for ( let i = 0 ; i < images.length ; i++ ){

                let x = "photo"+[i];
                formData.append( x , images[i] );
                console.log(i);

            }

            formData.append( "answers" , answers );
            formData.append("ePostId",EPostId);
            httpreq.open("POST","/EduClick_war_exploded/student/EducationalPostAnswersInsert" , true );
            httpreq.send( formData );

        }else{

            console.log( "image type invalid" );

        }

    }
    const completeAnswersSend = function ( httpreq ){
        let jsonAnswerResponse = JSON.parse( httpreq.responseText );
        if( jsonAnswerResponse.serverResponse === "null Session" || jsonAnswerResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonAnswerResponse.serverResponse === "Allowed") {
            console.log("Amo amo mamamehen awooooo");
            console.log(jsonAnswerResponse.EPostAnswer)

            let answersInPost = "answersInPost"+EPostId;
            let answer = document.getElementById(answersInPost);
            let postTextBox = "ans"+EPostId;
            let textBoxId = document.getElementById(postTextBox);
            let now = new Date().getTime();
            let extraTime = 7000;
            while(new Date().getTime() < now + extraTime ){}
            let htmlString =
                '<div class="singleAnswer">' +
                '                                    <div class="textAnswers" id="myComment">' +
                                                        jsonAnswerResponse.EPostAnswer.answer +
                '                                    </div>' +
                '                                    <div class="pictureAnswers">' +
                '                                        <a href="#">' +
                '                                            <img src="../Resources/Images/AnswerImages/' + jsonAnswerResponse.EPostAnswer.imagePath + '.jpeg">' +
                '                                        </a>' +
                '                                    </div>' +
                '                                    <div  class="Marks">' +
                                                        /*"Your marks is "+ jsonAnswerResponse.EPostAnswer.marks + "%"+*/
                                                        "Your answer will be marked soon by your teacher! "+
                '                                    </div>' +
                '                                </div>'
            answer.innerHTML+=htmlString;
            textBoxId.innerHTML="You successfully answered to this question!";
            textBoxId.style.color = "white";
            textBoxId.style.backgroundColor = "#6BDD29";

        }
        else{
            alert("something went wrong!!!");
        }
    }
}
const isImageAccepted = function ( type ){

    const arrayLength = mimeTypeArray.length;

    for ( let i = 0; i < arrayLength; i++ ) {

        if ( mimeTypeArray[i] === type ){

            return true;

        }

    }

    return false;

}

