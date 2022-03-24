function postAutoprint (){
    getServerData();
    console.log( "send signal" );
}


const getServerData = function (){
    console.log( "send signal" );

    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){
            complete( this );
        }

    }

    httpreq.open( "GET" , "/EduClick_war_exploded/ReportDataselect" , true);
    httpreq.send();

    function complete( httpreq ) {

        let jsonResponse = JSON.parse(httpreq.responseText);
        const postContents = document.getElementById("request");
        postContents.innerHTML = "";
        const one = "1";
        let count = jsonResponse.ReportPostDetails.length;
        for( i=0; i< count; i++ ){
            if(jsonResponse.ReportPostDetails[i].status === one){
                let htmlString ='<div class="post">' +
                    '<div class="postContentContainer">'+
                    '<div class="postProfileSection">'+
                    '<a href="#" class="postProfile">'+
                    '<div class="postProfileImage">'+
                    '<img class="postProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg">'+
                    '</div>'+
                    '<div class="postProfileName" >Admin</div>'+
                    '<div class="postTimeAndDate">'+jsonResponse.ReportPostDetails[i].date+'|'+ jsonResponse.ReportPostDetails[i].time+'</div>'+
                    '</a>'+
                    '</div>'+
                    '</div>'+
                    '<div class="postContentContainer">'+
                    '<div class="postData">'+
                    '<div class="postMessage">'+
                    jsonResponse.ReportPostDetails[i].caption+
                    '</div>'+
                    '<div class="postPicture">' +
                    '<div class="postPictureImageContainer">' +
                    '<img class="postPictureImage" src="../Resources/Images/AdminPostImages/' + jsonResponse.ReportPostDetails[i].aPId + '.jpeg">' +
                    '</div>'+
                    '</div>'+
                    '</div>'+
                    '<div class="postContentContainer">'+
                    '<div class="postData">'+
                    '<input type="button" value="-Delete" class="share" onclick=" ">'+
                    '</div>'+
                    '</div>'+
                    '</div>'+
                    '</div>';
                postContents.innerHTML += htmlString;
            }else if(jsonResponse.ReportPostDetails[i].status === one/*check thi one*/){
                let htmlString ='<div class="post">' +
                    '<div class="postContentContainer">'+
                    '<div class="postProfileSection">'+
                    '<a href="#" class="postProfile">'+
                    '<div class="postProfileImage">'+
                    '<img class="postProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg">'+
                    '</div>'+
                    '<div class="postProfileName" >Admin</div>'+
                    '<div class="postTimeAndDate">'+jsonResponse.ReportPostDetails[i].date+'|'+ jsonResponse.ReportPostDetails[i].time+'</div>'+
                    '</a>'+
                    '</div>'+
                    '</div>'+
                    '<div class="postContentContainer">'+
                    '<div class="postLikeShareButtons">'+
                    '<a href="#" class="postProfile">'+
                    '<div className="postProfileName">'+
                    Count_of_the_report_100+
                    '</div>'+
                    '</a>'+
                    '</div>'+
                    '<div class="emptySpaceLikeShare">'+
                    '</div>'+
                    '<div class="likeShareButtons">'+
                    '<input type="button" value="Remove" class="share" onclick="" >'+
                    '</div>'+
                    '</div>'+
                    '</div>';
                postContents.innerHTML += htmlString;
            }else{
                let htmlString ='<div class="post">' +

                    '<div class="postContentContainer">'+
                    '<div class="postProfileSection">'+
                    '<a href="#" class="postProfile">'+
                    '<div class="postProfileImage">'+
                    '<img class="postProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg">'+
                    '</div>'+
                    '<div class="postProfileName" >'+
                     Admin+
                    '</div>'+
                    '<div class="postTimeAndDate">'+jsonResponse.ReportPostDetails[i].date+'|'+ jsonResponse.ReportPostDetails[i].time+
                    '</div>'+
                    '</a>'+
                    '</div>'+
                    '</div>'+

                    '<div class="postContentContainer">'+
                    '<div class="postData">'+
                    '<div class="postMessage">'+
                    jsonResponse.ReportPostDetails[i].caption+
                    '</div>'+
                    '</div>'+
                    '</div>'+

                    '<div class="postContentContainer">' +
                    '<div class="postLikeShareButtons">' +
                    '<a href="#" class="postProfile">'+
                    '<div class="postProfileName" >'+
                    'Count of the report : 100'+
                    '</div>'+
                    '</a>'+
                    '<div class="emptySpaceLikeShare">'+
                    '</div>'+
                    '<div class="likeShareButtons">'+
                    '<input type="button" value="-Remove" class="share" onclick=" ">'+
                    '</div>'+
                    '</div>'+
                    '</div>'+

                    '</div>';
                postContents.innerHTML += htmlString;
            }
        }
    }

}