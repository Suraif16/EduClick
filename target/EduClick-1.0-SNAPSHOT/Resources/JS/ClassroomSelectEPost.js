let reloadStatus = true;
const postDataContent = document.getElementById( "postContents" );
let minEPostId = Infinity;
let selectMoreStatus = true;
let userName = "";
let userId = "";

window.onscroll = function (){

    if ( ( window.innerHeight + window.pageYOffset ) > document.body.offsetHeight && reloadStatus ){

        console.log( "At the bottom!!!" );
        reloadStatus = false;
        selectEPostFromServer( true );


    }

};

const displayEducationalPost = function ( postData ){

    let post = '<div class="post">' +
        '<div class="postContentContainer">' +
        '<div class="postProfileSection">' +
        '<a href="/EduClick_war_exploded/userProfileRedirect?userId=' + userId + '" class="postProfile">' +

        '<div class="postProfileImage">' +
        '<img class="postProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg">' +
        '</div>' +
        '<div class="postProfileName" >' + userName + '</div>' +
        '</a>' +
        '<div class="postTimeAndDate">' +
        postData.time +' | '+ postData.date +
        '</div>' +
        '<div class="userOptions">' +
        '<input class="userOptionsButton" type="button" value="    " id="educationalPostOPtion'+ postData.EpostId +'" onclick="showOptionMenu('+ postData.EpostId +',\'educationalPostOPtion\')">' +
        '</div>' +
        '</div>' +
        '</div>' +
        '<div class="postContentContainer">' +
        '<div class="postData">' +
        '<div class="postMessage">' +
        postData.caption +
        '</div>' +
        '<div class="postPicture">';

    if ( postData.imageStatus === "true" ){

        post += '<div class="postPictureImageContainer">' +
            '<img class="postPictureImage" src="../Resources/Images/EducationalPostImages/' + postData.EpostId +'.jpeg">' +
                '</div>';

    }

        post += '</div>' +
        '</div>' +
        '</div>' +
        '<div class="postContentContainer">' +
        '<div class="postAnswerButton">' +
        '<div class="answerButton" >' +
        '<input type="button" value="Answer" class="answerShowButton" onclick="showAnswers('+ postData.EpostId +')">' +
        '</div>' +
        '</div>' +
        '</div>' +
        '<div class="postContentContainer">' +
            '<div style="display:none;" class="answersInPost" id="answersInPost'+ postData.EpostId +'" >' +
            '</div>'+
        '</div>' +
        '</div>';

    return post;

}

const displayMessage = function ( postData ){

    let post = '<div class="post">' +
        '<div class="postContentContainer">' +
        '<div class="postProfileSection">' +
        '<a href="/EduClick_war_exploded/userProfileRedirect?userId=' + userId + '" class="postProfile">' +
        '<div class="postProfileImage">' +
        '<img class="postProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg">' +
        '</div>' +
        '<div class="postProfileName" >' + userName + '</div>' +
        '</a>' +
        '<div class="postTimeAndDate">' +
        postData.time +' | '+ postData.date +
        '</div>' +
        '<div class="userOptions">' +
        '<input class="userOptionsButton" type="button" value="    " id="educationalPostOPtion'+ postData.EpostId +'" onclick="showOptionMenu('+ postData.EpostId +',\'educationalPostOPtion\')">' +
        '</div>' +
        '</div>' +
        '</div>' +
        '<div class="postContentContainer">' +
        '<div class="postData">' +
        '<div class="postMessage">' +
        postData.caption +
        '</div>' +
        '<div class="postPicture">';

    if ( postData.imageStatus === "true" ){

        post += '<div class="postPictureImageContainer">' +
            '<img class="postPictureImage" src="../Resources/Images/EducationalPostImages/' + postData.EpostId +'.jpeg">' +
            '</div>';

    }

        post += '</div>' +
        '</div>' +
        '</div>' +
        '</div>';

    return post;

}

