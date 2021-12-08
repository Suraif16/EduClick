const mimeTypeArray = [ "image/apng" , "image/avif" , "image/jpeg" , "image/png" , "image/webp" , "image/bmp" , "image/x-icon" , "image/tiff" , "image/heic" , "image/heif" ];

const showAddEducationalPostForm = function (){

    const addEducationPostForm = document.getElementById( "addNewsFeedForm" );

    if ( addEducationPostForm.style.display === "flex" ){

        addEducationPostForm.style.display = "none";

    }else{

        addEducationPostForm.style.display = "flex";

    }

}

const postQuestionsMessages = function (){

    let message = document.getElementById( "addNewsFeedFormTextArea" ).value;
    let images = document.getElementById( "inputImage" ).files;
    let type = document.getElementById( "ePostType" ).value;

    console.log( message , images , type );

    if ( message === "" && images.length === 0 ){

        console.log("empty")

    }else{

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

                    complete( this );


                }

            }

            for ( let i = 0 ; i < images.length ; i++ ){

                let x = "photo"+[i];
                formData.append( x , images[i] );
                console.log(i);

            }

            formData.append( "message" , message );
            formData.append( "type" , type );

            httpreq.open("POST","/EduClick_war_exploded/teacher/EducationalPostInsert" , true );
            httpreq.send( formData );

        }else{

            console.log( "image type invalid" );

        }

    }

    const complete = function ( httpreq ){

        let jsonResponse = JSON.parse( httpreq.responseText );
        showAddEducationalPostForm();
        if( jsonResponse.serverResponse === "null Session" || jsonResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonResponse.serverResponse === "Allowed") {
            console.log( jsonResponse.EPost );
            const postContentsElement = document.getElementById( "postContents" );
            let now = new Date().getTime();
            let extraTime = 7000;
            while(new Date().getTime() < now + extraTime ){}
            let innerPreviouseHTML = postContentsElement.innerHTML;
            postContentsElement.innerHTML = '<div class="post">' +
                '            <div class="postContentContainer">' +
                '                <div class="postProfileSection">' +
                '                    <a href="TeacherProfile.html" class="postProfile">' +
                '                        <div class="postProfileImage">' +
                '                            <img class="postProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg">' +
                '                        </div>' +
                '                        <div class="postProfileName" >User Name</div>' +
                '                    </a>' +
                '                    <div class="postTimeAndDate">' +
                + jsonResponse.EPost.time +
                ' | ' + jsonResponse.EPost.date +
                '                    </div>' +
                '                    <div class="userOptions">' +
                '                        <input class="userOptionsButton" type="button" value="    " id="educationalPostOPtion1" onclick="showOptionMenu(1,\'educationalPostOPtion\')">' +
                '                    </div>' +
                '                </div>' +
                '            </div>' +
                '            <div class="postContentContainer">' +
                '                <div class="postData">' +
                '                    <div class="postMessage">' +
                                        jsonResponse.EPost.caption   +
                '                    </div>' +
                '                    <div class="postPicture">' +
                '                        <div class="postPictureImageContainer">' +
                '                            <!--To only present the message without the picture, keep this value as null / empty-->' +
                '                            <img class="postPictureImage" src="../Resources/Images/EducationalPostImages/' + jsonResponse.EPost.imagePath + '.jpeg">' +
                '                        </div>' +
                '                    </div>' +
                '                </div>' +
                '            </div>' +
                '            <div class="postContentContainer">' +
                '                <div class="postAnswerButton">' +
                '                    <div class="answerButton" >' +
                '                        <input type="button" value="Answer" class="answerShowButton" onclick="showAnswers(1)">' +
                '                    </div>' +
                '                </div>' +
                '            </div>' +
                '            <div class="postContentContainer">' +
                '                <div style="display:none;" class="answersInPost" id="answersInPost1" >' +
                '                    <div class="singleAnswer">' +
                '                        <div class="answerUser">' +
                '                            <a href="TeacherProfile.html" class="answerProfile">' +
                '                                <div class="answerProfileImage">' +
                '                                    <img class="answerProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg">' +
                '                                </div>' +
                '                                <div class="answerProfileName" >Student Name</div>' +
                '                            </a>' +
                '                            <div class="postTimeAndDate">' +
                '                                18:32:26 | 03/25/2015' +
                '                            </div>' +
                '                            <div class="userOptions">' +
                '                                <input class="userOptionsButton" type="button" value="    " id="answerOption1" onclick="showOptionMenu(1,\'answerOption\')">' +
                '                            </div>' +
                '                        </div>' +
                '                        <div class="textAnswers">' +
                '                            This could be proved by a contradiction.' +
                '                            This could be proved by a contradiction.' +
                '                            This could be proved by a contradiction.' +
                '                            This could be proved by a contradiction.' +
                '                            This could be proved by a contradiction.' +
                '                        </div>' +
                '                        <div class="pictureAnswers">' +
                '                            <a href="#">' +
                '                                <img src="../Resources/Images/images.jfif">' +
                '                            </a>' +
                '                        </div>' +
                '                        <div class="marksForAnswers">' +
                '                            <input type="range" value="0" max="100" oninput="answer1.value = this.value" class="marksForAnswersRange">' +
                '                            <output id="answer1" class="marksForAnswersRangeValue">0</output>' +
                '                        </div>' +
                '                    </div>' +
                '                    <div class="singleAnswer">' +
                '                        <div class="answerUser">' +
                '                            <a href="TeacherProfile.html" class="answerProfile">' +
                '                                <div class="answerProfileImage">' +
                '                                    <img class="answerProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg">' +
                '                                </div>' +
                '                                <div class="answerProfileName" >Student Name</div>' +
                '                            </a>' +
                '                            <div class="postTimeAndDate">' +
                '                                18:32:26 | 03/25/2015' +
                '                            </div>' +
                '                            <div class="userOptions">' +
                '                                <input class="userOptionsButton" type="button" value="    " id="answerOption2" onclick="showOptionMenu(2,\'answerOption\')">' +
                '                            </div>' +
                '                        </div>' +
                '                        <div class="textAnswers">' +
                '                            This could be proved by a contradiction.' +
                '                            This could be proved by a contradiction.' +
                '                            This could be proved by a contradiction.' +
                '                            This could be proved by a contradiction.' +
                '                            This could be proved by a contradiction.' +
                '                        </div>' +
                '                        <div class="pictureAnswers">' +
                '                            <a href="#">' +
                '                                <img src="../Resources/Images/answers2.jpg">' +
                '                            </a>' +
                '                        </div>' +
                '                        <div class="marksForAnswers">' +
                '                            <input type="range" value="0" max="100" oninput="answer2.value = this.value" class="marksForAnswersRange">' +
                '                            <output id="answer2" class="marksForAnswersRangeValue">0</output>' +
                '                        </div>' +
                '                    </div>' +
                '                </div>' +
                '            </div>' +
                '        </div>';

            postContentsElement.innerHTML += innerPreviouseHTML;

        }else{
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