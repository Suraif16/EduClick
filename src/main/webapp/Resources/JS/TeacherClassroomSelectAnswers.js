let ePostGetAnswerList = [];

function showAnswers( id ){

    let answerId = "answersInPost" + id;
    let answerContainer = document.getElementById( answerId );

    if (answerContainer.style.display === "none"){

        ePostGetAnswerList.push( id );

        answerContainer.style.display = "flex";

    }else{

        ePostGetAnswerList.splice( ePostGetAnswerList.indexOf( id ) , 1 );

        answerContainer.style.display = "none";

    }

}

const getAnswersServer = function ( id ){

    let answerId = "answersInPost" + id;
    let answerContainer = document.getElementById( answerId );

    let httpreq = new XMLHttpRequest();

    httpreq.onreadystatechange = function (){

        if ( this.readyState === 4 && this.status === 200 ){

            complete( this )

        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/teacherClassroomSelectAnswersServlet" , true );
    httpreq.setRequestHeader( "Content-type" , "application/x-www-form-urlencoded" );
    httpreq.send( "epostId=" + id );

    const complete = function ( httpreq ){

        let jsonObject = JSON.parse( httpreq.responseText );
        console.log( jsonObject );
        let answerList = jsonObject.answerList;

        answerContainer.innerHTML = "";

        for ( let i = 0 ; i < answerList.length ; i++ ) {

            let singleAnswer = '<div class="singleAnswer">' +
                '<div class="answerUser">' +
                '    <a href="/EduClick_war_exploded/userProfileRedirect?userId=' + answerList[i].userId + '" class="answerProfile">' +
                '        <div class="answerProfileImage">' +
                '            <img class="answerProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg">' +
                '        </div>' +
                '        <div class="answerProfileName" >' + answerList[i].studentName + '</div>' +
                '    </a>' +
                '    <div class="postTimeAndDate">' +
                answerList[i].answerTime + ' | ' + answerList[i].answerDate +
                '    </div>' +
                '    <div class="userOptions">' +
                '        <input class="userOptionsButton" type="button" value="    " id="answerOption1" onclick="showOptionMenu(1,\'answerOption\')">' +
                '    </div>' +
                '</div>' +
                '<div class="textAnswers">' +

                answerList[i].content +

                '</div>';

            if ( answerList[i].imageStatus === "true" ){

                singleAnswer +=    '<div class="pictureAnswers">' +
                    '    <a href="#">' +
                    '        <img src="../Resources/Images/AnswerImages/' + answerList[i].answerId + '.jpeg">' +
                    '    </a>' +
                    '</div>';

            }

            singleAnswer +=    '<div class="marksForAnswers">' +
                '    <input type="range" value="0" max="100" oninput="answer' + answerList[i].answerId + '.value = this.value" class="marksForAnswersRange">' +
                '    <output id="answer' + answerList[i].answerId + '" class="marksForAnswersRangeValue">0</output>' +
                '</div>' +
                '</div>';

            answerContainer.innerHTML += singleAnswer;

        }

    }

}

const traverseEPostListToGetAnswers = function (){

    ePostGetAnswerList.forEach( function ( id ){

        getAnswersServer( id );

    });

}

setInterval( traverseEPostListToGetAnswers , 2000 );

function showMcqResult( id ){

    let mcqResultsInPostId = "mcqResultsInPost" + id;
    let mcqResultsInPost = document.getElementById( mcqResultsInPostId );

    if (mcqResultsInPost.style.display === "none"){

        mcqResultsInPost.style.display = "flex";

    }else{

        mcqResultsInPost.style.display = "none";

    }


}