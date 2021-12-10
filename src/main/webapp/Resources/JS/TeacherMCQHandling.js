const previousElement = document.getElementById( "previousQuestion" );
const nextElement = document.getElementById( "nextQuestion" );
const mcqAddPostForm = document.getElementById( "mcqAddPostForm");
const arrayCount = 10;

let mcqContent = [];
let arrayQuestionIndex = 0;


previousElement.onclick = function (){

    arrayQuestionIndex = ( arrayQuestionIndex - 1 + arrayCount ) % arrayCount;
    console.log( "clicked previous" , arrayQuestionIndex );
    console.log( getAllInputs() );

}

nextElement.onclick = function (){

    arrayQuestionIndex = ( arrayQuestionIndex + 1 ) % arrayCount;
    console.log( "clicked next" , arrayQuestionIndex );
    console.log( getAllInputs() );

}

const getAllInputs = function (){

    let Question = document.getElementById( "mcqQuestion" ).value ;
    let answer1 = document.getElementById( "mcqAnswer1" ).value ;
    let answer2 = document.getElementById( "mcqAnswer2" ).value ;
    let answer3 = document.getElementById( "mcqAnswer3" ).value ;
    let answer4 = document.getElementById( "mcqAnswer4" ).value ;


    const getCorrectAnswer = function (){
        /* gets the selected value from radio buttons*/

        const radioElements = document.getElementsByName( "correctAnswerMCQ" );

        for (let i = 0; i < radioElements.length; i++) {

            if ( radioElements[i].checked ){

                return radioElements[i].value

            }

        }


    }

    let correctAnswer = getCorrectAnswer();

    console.log( Question , answer1 , answer2 , answer3 , answer4 , correctAnswer );
    /* checks if the user has inserted all required values, if yes returns the data in an json object literal, else returns false */
    if ( Question === undefined || answer1 === undefined || answer2 === undefined || answer3 === undefined || answer4 === undefined || correctAnswer === undefined ){

        return false

    }else {

        return {

            "question" : Question,
            "Answer1" : answer1,
            "Answer2" : answer2,
            "Answer3" : answer3,
            "Answer4" : answer4,
            "CorrectAnswer" : correctAnswer

        }

    }

}

const showMcqAddPostForm = function (){

    if ( mcqAddPostForm.style.display === "flex" ){

        mcqAddPostForm.style.display = "none";

    }else{

        mcqAddPostForm.style.display = "flex";

    }

}