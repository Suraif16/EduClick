let reloadStatus = true;
const postData = document.getElementById( "postContents" );

window.onscroll = function (){

    if ( ( window.innerHeight + window.pageYOffset ) > document.body.offsetHeight && reloadStatus ){

        console.log( "At the bottom!!!" );
        reloadStatus = false;
        selectEPostFromServer();


    }

};

const displayEducationalPost = function ( postData ){

    let post = '<div class="post">' +
        '            <div class="postContentContainer">' +
        '                <div class="postProfileSection">' +
        '                    <a href="TeacherProfile.html" class="postProfile">' +

        '                        <div class="postProfileImage">' +
        '                            <img class="postProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg">' +
        '                        </div>' +
        '                        <div class="postProfileName" >User Name</div>' +
        '                    </a>' +
        '                    <div class="postTimeAndDate">' +
        postData.time +' | '+ postData.date +
        '                    </div>' +
        '                    <div class="userOptions">' +
        '                        <input class="userOptionsButton" type="button" value="    " id="educationalPostOPtion1" onclick="showOptionMenu(1,\'educationalPostOPtion\')">' +
        '                    </div>' +
        '                </div>' +
        '            </div>' +
        '            <div class="postContentContainer">' +
        '                <div class="postData">' +
        '                    <div class="postMessage">' +
            postData.caption +
        '                    </div>' +
        '                    <div class="postPicture">' +
        '                        <div class="postPictureImageContainer">' +
        '                            <!--To only present the message without the picture, keep this value as null / empty-->' +
        '                            <img class="postPictureImage" src="../Resources/Images/EducationalPostImages/' + postData.EpostId +'.jpeg">' +
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
        '            </div>' +
        '        </div>';

    return post;

}

const displayMessage = function ( postData ){

    let post = '<div class="post">' +
        '            <div class="postContentContainer">' +
        '                <div class="postProfileSection">' +
        '                    <a href="TeacherProfile.html" class="postProfile">' +
        '                        <div class="postProfileImage">' +
        '                            <img class="postProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg">' +
        '                        </div>' +
        '                        <div class="postProfileName" >User Name</div>' +
        '                    </a>' +
        '                    <div class="postTimeAndDate">' +
        '                        18:32:26 | 03/25/2015' +
        '                    </div>' +
        '                    <div class="userOptions">' +
        '                        <input class="userOptionsButton" type="button" value="    " id="educationalPostOPtion7" onclick="showOptionMenu(7,\'educationalPostOPtion\')">' +
        '                    </div>' +
        '                </div>' +
        '            </div>' +
        '            <div class="postContentContainer">' +
        '                <div class="postData">' +
        '                    <div class="postMessage">' +
        '                        Due to the pandemic situations all classes will be canceled for two weeks, and we will start online class soon...Stay Safe' +
        '                    </div>' +
        '                    <div class="postPicture">' +
        '<!--                        To only present the message without the picture, comment the part below-->' +
        '                        <div class="postPictureImageContainer">' +
        '                            <img class="postPictureImage" src="../Resources/Images/seminar-text.jpg">' +
        '                        </div>' +
        '                    </div>' +
        '                </div>' +
        '            </div>' +
        '        </div>';

    return post;

}

const displayMcqPost = function ( postData ){

    let post = '<div class="post">' +
        '            <div class="postContentContainer">' +
        '                <div class="postProfileSection">' +
        '                    <a href="TeacherProfile.html" class="postProfile">' +
        '                        <div class="postProfileImage">' +
        '                            <img class="postProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg">' +
        '                        </div>' +
        '                        <div class="postProfileName" >User Name</div>' +
        '                    </a>' +
        '                    <div class="postTimeAndDate">' +
        '                        18:32:26 | 03/25/2015' +
        '                    </div>' +
        '                    <div class="userOptions">' +
        '                        <input class="userOptionsButton" type="button" value="    " id="educationalPostOPtion3" onclick="showOptionMenu(3,\'educationalPostOPtion\')">' +
        '                    </div>' +
        '                </div>' +
        '            </div>' +
        '            <div class="postContentContainer">' +
        '                <div class="postData">';

    for ( let i = 0; i < 10; i++ ) {

        post += '                    <div class="postMessage mcq">' +
            '                        Question ' + ( i + 1 ) +
            '                        <div class="mcqAnswerContainer">' +
            '                            <div class="mcqSingleAnswer">' +
            '                                MCQ Answer 1' +
            '                            </div>' +
            '                            <div class="mcqSingleAnswer">' +
            '                                MCQ Answer 2' +
            '                            </div>' +
            '                            <div class="mcqSingleAnswer correctMcqSingleAnswer">' +
            '                                MCQ Answer 3' +
            '                            </div>' +
            '                            <div class="mcqSingleAnswer">' +
            '                                MCQ Answer 4' +
            '                            </div>' +
            '                        </div>' +
            '                    </div>';

    }


    post +=    '                </div>' +
        '            </div>' +
        '            <div class="postContentContainer">' +
        '                <div class="postAnswerButton">' +
        '                    <div class="answerButton" >' +
        '                        <input type="button" value="Show Results" class="mcqResultShowButton" onclick="showMcqResult(1)">' +
        '                    </div>' +
        '                </div>' +
        '            </div>' +
        '            <div class="postContentContainer">' +
        '            </div>' +
        '        </div>';

    return post;


}

const selectEPostFromServer = function (){

    let httpreq = new XMLHttpRequest();
    let postContent = "";

    httpreq.onreadystatechange = function (){

        if ( this.status === 200 && this.readyState === 4 ){

            displayAll( this );

        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/selectEPostClassroom" , true );
    httpreq.send();

    const displayAll = function ( httpreq ){

        let jsonResponse = JSON.parse( httpreq.responseText );

        if( jsonResponse.serverResponse === "null Session" || jsonResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonResponse.serverResponse === "Allowed") {

            console.log( jsonResponse.ePosts );

            const ePostsLIst = jsonResponse.ePosts;

            for (let i = 0; i < ePostsLIst.length ; i++) {

                if ( ePostsLIst[i].EPtype === "MCQ" ){

                    postContent += displayMcqPost( ePostsLIst[i] );

                }else {

                    if ( ePostsLIst[i].type === "Question" ){

                        postContent += displayEducationalPost( ePostsLIst[i] );

                    }else {

                        postContent += displayMessage( ePostsLIst[i] );

                    }

                }

            }

            postData.innerHTML = "";
            postData.innerHTML += postContent;

        }else{
            alert("something went wrong!!!");
        }

    }



}