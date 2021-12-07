const mimeTypeArray = [ "image/apng" , "image/avif" , "image/jpeg" , "image/png" , "image/webp" , "image/bmp" , "image/x-icon" , "image/tiff" , "image/heic" , "image/heif" ];

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

        let isAllImageValid = false;

        for ( let i = 0; i < images.length; i++ ) {

            isAllImageValid = isImageAccepted( images[i].type )
            if ( !isAllImageValid ){
                console.log("break");
                break;

            }

        }

        if ( isAllImageValid ){

            let httpreq = new XMLHttpRequest();
            let formData = new FormData();
            httpreq.onreadystatechange = function(){

                if ( this.readyState === 4 && this.status === 200 ){

                    let output = httpreq.responseText;


                }

            }

            for ( let i = 0 ; i < images.length ; i++ ){

                let x = "photo"+[i];
                formData.append( x , images[i] );
                console.log(i);

            }

            formData.append( "message" , message );
            formData.append( "type" , type );

            httpreq.open("POST","/EduClick_war_exploded/teacher/EducationalPostInsert" , true );
            httpreq.send( formData );

        }else{

            console.log( "image type invalid" );

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