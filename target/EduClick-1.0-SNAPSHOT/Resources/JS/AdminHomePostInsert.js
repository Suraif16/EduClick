let addClassroomFormStatus = false; /*if it is false the addClassroomForm is hidden*/
const addClassroomForm = document.getElementById("addNewsFeedForm");
function showAddClassroomFrom(){
    if(addClassroomFormStatus){
        addClassroomForm.style.display = "none";
        addClassroomFormStatus = false;
    }else {
        addClassroomForm.style.display = "flex";
        addClassroomFormStatus = true;
    }
}




const mimeTypeArray = [ "image/apng" , "image/avif" , "image/jpeg" , "image/png" , "image/webp" , "image/bmp" , "image/x-icon" , "image/tiff" , "image/heic" , "image/heif" ];

const postQuestionsMessages = function (){
    showAddClassroomFrom();
    let message = document.getElementById( "addNewsFeedFormTextArea" ).value;
    let images = document.getElementById( "inputImage" ).files;
    console.log( message , images );
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
                    //complete( this );
                }
            }

            for ( let i = 0 ; i < images.length ; i++ ){
                let x = "photo"+[i];
                formData.append( x , images[i] );
                console.log(i);
            }

            formData.append( "message" , message );
            httpreq.open("POST","/EduClick_war_exploded/AdminPost" , true );
            httpreq.send( formData );

            let now = new Date().getTime();
            let extraTime = 3000;
            while(new Date().getTime() < now + extraTime ){}
            postAutoprint ();
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