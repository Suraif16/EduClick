function shareNewsFeeds ( NewsFeedspostID){

    console.log(NewsFeedspostID);

    let id = NewsFeedspostID;
    console.log(id+" = sharedID");

    let httpreq = new XMLHttpRequest();

    httpreq.onreadystatechange = function () {

        if (this.readyState === 4 && this.status === 200) {

            completeShare(this);

        }
    }
    httpreq.open("POST", "/EduClick_war_exploded/teacher/shareNewsFeedsServlet", true);
    httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
    httpreq.send("id=" + id );

    function completeShare(httpreq){

        let jsonResponse = JSON.parse( httpreq.responseText );

        if( jsonResponse.serverResponse === "null Session" || jsonResponse.serverResponse === "Not Allowed"){
            window.location.replace("/EduClick_war_exploded/Login.html");
        }else if(jsonResponse.serverResponse === "Allowed") {

            console.log(jsonResponse.NewsFeedsDetail);
            console.log(jsonResponse.NewsFeedsDetail.Time);
            console.log(jsonResponse.NewsFeedsDetail.Caption);
            console.log(jsonResponse.NewsFeedsImagePath);
            console.log(jsonResponse.fullName + "******");


            /*const ppostContents = document.getElementById("profilePostContents");
            let now = new Date().getTime();
            let extraTime = 1000;
            while(new Date().getTime() < now + extraTime ){}
            let innerPreviouseHTML = ppostContents.innerHTML;
            ppostContents.innerHTML = '        <div class="post">    ' +
                '              <div class="postContentContainer">' +
                '                   <div class="postProfileSection">' +
                '                        <a href="TeacherProfile.html" class="postProfile">' +
                '                           <div class="postProfileImage">' +
                '                               <img class="postProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg"> ' +
                '                           </div>' +
                '                           <div class="postProfileName" >' + jsonResponse.fullName +
                '</div>' + ' </a>' +
                '                           <div class="postTimeAndDate" >' +
                jsonResponse.NewsFeedsDetail.Time + ' | ' +
                jsonResponse.NewsFeedsDetail.date +
                '                            </div>' +
                '                    <div class="userOptions">' +
                '<input class="userOptionsButton" type="button" value="    " id="educationalPostOPtion' + id + '" onclick="showOptionMenu(' + id + ',\'educationalPostOPtion\')">'+
                '                    </div>' +
                '                   </div>' +
                '               </div>' +
                '               <div class="postContentContainer">' +
                '                   <div class="postData">' +
                '                       <div class="postMessage">' + jsonResponse.NewsFeedsDetail.caption +

                '                       </div>' +
                '                       <div class="postPicture">' +
                '                           <div class="postPictureImageContainer">' +
                '                              <img class="postPictureImage" src="../Resources/Images/NewsFeedImages/' + jsonResponse.NewsFeedsImagePath + '.jpeg">'+
                '                           </div>' +
                '                       </div>' +
                '                     </div>' +
                '                  </div>' +
                '                   <div class="postContentContainer">' +
                '                       <div class="postLikeShareButtons">' +
                '                           <div class="likeShareButtons" >' +
                '                               <input type="button" value="Like" class="like">' +
                '                           </div>' +
                '                           <div class="countOfLikeShare" >' +
                '                               <div class="likeCount">' + jsonResponse.NewsFeedsDetail.likeCount +
                '                                   Likes' +
                '                               </div >' +
                '                               <div class="emptySpaceLikeShare">' +
                '                               </div>' +
                '                               <div class="shareCount">' + jsonResponse.NewsFeedsDetail.likeShare +
                '                                    Shares' +
                '                               </div>' +
                '                              </div>' +
                '                           <div class="likeShareButtons" >' +
                '                               <input type="button" value="Share" class="share" onclick="shareNewsFeeds(' + jsonResponse.NewsFeedsDetail.postID + ')">' +
                '                           </div>' +
                '                        </div>' +
                '                       </div>' +
                '                   </div>'

            ppostContents.innerHTML += innerPreviouseHTML;
*/
        }else{
            alert("something went wrong!!!");
        }
    }

}