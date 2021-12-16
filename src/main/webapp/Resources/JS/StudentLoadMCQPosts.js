const displayMcqPost = function (postData,TeacherFullName,TeaherId){
    console.log("In MCQ : "+postData.questionList[0].question)
    console.log("In MCQ : "+TeacherFullName)
    console.log("In MCQ : "+TeaherId)
    console.log("In MCQ Post ID : "+postData.EpostId)

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
        for(i = 0 ; i<10; i++){
            let htmlString =
                '                    <div class="postMessage mcq">' +
                postData.questionList[i].question+
                '                        <div class="mcqAnswerContainer">' +
                '                            <div class="mcqSingleAnswer">' +
                '                                <input type="radio" id="MCQ Answer 1" name="MCQ1">' +
                '                                    <label for="MCQ Answer 1">' +
                postData.questionList[i].answer1+
                '                                    </label>' +
                '                            </div>' +
                '                            <div class="mcqSingleAnswer">' +
                '                                <input type="radio" id="MCQ Answer 2" name="MCQ1">' +
                '                                    <label for="MCQ Answer 2">' +
                postData.questionList[i].answer2 +
                '                                    </label>' +
                '                            </div>' +
                '                            <div class="mcqSingleAnswer">' +
                '                                <input type="radio" id="MCQ Answer 3" name="MCQ1">' +
                '                                    <label for="MCQ Answer 3">' +
                postData.questionList[i].answer3 +
                '                                    </label>\n' +
                '                            </div>' +
                '                            <div class="mcqSingleAnswer">' +
                '                                <input type="radio" id="MCQ Answer 4" name="MCQ1">' +
                '                                    <label for="MCQ Answer 4">' +
                postData.questionList[i].answer4 +
                '                                    </label>' +
                '                            </div>' +
                '                        </div>' +
                '                    </div>'
            post+=htmlString
        }

        post += '                </div>' +
                 '            </div>' +
                '<div class="postContentContainer">' +

                '                <div class="postAnswerButton">' +

                '                    <div class="answerButton" >' +
                '                        <input type="button" value="Show Marks" class="mcqResultShowButton" onclick="showMcqResult('+postData.EpostId+')">' +
                '                    </div>' +
                '                </div>' +
                '            </div>'    +
                '<div class="postContentContainer">' +
                '                <div style="display:none;" class="mcqResultsInPost" id="mcqResultsInPost'+postData.EpostId+'" >' +
                '                    <div class="mcqSingleStudentResult">' +
                '                        <a href="#" class="mcqProfile">' +
                '                            <div class="mcqProfileImage">' +
                '                                <img class="mcqProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg">' +
                '                            </div>' +
                '                            <div>Student Name</div>' +
                '                        </a>' +
                '                        <div class="mcqSingleStudentResultMarks">' +

                '                            25%' +
                '                        </div>' +
                '                    </div>'+
                '               </div>'+
                '            </div>'+
                '        </div>'

    return post;

}