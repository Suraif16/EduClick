function shareNewsFeeds ( NewsFeedspostID){

    console.log(NewsFeedspostID);

    let id = NewsFeedspostID;
    console.log(id+" = sharedID ****");

    let httpreq = new XMLHttpRequest();

    httpreq.onreadystatechange = function () {

        if (this.readyState === 4 && this.status === 200) {

            completeShare(this);

        }
    }
    httpreq.open("POST", "/EduClick_war_exploded/teacher/shareNewsFeedsServlet", true);
    httpreq.send();
 //   httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
  //  httpreq.send("id=" + id );

    function completeShare(httpreq){

        let jsonLoginResponse = JSON.parse(httpreq.responseText);

        if (jsonLoginResponse.serverResponse === "null Session" || jsonLoginResponse.serverResponse === "Not Allowed") {
            window.location.replace("/EduClick_war_exploded/Login.html");
        } else if (jsonLoginResponse.serverResponse === "Allowed") {

            let k = jsonLoginResponse.ImagePath.length;
            for (i = 0; i < k; i++) {
                console.log(jsonLoginResponse.ImagePath[i]);
            }

            const postContents = document.getElementById("postContents");
            postContents.innerHTML = "";

                let innerPreviouseHTML = postContents.innerHTML;
                postContents.innerHTML = '        <div class="post">    ' +
                    '              <div class="postContentContainer">' +
                    '                   <div class="postProfileSection">' +
                    '                        <a href="TeacherProfile.html" class="postProfile">' +
                    '                           <div class="postProfileImage">' +
                    '                               <img class="postProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg"> ' +
                    '                           </div>' +
                    '                           <div class="postProfileName" >' + jsonResponse.fullName +
                    '</div>' + ' </a>' +
                    '                           <div class="postTimeAndDate" >' +
                    jsonResponse.Time + ' | ' +
                    jsonResponse.NewsFeedsPost.date +
                    '                            </div>' +
                    '                    <div class="userOptions">' +
                    '<input class="userOptionsButton" type="button" value="    " id="educationalPostOPtion' + jsonResponse.NewsFeedsPost.postID + '" onclick="showOptionMenu(' + jsonResponse.NewsFeedsPost.postID + ',\'educationalPostOPtion\')">' +
                    '                    </div>' +
                    '                   </div>' +
                    '               </div>' +
                    '               <div class="postContentContainer">' +
                    '                   <div class="postData">' +
                    '                       <div class="postMessage">' + jsonResponse.NewsFeedsPost.caption +

                    '                       </div>' +
                    '                       <div class="postPicture">' +
                    '                           <div class="postPictureImageContainer">' +
                    '                              <img class="postPictureImage" src="../Resources/Images/NewsFeedImages/' + jsonResponse.NewsFeedsPost.imagePath + '.jpeg">' +
                    '                           </div>' +
                    '                       </div>' +
                    '                     </div>' +
                    '                  </div>' +
                    '                   <div class="postContentContainer">' +
                    '                       <div class="postLikeShareButtons">' +
                    '                           <div class="likeShareButtons" >' +
                    '                               <input type="button" value="Like" class="like" onclick="likeNewsFeeds(' + jsonResponse.NewsFeedsPost.postID + ')">' +
                    '                           </div>' +
                    '                           <div class="countOfLikeShare" >' +
                    '                               <div class="likeCount">' + jsonResponse.NewsFeedsPost.likeCount +
                    '                                   Likes' +
                    '                               </div >' +
                    '                               <div class="emptySpaceLikeShare">' +
                    '                               </div>' +
                    '                               <div class="shareCount">' + jsonResponse.NewsFeedsPost.likeShare +
                    '                                    Shares' +
                    '                               </div>' +
                    '                              </div>' +
                    '                           <div class="likeShareButtons" >' +
                    '                               <input type="button" value="Share" class="share" onclick="shareNewsFeeds(' + jsonResponse.NewsFeedsPost.postID + ')">' +
                    '                           </div>' +
                    '                        </div>' +
                    '                       </div>' +
                    '                   </div>'


                postContents.innerHTML += innerPreviouseHTML;
                return postContents;
            }



        else
            {
                alert("something went wrong!!!");
            }
        }}

