const userProfileEditForm = document.getElementById( "userProfileEditForm" );
const firstNameElement = document.getElementById( "firstName" );
const lastNameElement  = document.getElementById( "lastName" );
const countryElement = document.getElementById( "country" );
const cityElement = document.getElementById( "city" );
const userProfileImage = document.getElementById( "userProfileEditFormRowProfileImage" );
const imageInsertIcon = document.getElementById( "inputImage" );

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

        userProfileType = jsonResponse.userType;

        firstNameElement.value = jsonResponse.firstName;
        lastNameElement.value = jsonResponse.lastName;
        countryElement.value = jsonResponse.country;
        cityElement.value = jsonResponse.city;

        let image = jsonResponse.profilePicture;

        if ( image !== undefined ){

            userProfileImage.innerHTML = '<img src="../Resources/Images/answers2.jpg/profilePicture' + image + '.jpeg" alt="">';

        }else{

            userProfileImage.innerHTML = '<img src="../Resources/Icons/account_circle_white_24dp.svg" alt="">';

        }

        if ( userProfileType === "Teacher"){

            let workplace = jsonResponse.workPlace;

            if ( workplace !== undefined ){

                document.getElementById( "workPlace" ).value = workplace;

            }



        }/*else for Student*/

    }else{

        alert( "This operation is not allowed" );

    }


}



imageInsertIcon.addEventListener( "change" , function (){

    let imageFile = document.createElement( "IMG" );
    imageFile.src = URL.createObjectURL( imageInsertIcon.files[0] );

    userProfileImage.innerHTML = "";
    userProfileImage.appendChild( imageFile );

});

const saveProfileEditForm = function (){

    let workPlace = null;

    if ( userProfileType === "Teacher"){

        workPlace = document.getElementById( "workPlace" ).value;


    }/*else for Student*/

    console.log( firstNameElement.value , lastNameElement.value , countryElement.value , cityElement.value , workPlace , imageInsertIcon.files[0] );

    /*let httpreq = new XMLHttpRequest();
    let formData = new FormData();

    httpreq.onreadystatechange = function(){

        if ( this.readyState === 4 && this.status === 200 ){

        //


        }

    }

    formData.append( "profileImage" , imageInsertIcon.files[0] );
    formData.append( "firstName" ,  );
    formData.append( "lastName" ,  );
    formData.append( "lastName" ,  );

    httpreq.open("POST","/EduClick_war_exploded/teacher/EducationalPostInsert" , true );
    httpreq.send( formData );*/

}