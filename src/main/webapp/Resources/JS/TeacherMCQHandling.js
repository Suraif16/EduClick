const previousElement = document.getElementById( "previousQuestion" );
const nextElement = document.getElementById( "nextQuestion" );
const mcqAddPostForm = document.getElementById( "mcqAddPostForm");
const mcqSubmitButton = document.getElementById( "mcqSubmit" );
const arrayCount = 10;

let mcqContent = [];
let arrayQuestionIndex = 0;


previousElement.onclick = function (){

    insertDataIntoArray( false );
    arrayQuestionIndex = ( arrayQuestionIndex - 1 + arrayCount ) % arrayCount;
    getDataFromArrayToForm();

}

nextElement.onclick = function (){

    insertDataIntoArray( true );

}

const insertDataIntoArray = function ( previousCondition ){

    let jsonInputData = getAllInputs();

    if ( jsonInputData === false ){

        alert("fill the visible field to move to next or previous question");

    }else {

        mcqContent[ arrayQuestionIndex ] = jsonInputData;
        setInputDataToNull();
        if ( previousCondition ){

            arrayQuestionIndex = ( arrayQuestionIndex + 1 ) % arrayCount;
            adjustFormData();

        }

    }

    getDataFromArrayToForm();

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

            "Question" : Question,
            "Answer1" : answer1,
            "Answer2" : answer2,
            "Answer3" : answer3,
            "Answer4" : answer4,
            "CorrectAnswer" : correctAnswer

        }

    }

}

const setInputDataToNull = function (){

    document.getElementById( "mcqQuestion" ).value = null ;
    document.getElementById( "mcqAnswer1" ).value = null ;
    document.getElementById( "mcqAnswer2" ).value = null ;
    document.getElementById( "mcqAnswer3" ).value = null ;
    document.getElementById( "mcqAnswer4" ).value = null ;

    const radioElements = document.getElementsByName( "correctAnswerMCQ" );

    for (let i = 0; i < radioElements.length; i++) {

       radioElements[i].checked = false;

    }

}

const adjustFormData = function (){

    let questionCount = ( arrayQuestionIndex + 1 );console.log( "question count" , questionCount );
    document.getElementById( "mcqQuestionLabel" ).innerHTML = "Question " + questionCount +" : ";
    document.getElementById( "questionCount" ).innerHTML = "Question : " + questionCount + "/10";

    if ( questionCount > 1 ){

        previousElement.style.visibility = "visible";

    }else{

        previousElement.style.visibility = "hidden";

    }

    if ( questionCount === arrayCount ){

        document.getElementById( "mcqSubmit" ).style.display = "flex";
        nextElement.style.visibility = "hidden";

    }else{

        document.getElementById( "mcqSubmit" ).style.display = "none";
        nextElement.style.visibility = "visible";

    }

}

const getDataFromArrayToForm = function (){

    let currentArrayElement = mcqContent[ arrayQuestionIndex ];

    document.getElementById( "mcqQuestion" ).value = currentArrayElement[ "Question" ] ;
    document.getElementById( "mcqAnswer1" ).value = currentArrayElement[ "Answer1" ]  ;
    document.getElementById( "mcqAnswer2" ).value = currentArrayElement[ "Answer2" ]  ;
    document.getElementById( "mcqAnswer3" ).value = currentArrayElement[ "Answer3" ]  ;
    document.getElementById( "mcqAnswer4" ).value = currentArrayElement[ "Answer4" ]  ;

    console.log( currentArrayElement[ "CorrectAnswer" ] , "currect answer values");

    document.getElementById( "correctAnswer" + currentArrayElement[ "CorrectAnswer" ] ).checked = true;

    adjustFormData();

}

const showMcqAddPostForm = function (){

    mcqContent = [];
    arrayQuestionIndex = 0;
    adjustFormData();
    setInputDataToNull();
    if ( mcqAddPostForm.style.display === "flex" ){

        mcqAddPostForm.style.display = "none";

    }else{

        mcqAddPostForm.style.display = "flex";

    }

}

mcqSubmitButton.onclick = function (){

    insertDataIntoArray( false );
    console.log( mcqContent );
    submitMCQToServer( mcqContent );
    showMcqAddPostForm();

}

const submitMCQToServer = async function ( mcqContentArracy ){

    let dataString = "";

    for ( let i = 0 ; i < 10 ; i++ ) {

        dataString +="mcq" + ( i + 1 ) + "=";
        dataString += JSON.stringify( mcqContentArracy[i] );

        if ( i !== 9 ){

            dataString+="&";

        }

    }

    let httpreq = new XMLHttpRequest();

    httpreq.onreadystatechange = function (){

        if ( this.readyState === 4 && this.status === 200 ){

            console.log( "done!!!");

        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/mcqPostInsert" , true);
    httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
    httpreq.send( dataString );



}