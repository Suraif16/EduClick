const userProfileEditForm = document.getElementById( "userProfileEditForm" );

const showHideUserProfileEditForm = function (){

    if ( userProfileEditForm.style.display === "flex" ){

        userProfileEditForm.style.display = "none";

    }else{

        userProfileEditForm.style.display = "flex";
        getUserProfileDetails();

    }

}

const getUserProfileDetails = function (){

    let httpreq = new XMLHttpRequest();

    httpreq.onreadystatechange = function (){

        if ( this.readyState === 4 && this.status === 200 ){

            let jsonResponse = JSON.parse( this.responseText );

            displayUserProfileDetails( jsonResponse );

        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/user/selectEditProfileDetails" ,true );
    httpreq.setRequestHeader("Content-type", "application/x-www-form-urlencoded" );
    httpreq.send( "userId=" + getUserIdClientSide() );

}

const displayUserProfileDetails = function ( jsonResponse ){

    console.log( jsonResponse );

}