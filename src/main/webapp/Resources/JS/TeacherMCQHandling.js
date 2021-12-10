const previousElement = document.getElementById( "previousQuestion" );
const nextElement = document.getElementById( "nextQuestion" );
const mcqAddPostForm = document.getElementById( "mcqAddPostForm");
const arrayCount = 10;

let mcqContent = [];
let arrayQuestionIndex = 0;


previousElement.onclick = function (){

    arrayQuestionIndex = ( arrayQuestionIndex - 1 + arrayCount ) % arrayCount;
    console.log( "clicked previous" , arrayQuestionIndex );

}

nextElement.onclick = function (){

    arrayQuestionIndex = ( arrayQuestionIndex + 1 ) % arrayCount;
    console.log( "clicked next" , arrayQuestionIndex );

}

const showMcqAddPostForm = function (){

    if ( mcqAddPostForm.style.display === "flex" ){

        mcqAddPostForm.style.display = "none";

    }else{

        mcqAddPostForm.style.display = "flex";

    }

}