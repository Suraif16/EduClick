const previousElement = document.getElementById( "previousQuestion" );
const nextElement = document.getElementById( "nextQuestion" );
const mcqAddPostForm = document.getElementById( "mcqAddPostForm");

previousElement.onclick = function (){

    console.log( "clicked previous" );

}

nextElement.onclick = function (){

    console.log( "clicked next" );

}

const showMcqAddPostForm = function (){

    if ( mcqAddPostForm.style.display === "flex" ){

        mcqAddPostForm.style.display = "none";

    }else{

        mcqAddPostForm.style.display = "flex";

    }

}