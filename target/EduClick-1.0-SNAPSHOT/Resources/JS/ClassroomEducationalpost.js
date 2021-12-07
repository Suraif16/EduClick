
const showAddEducationalPostForm = function (){

    let addEducationPostForm = document.getElementById( "addNewsFeedForm" );

    if ( addEducationPostForm.style.display === "none" ){

        addEducationPostForm.style.display = "flex";

    }else{

        addEducationPostForm.style.display = "none";

    }

}