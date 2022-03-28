document.onreadystatechange = function (){
    if ( document.readyState === 'complete' ){
        /* when the document is loaded and complete this function will run*/
        postAutoprint ();
    }
}

function postAutoprint (){
    getServerData();
    console.log( "send report signal" );
}


const getServerData = function (){
    console.log( "send signal" );

    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){
            complete( this );
        }

    }

    httpreq.open( "GET" , "/EduClick_war_exploded/ReportnewsfeedDataselect" , true);
    console.log( "send signal" );

    httpreq.send();

    function complete( httpreq ) {

        let jsonResponse = JSON.parse(httpreq.responseText);
        const postContents = document.getElementById("postContents");
        postContents.innerHTML = "";


        for( i=0; i< jsonResponse.ReportPostDetails.length; i++ ){
            if(jsonResponse.ReportPostDetails[i].profilePic === undefined){
                if(jsonResponse.ReportPostDetails[i].imageStatus === "true"){
                    let htmlString ='<div class="post">'+
                        '<div class="postContentContainer">'+
                        '<div class="postProfileSection">'+
                        '<a href="#" class="postProfile">'+
                        '<div class="postProfileImage">'+
                        ' <img class="postProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg">'+
                        '</div>'+
                        '<div class="postProfileName" >'+jsonResponse.ReportPostDetails[i].firstName+' '+jsonResponse.ReportPostDetails[i].lastName+'</div>'+
                        '<div class="postTimeAndDate">'+jsonResponse.ReportPostDetails[i].date+'|'+ jsonResponse.ReportPostDetails[i].time+'</div>'+
                        '</a>'+
                        '</div>'+
                        '</div>'+
                        '<div class="postContentContainer">'+
                        '<div class="postData">'+
                        '<div class="postMessage">'+
                        jsonResponse.ReportPostDetails[i].caption+
                        '</div>'+
                        '<div class="postPicture">'+
                        '<div class="postPictureImageContainer">'+
                        '<img class="postPictureImage" src="../Resources/Images/NewsFeedImages/' + jsonResponse.ReportPostDetails[i].imagePath + '.jpeg">'+
                        '</div>'+
                        '</div>'+
                        '</div>'+
                        '</div>'+

                        '<div class="postContentContainer">'+
                        '<div class="postLikeShareButtons">'+
                        '<a href="#" class="postProfile">'+
                        '<div class="postProfileName" >'+'Count of the report :'+jsonResponse.ReportPostDetails[i].count+'</div>'+
                        '</a>'+
                        '<div class="emptySpaceLikeShare" ></div>'+
                        '<div class="likeShareButtons" >'+
                        '<input type="button" value="Delete" class="share" onclick="deleteAdminReportPostData('+jsonResponse.ReportPostDetails[i].nFPostID+')" >'+
                        '</div>'+
                        '</div>'+
                        '</div>'+
                        '</div>';

                    postContents.innerHTML += htmlString;
                }else if(jsonResponse.ReportPostDetails[i].imageStatus === "false"/*check thi one*/){
                    let htmlString ='<div class="post">'+
                        '<div class="postContentContainer">'+
                        '<div class="postProfileSection">'+
                        '<a href="#" class="postProfile">'+
                        '<div class="postProfileImage">'+
                        ' <img class="postProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg">'+
                        '</div>'+
                        '<div class="postProfileName" >'+jsonResponse.ReportPostDetails[i].firstName+' '+jsonResponse.ReportPostDetails[i].lastName+'</div>'+
                        '<div class="postTimeAndDate">'+jsonResponse.ReportPostDetails[i].date+'|'+ jsonResponse.ReportPostDetails[i].time+'</div>'+
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

                        '<div class="postContentContainer">'+
                        '<div class="postLikeShareButtons">'+
                        '<a href="#" class="postProfile">'+
                        '<div class="postProfileName" >'+'Count of the report :'+jsonResponse.ReportPostDetails[i].count+'</div>'+
                        '</a>'+
                        '<div class="emptySpaceLikeShare" ></div>'+
                        '<div class="likeShareButtons" >'+
                        '<input type="button" value="Delete" class="share" onclick="deleteAdminReportPostData('+jsonResponse.ReportPostDetails[i].nFPostID+')" >'+
                        '</div>'+
                        '</div>'+
                        '</div>'+
                        '</div>';
                    postContents.innerHTML += htmlString;
                }
            }else{
                if(jsonResponse.ReportPostDetails[i].imageStatus === "true"){
                    let htmlString ='<div class="post">'+
                        '<div class="postContentContainer">'+
                        '<div class="postProfileSection">'+
                        '<a href="#" class="postProfile">'+
                        '<div class="postProfileImage">'+
                        ' <img class="postProfileIcon" src="../Resources/Images/UserProfileImages/profilePicture' + jsonLoginResponse.ReportPostDetails[i].profilePic + '.jpeg">'+
                        '</div>'+
                        '<div class="postProfileName" >'+jsonResponse.ReportPostDetails[i].firstName+' '+jsonResponse.ReportPostDetails[i].lastName+'</div>'+
                        '<div class="postTimeAndDate">'+jsonResponse.ReportPostDetails[i].date+'|'+ jsonResponse.ReportPostDetails[i].time+'</div>'+
                        '</a>'+
                        '</div>'+
                        '</div>'+
                        '<div class="postContentContainer">'+
                        '<div class="postData">'+
                        '<div class="postMessage">'+
                        jsonResponse.ReportPostDetails[i].caption+
                        '</div>'+
                        '<div class="postPicture">'+
                        '<div class="postPictureImageContainer">'+
                        '<img class="postPictureImage" src="../Resources/Images/NewsFeedImages/' + jsonResponse.ReportPostDetails[i].imagePath + '.jpeg">'+
                        '</div>'+
                        '</div>'+
                        '</div>'+
                        '</div>'+

                        '<div class="postContentContainer">'+
                        '<div class="postLikeShareButtons">'+
                        '<a href="#" class="postProfile">'+
                        '<div class="postProfileName" >'+'Count of the report :'+jsonResponse.ReportPostDetails[i].count+'</div>'+
                        '</a>'+
                        '<div class="emptySpaceLikeShare" ></div>'+
                        '<div class="likeShareButtons" >'+
                        '<input type="button" value="Delete" class="share" onclick="deleteAdminReportPostData('+jsonResponse.ReportPostDetails[i].nFPostID+')" >'+
                        '</div>'+
                        '</div>'+
                        '</div>'+
                        '</div>';

                    postContents.innerHTML += htmlString;
                }else if(jsonResponse.ReportPostDetails[i].imageStatus === "false"/*check thi one*/){
                    let htmlString ='<div class="post">'+
                        '<div class="postContentContainer">'+
                        '<div class="postProfileSection">'+
                        '<a href="#" class="postProfile">'+
                        '<div class="postProfileImage">'+
                        ' <img class="postProfileIcon" src="../Resources/Images/UserProfileImages/profilePicture' + jsonLoginResponse.ReportPostDetails[i].profilePic + '.jpeg">'+
                        '</div>'+
                        '<div class="postProfileName" >'+jsonResponse.ReportPostDetails[i].firstName+' '+jsonResponse.ReportPostDetails[i].lastName+'</div>'+
                        '<div class="postTimeAndDate">'+jsonResponse.ReportPostDetails[i].date+'|'+ jsonResponse.ReportPostDetails[i].time+'</div>'+
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

                        '<div class="postContentContainer">'+
                        '<div class="postLikeShareButtons">'+
                        '<a href="#" class="postProfile">'+
                        '<div class="postProfileName" >'+'Count of the report :'+jsonResponse.ReportPostDetails[i].count+'</div>'+
                        '</a>'+
                        '<div class="emptySpaceLikeShare" ></div>'+
                        '<div class="likeShareButtons" >'+
                        '<input type="button" value="Delete" class="share" onclick="deleteAdminReportPostData('+jsonResponse.ReportPostDetails[i].nFPostID+')" >'+
                        '</div>'+
                        '</div>'+
                        '</div>'+
                        '</div>';
                    postContents.innerHTML += htmlString;
                }
            }

        }
    }

}