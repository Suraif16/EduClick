/*document.onreadystatechange = function (){

    if ( document.readyState === 'complete' ){

        LoadNewsFeedsInTeacherProfilePage();


    }

}*/


const LoadNewsFeedsInTeacherProfilePage = function (){

    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){
            completeNFLoad( this ); /*This is where we get the response when the request was successfully sent and a successfully response is received */
        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/TeacherProfilePageNewsFeedsLoadServlet" , true);
    httpreq.setRequestHeader("Content-type", "application/x-www-form-urlencoded" );
    httpreq.send("userId=" + getUserIdClientSide());
}

function completeNFLoad(httpreq) {

    let jsonResponse = JSON.parse(httpreq.responseText);

    if (jsonResponse.serverResponse === "null Session" || jsonResponse.serverResponse === "Not Allowed") {
        window.location.replace("/EduClick_war_exploded/Login.html");
    } else if (jsonResponse.serverResponse === "Allowed") {

        const name = document.getElementById("profileUserName");
        name.innerHTML = jsonResponse.fullName;
       /* let url = '/EduClick_war_exploded/userProfileRedirect?userId=' + jsonLoginResponse.userId;
        headerUserProfileIdAchorElement.setAttribute("href" , url);*/

        for( let i=0; i<jsonResponse.jsonArray1.length;i++) {

            console.log(jsonResponse.jsonArray1[i]);

            if (jsonResponse.jsonArray1[i].path !== "" && jsonResponse.jsonArray1[i].Caption !=="") {

                let innerPreviouseHTML = postContents.innerHTML;
                postContents.innerHTML = '        <div class="post">    ' +
                    '              <div class="postContentContainer">' +
                    '                   <div class="postProfileSection">' +
                    '                        <a href="/EduClick_war_exploded/userProfileRedirect?userId=' + jsonResponse.jsonArray1[i].ownerId + '" class="postProfile">' +
                    '                           <div class="postProfileImage">' +
                    '                               <img class="postProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg"> ' +
                    '                           </div>' +
                    '                           <div class="postProfileName" >' + jsonResponse.jsonArray1[i].ownerName +
                    '</div>' + ' </a>' +
                    '                           <div class="postTimeAndDate" >' +
                    jsonResponse.jsonArray1[i].Time + ' | ' +
                    jsonResponse.jsonArray1[i].Date +
                    '                            </div>' +
                    '                    <div class="userOptions">' +
                    '<input class="userOptionsButton" type="button" value="    " id="educationalPostOPtion' + jsonResponse.jsonArray1[i].postId + '" onclick="showOptionMenu(' + jsonResponse.jsonArray1[i].postId + ',\'educationalPostOPtion\')">' +
                    '                    </div>' +
                    '                   </div>' +
                    '               </div>' +
                    '               <div class="postContentContainer">' +
                    '                   <div class="postData">' +
                    '                       <div class="postMessage">' + jsonResponse.jsonArray1[i].Caption +

                    '                       </div>' +
                    '                       <div class="postPicture">' +
                    '                           <div class="postPictureImageContainer">' +
                    '                              <img class="postPictureImage" src="../Resources/Images/NewsFeedImages/' + jsonResponse.jsonArray1[i].path + '.jpeg">' +
                    '                           </div>' +
                    '                       </div>' +
                    '                     </div>' +
                    '                  </div>' +
                    '                   <div class="postContentContainer">' +
                    '                       <div class="postLikeShareButtons">' +
                    '                           <div class="likeShareButtons" >' +
                    '                               <input type="button" value="Like" class="like" onclick="likeNewsFeeds(' + jsonResponse.jsonArray1[i].postId + ')">' +
                    '                           </div>' +
                    '                           <div class="countOfLikeShare" >' +
                    '                               <div class="likeCount">' + jsonResponse.jsonArray1[i].likeCount +
                    '                                   Likes' +
                    '                               </div >' +
                    '                               <div class="emptySpaceLikeShare">' +
                    '                               </div>' +
                    '                               <div class="shareCount">' + jsonResponse.jsonArray1[i].shareCount +
                    '                                    Shares' +
                    '                               </div>' +
                    '                              </div>' +
                    '                           <div class="likeShareButtons" >' +
                    '                               <input type="button" value="Share" class="share" onclick="shareNewsFeeds(' + jsonResponse.jsonArray1[i].postId + ')">' +
                    '                           </div>' +
                    '                        </div>' +
                    '                       </div>' +
                    '                   </div>'


                postContents.innerHTML += innerPreviouseHTML;


            }else if(jsonResponse.jsonArray1[i].Caption !==""){

                let innerPreviouseHTML = postContents.innerHTML;
                postContents.innerHTML = '        <div class="post">    ' +
                    '              <div class="postContentContainer">' +
                    '                   <div class="postProfileSection">' +
                    '                        <a href="/EduClick_war_exploded/userProfileRedirect?userId=' + jsonResponse.jsonArray1[i].ownerId + '" class="postProfile">' +
                    '                           <div class="postProfileImage">' +
                    '                               <img class="postProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg"> ' +
                    '                           </div>' +
                    '                           <div class="postProfileName" >' + jsonResponse.jsonArray1[i].ownerName +
                    '</div>' + ' </a>' +
                    '                           <div class="postTimeAndDate" >' +
                    jsonResponse.jsonArray1[i].Time + ' | ' +
                    jsonResponse.jsonArray1[i].Date +
                    '                            </div>' +
                    '                    <div class="userOptions">' +
                    '<input class="userOptionsButton" type="button" value="    " id="educationalPostOPtion' + jsonResponse.jsonArray1[i].postId + '" onclick="showOptionMenu(' + jsonResponse.jsonArray1[i].postId + ',\'educationalPostOPtion\')">' +
                    '                    </div>' +
                    '                   </div>' +
                    '               </div>' +
                    '               <div class="postContentContainer">' +
                    '                   <div class="postData">' +
                    '                       <div class="postMessage">' + jsonResponse.jsonArray1[i].Caption +

                    '                       </div>' +

                    '                     </div>' +
                    '                  </div>' +
                    '                   <div class="postContentContainer">' +
                    '                       <div class="postLikeShareButtons">' +
                    '                           <div class="likeShareButtons" >' +
                    '                               <input type="button" value="Like" class="like" onclick="likeNewsFeeds(' + jsonResponse.jsonArray1[i].postId + ')">' +
                    '                           </div>' +
                    '                           <div class="countOfLikeShare" >' +
                    '                               <div class="likeCount">' + jsonResponse.jsonArray1[i].likeCount +
                    '                                   Likes' +
                    '                               </div >' +
                    '                               <div class="emptySpaceLikeShare">' +
                    '                               </div>' +
                    '                               <div class="shareCount">' + jsonResponse.jsonArray1[i].shareCount +
                    '                                    Shares' +
                    '                               </div>' +
                    '                              </div>' +
                    '                           <div class="likeShareButtons" >' +
                    '                               <input type="button" value="Share" class="share" onclick="shareNewsFeeds(' + jsonResponse.jsonArray1[i].postId + ')">' +
                    '                           </div>' +
                    '                        </div>' +
                    '                       </div>' +
                    '                   </div>'


                postContents.innerHTML += innerPreviouseHTML;


            }else if (jsonResponse.jsonArray1[i].path !== "")
            {

                let innerPreviouseHTML = postContents.innerHTML;
                postContents.innerHTML = '        <div class="post">    ' +
                    '              <div class="postContentContainer">' +
                    '                   <div class="postProfileSection">' +
                    '                        <a href="/EduClick_war_exploded/userProfileRedirect?userId=' + jsonResponse.jsonArray1[i].ownerId + '" class="postProfile">' +
                    '                           <div class="postProfileImage">' +
                    '                               <img class="postProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg"> ' +
                    '                           </div>' +
                    '                           <div class="postProfileName" >' + jsonResponse.jsonArray1[i].ownerName +
                    '</div>' + ' </a>' +
                    '                           <div class="postTimeAndDate" >' +
                    jsonResponse.jsonArray1[i].Time + ' | ' +
                    jsonResponse.jsonArray1[i].Date +
                    '                            </div>' +
                    '                    <div class="userOptions">' +
                    '<input class="userOptionsButton" type="button" value="    " id="educationalPostOPtion' + jsonResponse.jsonArray1[i].postId + '" onclick="showOptionMenu(' + jsonResponse.jsonArray1[i].postId + ',\'educationalPostOPtion\')">' +
                    '                    </div>' +
                    '                   </div>' +
                    '               </div>' +
                    '               <div class="postContentContainer">' +
                    '                   <div class="postData">' +

                    '                       <div class="postPicture">' +
                    '                           <div class="postPictureImageContainer">' +
                    '                              <img class="postPictureImage" src="../Resources/Images/NewsFeedImages/' + jsonResponse.jsonArray1[i].path + '.jpeg">' +
                    '                           </div>' +
                    '                       </div>' +
                    '                     </div>' +
                    '                  </div>' +
                    '                   <div class="postContentContainer">' +
                    '                       <div class="postLikeShareButtons">' +
                    '                           <div class="likeShareButtons" >' +
                    '                               <input type="button" value="Like" class="like" onclick="likeNewsFeeds(' + jsonResponse.jsonArray1[i].postId + ')">' +
                    '                           </div>' +
                    '                           <div class="countOfLikeShare" >' +
                    '                               <div class="likeCount">' + jsonResponse.jsonArray1[i].likeCount +
                    '                                   Likes' +
                    '                               </div >' +
                    '                               <div class="emptySpaceLikeShare">' +
                    '                               </div>' +
                    '                               <div class="shareCount">' + jsonResponse.jsonArray1[i].shareCount +
                    '                                    Shares' +
                    '                               </div>' +
                    '                              </div>' +
                    '                           <div class="likeShareButtons" >' +
                    '                               <input type="button" value="Share" class="share" onclick="shareNewsFeeds(' + jsonResponse.jsonArray1[i].postId + ')">' +
                    '                           </div>' +
                    '                        </div>' +
                    '                       </div>' +
                    '                   </div>'


                postContents.innerHTML += innerPreviouseHTML;





            }


        }


    }

    else
    {
        alert("something went wrong!!!");
    }
}
/*
const getUserIdClientSide = function (){

    let currentClassUrl = new URL( window.location.href );
    console.log(currentClassUrl+"***********");
    return currentClassUrl.searchParams.get( "userId" );

}*/
