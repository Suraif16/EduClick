function deleteAdminPostData(id ){
    console.log( id );
    deletepost(id );
}


const deletepost = function ( id ){

    console.log("delete" , id )

    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){
        if (this.readyState === 4 && this.status === 200){
            //completeLogin( this ) /*This is where we get the response when the request was successfully sent and a successfully response is received */
        }
    }

    httpreq.open( "POST" , "/EduClick_war_exploded/AdminDataDelete" , true);
    httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
    httpreq.send("id=" + id );

    document.onreadystatechange = function (){
        if ( document.readyState === 'complete' ){
            /* when the document is loaded and complete this function will run*/
            postAutoprint ();
        }
    }
}