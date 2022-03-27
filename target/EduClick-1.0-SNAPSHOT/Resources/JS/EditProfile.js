const userProfileEditForm = document.getElementById( "userProfileEditForm" );
const firstNameElement = document.getElementById( "firstName" );
const lastNameElement  = document.getElementById( "lastName" );
const countryElement = document.getElementById( "country" );
const cityElement = document.getElementById( "city" );
const userProfileImage = document.getElementById( "userProfileEditFormRowProfileImage" );
let userProfileType;
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

    if ( jsonResponse.serverResponse === "Allowed" ){

        firstNameElement.value = jsonResponse.firstName;
        lastNameElement.value = jsonResponse.lastName;
        countryElement.value = jsonResponse.country;
        cityElement.value = jsonResponse.city;

        if ( jsonResponse.userType === "Teacher"){

            let workplace = jsonResponse.workPlace;

            if ( workplace !== undefined ){

                document.getElementById( "" ).value = workplace;

            }

            let image = jsonResponse.profilePicture;

            if ( image !== undefined ){

                userProfileImage.innerHTML = '<img src="../Resources/Images/answers2.jpg/profilePicture' + image + '.jpeg" alt="">';

            }else{

                userProfileImage.innerHTML = '<img src="../Resources/Icons/account_circle_white_24dp.svg" alt="">';

            }

        }/*else for Student*/

    }else{

        alert( "This operation is not allowed" );

    }


}