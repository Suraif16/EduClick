
const showAddEducationalPostForm = function (){

    let addEducationPostForm = document.getElementById( "addNewsFeedForm" );

    if ( addEducationPostForm.style.display === "none" ){

        addEducationPostForm.style.display = "flex";

    }else{

        addEducationPostForm.style.display = "none";

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

        console.log("not empty")

    }

}