const displayMcqPost = function ( postData ){

    let post = '<div class="post">' +
        '<div class="postContentContainer">' +
        '<div class="postProfileSection">' +
        '<a href="/EduClick_war_exploded/userProfileRedirect?userId=' + userId + '" class="postProfile">' +
        '<div class="postProfileImage">' +
        '<img class="postProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg">' +
        '</div>' +
        '<div class="postProfileName" >' + userName + '</div>' +
        '</a>' +
        '<div class="postTimeAndDate">' +
            postData.time + ' | ' + postData.date +
        '</div>' +
        '<div class="userOptions">' +
        '<input class="userOptionsButton" type="button" value="    " id="educationalPostOPtion'+ postData.EpostId +'" onclick="showOptionMenu('+ postData.EpostId +',\'educationalPostOPtion\')">' +
        '</div>' +
        '</div>' +
        '</div>' +
        '<div class="postContentContainer">' +
        '<div class="postData">';

    for ( let i = 0; i < 10; i++ ) {

        post += '<div class="postMessage mcq">' +
              'Q'+ ( i + 1 ) + '. ' +  postData.questionList[i]["question"] +
            '<div class="mcqAnswerContainer">';

        for (let j = 1; j < 5; j++) {

            let answerNumber = "answerNo";
            let answer = "answer";

            if ( postData.questionList[i][ answerNumber + j ] === postData.questionList[i][ "correctAnswer" ] ){

                post += '<div class="mcqSingleAnswer correctMcqSingleAnswer">' +
                    j + '. ' + postData.questionList[i][ answer + j ]+
                        '</div>';

            }else{

                post += '<div class="mcqSingleAnswer">' +
                    j + '. ' + postData.questionList[i][ answer + j ] +
                        '</div>';

            }

        }

        post +='</div>' +
            '</div>';

    }


    post +=    '</div>' +
        '</div>' +
        '<div class="postContentContainer">' +
        '<div class="postAnswerButton">' +
        '<div class="answerButton" >' +
        '<input type="button" value="Show Results" class="mcqResultShowButton" onclick="showMcqResult(' + postData.EpostId + ')">' +
        '</div>' +
        '</div>' +
        '</div>' +
        '<div class="postContentContainer">' +
        '<div style="display:none;" class="mcqResultsInPost" id="mcqResultsInPost' + postData.EpostId + '" >' +
        '</div>' +
        '</div>' +
        '</div>';

    return post;


}

const selectEPostFromServer = function ( scrollStatus ){
    let classroomId = getClassroomIdClientSide();
    let httpreq = new XMLHttpRequest();
    let postContent = "";
    let selectId = null;

    if ( !scrollStatus ){

        selectId = -1;
        document.getElementById( "postContents" ).innerHTML = "";
        minEPostId = Infinity;

    }else {

        selectId = minEPostId;

    }

    httpreq.onreadystatechange = function (){

        if ( this.status === 200 && this.readyState === 4 ){

            displayAll( this );

        }

    }

    if ( selectMoreStatus ){

        httpreq.open( "POST" , "/EduClick_war_exploded/teacher/selectEPostClassroom" , true );
        httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
        console.log( "its the selected id here : " , selectId)
        httpreq.send("id=" + selectId + "&classroomId=" + classroomId );
        console.log(" in if condition and select is sent to server ")

    }

    const displayAll = function ( httpreq ){

        let jsonResponse = JSON.parse( httpreq.responseText );

        if( jsonResponse.serverResponse === "null Session" || jsonResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonResponse.serverResponse === "Allowed") {

            console.log( jsonResponse );

            userName = jsonResponse.userName;
            userId = jsonResponse.userId;

            const ePostsLIst = jsonResponse.ePosts;

            if ( ePostsLIst.length === 0 ){

                selectMoreStatus = false;

            }

            for (let i = 0; i < ePostsLIst.length ; i++) {

                if ( ePostsLIst[i].EpostId < minEPostId ){

                    minEPostId = ePostsLIst[i].EpostId;

                }

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

            postDataContent.innerHTML += postContent;
            reloadStatus = true;

        }else{
            alert("something went wrong!!!");
        }

    }

}

