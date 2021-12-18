const mimeTypeArray = [ "image/apng" , "image/avif" , "image/jpeg" , "image/png" , "image/webp" , "image/bmp" , "image/x-icon" , "image/tiff" , "image/heic" , "image/heif" ];

const showAddEducationalPostForm = function (){

    const addEducationPostForm = document.getElementById( "addNewsFeedForm" );

    if ( addEducationPostForm.style.display === "flex" ){

        addEducationPostForm.style.display = "none";

    }else{

        addEducationPostForm.style.display = "flex";

    }

}

const postQuestionsMessages = function (){
    getClassroomIdClientSide();
    let message = document.getElementById( "addNewsFeedFormTextArea" ).value;
    let images = document.getElementById( "inputImage" ).files;
    let type = document.getElementById( "ePostType" ).value;

    console.log( message , images , type );

    if ( message === "" && images.length === 0 ){

        console.log("empty")

    }else{

        let isAllImageValid = false;

        if ( images.length === 0 ){

            isAllImageValid = true;

        }else{

            for ( let i = 0; i < images.length; i++ ) {

                isAllImageValid = isImageAccepted( images[i].type )
                if ( !isAllImageValid ){
                    console.log("break");
                    break;

                }

            }

        }

        if ( isAllImageValid ){

            let httpreq = new XMLHttpRequest();
            let formData = new FormData();
            httpreq.onreadystatechange = function(){

                if ( this.readyState === 4 && this.status === 200 ){

                    complete( this );


                }

            }

            for ( let i = 0 ; i < images.length ; i++ ){

                let x = "photo"+[i];
                formData.append( x , images[i] );
                console.log(i);

            }

            formData.append( "message" , message );
            formData.append( "type" , type );
            formData.append( "classroomId" , getClassroomIdClientSide() );

            httpreq.open("POST","/EduClick_war_exploded/teacher/EducationalPostInsert" , true );
            httpreq.send( formData );

        }else{

            console.log( "image type invalid" );

        }

    }

    const complete = function ( httpreq ){

        let jsonResponse = JSON.parse( httpreq.responseText );

        if( jsonResponse.serverResponse === "null Session" || jsonResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonResponse.serverResponse === "Allowed") {
            console.log( jsonResponse.EPost );

            document.getElementById( "addNewsFeedFormTextArea" ).value = null;
            document.getElementById( "inputImage" ).value = null;

            showAddEducationalPostForm();

            selectMoreStatus = true;
            setTimeout( function (){

                    ePostGetAnswerList = [];
                selectEPostFromServer( false );

                }
                , 3000 );

        }else{
            alert("something went wrong!!!");
        }

    }

}

const isImageAccepted = function ( type ){

    const arrayLength = mimeTypeArray.length;

    for ( let i = 0; i < arrayLength; i++ ) {

        if ( mimeTypeArray[i] === type ){

            return true;

        }

    }

    return false;

}