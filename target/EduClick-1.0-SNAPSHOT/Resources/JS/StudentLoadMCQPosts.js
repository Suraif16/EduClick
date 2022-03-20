const displayMcqPost = function (postData,TeacherFullName,TeaherId){
    console.log("In MCQ : "+postData.questionList[0].question)
    console.log("In MCQ : "+TeacherFullName)
    console.log("In MCQ : "+TeaherId)
    console.log("In MCQ Post ID : "+postData.EpostId)
    console.log("IN MCQ LOADING : "+postData.Answered)

    if(postData.Answered == "Yes"){

        let post =
            '<div class="post">' +
            '            <div class="postContentContainer">' +
            '                <div class="postProfileSection">' +
            '                    <a href="/EduClick_war_exploded/userProfileRedirect?userId=' +TeaherId + '" class="postProfile">' +
            '                        <div class="postProfileImage">' +
            '                            <img class="postProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg">' +
            '                        </div>' +
            '                        <div class="postProfileName">' + TeacherFullName + '</div>' +
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
            '                <div class="postData">'

        for ( let i = 0; i < 10; i++ ) {

            post += '<div class="postMessage mcq">' +
                postData.questionList[i]["question"] +
                '<div class="mcqAnswerContainer">';

            for (let j = 1; j < 5; j++) {

                let answerNumber = "answerNo";
                let answer = "answer";

                let htmlStringMCQ;

                if (postData.questionList[i][answerNumber + j] ===postData.questionList[i]["choice"]  ){

                    htmlStringMCQ = '<div class="mcqSingleAnswer choiceMcqSingleAnswer">' +
                        postData.questionList[i][ answer + j ]+
                        '</div>';

                }
                else if( postData.questionList[i][ answerNumber + j ] === postData.questionList[i][ "correctAnswer" ]){

                    htmlStringMCQ = '<div class="mcqSingleAnswer correctMcqSingleAnswer">' +
                        postData.questionList[i][ answer + j ]+
                        '</div>';
                }
                else{

                    htmlStringMCQ = '<div class="mcqSingleAnswer">' +
                        postData.questionList[i][ answer + j ] +
                        '</div>';

                }
                post+=htmlStringMCQ;


            }

            post +='</div>' +
                '</div>';

        }


        post +=  '  </div>' +
            '            </div>' +
            '                   <div class="postContentContainer">' +

            '                          <div class="postAnswerButton">' +

            '                               <div class="answerButton" >' +
            '                                       <input type="button" value="Show Results" class="mcqResultShowButton" onclick="showMcqResultOnLoad('+postData.EpostId+')">' +
            '                               </div>' +
            '                   </div>' +
            '            </div>'    +
            '   <div class="postContentContainer">' +
            '                <div style="display:none;" class="mcqResultsInPost" id="mcqResultsInPost'+postData.EpostId+'" >' +

            '               </div>'+
            '            </div>'+
            '        </div>'

        return post;

    }else{

        let post =
            '<div class="post">' +
            '            <div class="postContentContainer">' +
            '                <div class="postProfileSection">' +
            '                    <a href="/EduClick_war_exploded/userProfileRedirect?userId=' +TeaherId + '" class="postProfile">' +
            '                        <div class="postProfileImage">' +
            '                            <img class="postProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg">' +
            '                        </div>' +
            '                        <div class="postProfileName">' + TeacherFullName + '</div>' +
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
            '                <div class="postData">'
        let x = "[";
        for(i = 0 ; i<10; i++){


            x += postData.questionList[i].questionId + ",";
            let htmlString =
                '                    <div class="postMessage mcq">' +
                postData.questionList[i].question+
                '                        <div class="mcqAnswerContainer">' +
                '                            <div class="mcqSingleAnswer">' +
                '                                <input type="radio" id="MCQAnswer'+postData.questionList[i].answerNo1+'" name="MCQ'+postData.questionList[i].questionId+'" value="'+postData.questionList[i].answerNo1+'">' +
                '                                    <label for="MCQ Answer 1">' +
                postData.questionList[i].answer1+
                '                                    </label>' +
                '                            </div>' +
                '                            <div class="mcqSingleAnswer">' +
                '                                <input type="radio" id="MCQAnswer'+postData.questionList[i].answerNo2+'" name="MCQ'+postData.questionList[i].questionId+'" value="'+postData.questionList[i].answerNo2+'">' +
                '                                    <label for="MCQ Answer 2">' +
                postData.questionList[i].answer2 +
                '                                    </label>' +
                '                            </div>' +
                '                            <div class="mcqSingleAnswer">' +
                '                                <input type="radio" id="MCQAnswer'+postData.questionList[i].answerNo3+'" name="MCQ'+postData.questionList[i].questionId+'" value="'+postData.questionList[i].answerNo3+'">' +
                '                                    <label for="MCQ Answer 3">' +
                postData.questionList[i].answer3 +
                '                                    </label>\n' +
                '                            </div>' +
                '                            <div class="mcqSingleAnswer">' +
                '                                <input type="radio" id="MCQAnswer'+postData.questionList[i].answerNo4+'" name="MCQ'+postData.questionList[i].questionId+'" value="'+postData.questionList[i].answerNo4+'">' +
                '                                    <label for="MCQ Answer 4">' +
                postData.questionList[i].answer4 +
                '                                    </label>' +
                '                            </div>' +
                '                        </div>' +
                '                    </div>'
            post+=htmlString
        }
        x += "]";

        post += '                </div>' +
            '            </div>' +
            '<div class="postContentContainer">' +

            '                <div class="postAnswerButton">' +

            '                    <div class="answerButton" >' +
            '                        <input type="button" value="Submit and Finish" class="mcqResultShowButton" onclick="showMcqResult('+ x +","+postData.EpostId+')">' +
            '                    </div>' +
            '                </div>' +
            '            </div>'    +
            '<div class="postContentContainer">' +
            '                <div style="display:none;" class="mcqResultsInPost" id="mcqResultsInPost'+postData.EpostId+'" >' +

            '               </div>'+
            '            </div>'+
            '        </div>'

        return post;



    }




}


