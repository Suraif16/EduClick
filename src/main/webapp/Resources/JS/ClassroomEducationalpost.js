
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

        let httpreq = new XMLHttpRequest();
        let formData = new FormData();
        httpreq.onreadystatechange = function(){

            if ( this.readyState === 4 && this.status === 200 ){

                let output = httpreq.responseText;


            }

        }

        for (let i = 0 ; i < images.length ; i++ ){

            let x = "photo"+[i];
            formData.append( x , images[i] );
            console.log(i);

        }

        formData.append( "message" , message );
        formData.append( "type" , type );

        httpreq.open("POST","/EduClick_war_exploded/teacher/EducationalPostInsert" , true );
        httpreq.send( formData );

    }

}