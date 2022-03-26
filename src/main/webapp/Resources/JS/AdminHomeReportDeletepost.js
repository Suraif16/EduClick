function deleteAdminReportPostData(id ){
    console.log( "report"+id );
    deletereportpost(id );
}


const deletereportpost = function ( id ){

    console.log("delete" , id )

    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){
        if (this.readyState === 4 && this.status === 200){
            //completeLogin( this ) /*This is where we get the response when the request was successfully sent and a successfully response is received */
        }
    }

    httpreq.open( "POST" , "/EduClick_war_exploded/AdminDeleteReportPost" , true);
    httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
    httpreq.send("id=" + id );
    let now = new Date().getTime();
    let extraTime = 2000;
    while(new Date().getTime() < now + extraTime ){}
    postAutoprint ();
}