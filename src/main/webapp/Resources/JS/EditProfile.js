const userProfileEditForm = document.getElementById( "userProfileEditForm" );

const showHideUserProfileEditForm = function (){

    if ( userProfileEditForm.style.display === "flex" ){

        userProfileEditForm.style.display = "none";

    }else{

        userProfileEditForm.style.display = "flex";

    }

}