function calculateMarks(){

    let mcqAnswers = [];


    let postData = arrayX
    console.log("farzan")
    console.log( postData );
    for(i = 0; i < 10 ; i++){

        let MCQName = "MCQ"+postData.questionList[i].questionId;

        let option = document.getElementsByName(MCQName);

        for (let j = 0; j < option.length; j++) {
            console.log("asddsaudssaiuia fasduysadygd")
            if (option[j].checked){

                console.log("hsadgjhsakdbhjhdsf :  "+option[j].value)
                mcqAnswers[i] = option[j].value

            }
        }

    }


    submitMCQToServer( mcqAnswers,postData.EpostId);

}

const submitMCQToServer = function (mcqAnswers,postId,x){
    let goodToSubmit = 1;
    for (i = 0 ; i < 10 ; i++){
        console.log(mcqAnswers[i]+"\n")
        if(mcqAnswers[i] === undefined){
            goodToSubmit = 0;
        }
    }

    for(i=0;i<10;i++){
        console.log(x[i]);
    }


if(goodToSubmit === 1){
    let httpreq = new XMLHttpRequest();

    httpreq.onreadystatechange = function (){

        if ( this.readyState === 4 && this.status === 200 ){

            console.log( "done!!!");

            let jsonResponse = JSON.parse( httpreq.responseText );
            if( jsonResponse.serverResponse === "null Session" || jsonResponse.serverResponse === "Not Allowed"){
                window.location.replace("/EduClick_war_exploded/Login.html");
            }else if(jsonResponse.serverResponse === "Allowed"){

                console.log(jsonResponse.Result)
                let resultTag = "mcqResultsInPost"+postId
                let studentResult = document.getElementById(resultTag);
                studentResult.innerHTML="";
                let htmlString =
                    '<div class="mcqSingleStudentResult">' +
                    '          <div  class="mcqSingleStudentResultMarks">' +
                    "Your result is "+jsonResponse.Result+"%" +
                    '           </div>' +
                    '</div>'
                studentResult.innerHTML+=htmlString




            }else{
                alert("something went wrong!!!");
            }

        }

    }
    httpreq.open( "POST" , "/EduClick_war_exploded/student/mcqResultLoad" , true);
    httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
    httpreq.send( "mcq1=" + mcqAnswers[0] + "&mcq2=" + mcqAnswers[1] + "&mcq3=" + mcqAnswers[2] +"&mcq4=" + mcqAnswers[3] +"&mcq5=" + mcqAnswers[4] +"&mcq6=" + mcqAnswers[5] +"&mcq7=" + mcqAnswers[6] +"&mcq8=" + mcqAnswers[7] +"&mcq9=" + mcqAnswers[8] +"&mcq10=" + mcqAnswers[9] +"&classroomId=" + getClassroomIdClientSide() + "&postId=" +postId);

    "mcq1={ questionid :@ qidvalue , answerchoice : answerchoicevalue}&mcq2={}"

    // let a = mcq[i] + "={ questionid : " +  mcqquestionId[i] + ", answerchoice : " +  mcqAnswers[0] + " }&" + mcq[i+1]  + "={ questionid : " +  mcqquestionId[i] + ", answerchoice : " +  mcqAnswers[0] + " }"

}else{
    let mcqResultsInPostId = "mcqResultsInPost" + postId;
    let mcqResultsInPost = document.getElementById( mcqResultsInPostId );
    mcqResultsInPost.style.display = "none";
    alert("You cant keep empty fields")
}

}

const showMcqResultOnLoad = function (){




}






