const LoadAdminPost = function (){

     let httpreq = new XMLHttpRequest();
     httpreq.onreadystatechange = function (){

          if (this.readyState === 4 && this.status === 200){
               completeLoadAdminPost( this ); /*This is where we get the response when the request was successfully sent and a successfully response is received */
          }

     }

     httpreq.open( "POST" , "/EduClick_war_exploded/user/EntrancePageAdminPostLoadServlet" , true);
     httpreq.send();

     function completeLoadAdminPost( httpreq ){

          let jsonResponse = JSON.parse(httpreq.responseText);

          if( jsonResponse.serverResponse === "null Session" || jsonResponse.serverResponse === "Not Allowed"){
               window.location.replace("/EduClick_war_exploded/Login.html");
               }else if(jsonResponse.serverResponse === "Allowed") {

               for( let i=0; i<jsonResponse.jsonArray1.length;i++) {

                    console.log(jsonResponse.jsonArray1[i]);

                    if (jsonResponse.jsonArray1[i].status=1 && jsonResponse.jsonArray1[i].Caption !=="") {

                         let innerPreviouseHTML = postContents.innerHTML;
                         postContents.innerHTML = '        <div class="post">    ' +
                             '              <div class="postContentContainer">' +
                             '                   <div class="postProfileSection">' + '<div class="name">EduClick</div>' +
                             '                           <div class="postProfileImage">' +
                            /* '                               <img class="postProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg"> ' +*/
                             '                           </div>' +
                             '                           <div class="postProfileName " >' +
                             '</div>' +
                             '                           <div class="postTimeAndDate" >' +
                             jsonResponse.jsonArray1[i].time + ' | ' +
                             jsonResponse.jsonArray1[i].date +
                             '                            </div>' +
                             '                    <div class="userOptions">' +
                             '<input class="userOptionsButton" type="button" value="    " id="educationalPostOPtion' + jsonResponse.jsonArray1[i].aPId + '" onclick="showOptionMenu(' + jsonResponse.jsonArray1[i].aPId + ',\'educationalPostOPtion\')">' +
                             '                    </div>' +
                             '                   </div>' +
                             '               </div>' +
                             '               <div class="postContentContainer">' +
                             '                   <div class="postData">' +
                             '                       <div class="postMessage">' + jsonResponse.jsonArray1[i].caption +

                             '                       </div>' +
                             '                       <div class="postPicture">' +
                             '                           <div class="postPictureImageContainer">' +
                             '                              <img class="postPictureImage" src="../Resources/Images/AdminPostImages/' + jsonResponse.jsonArray1[i].aPId + '.jpeg">' +
                             '                           </div>' +
                             '                       </div>' +
                             '                     </div>' +
                             '                  </div>' +
                             '                   <div class="postContentContainer">' +
                           /*  '                       <div class="postLikeShareButtons">' +
                             '                           <div class="likeShareButtons" >' +
                             '                               <input type="button" value="Like" class="like" onclick="likeNewsFeeds(' + jsonResponse.jsonArray1[i].postId + ')">' +
                             '                           </div>' +
                             '                           <div class="countOfLikeShare" >' +*/
                             /*'                               <div class="likeCount">' + jsonResponse.jsonArray1[i].likeCount +
                             '                                   Likes' +
                             '                               </div >' +*/
                            /* '                               <div class="emptySpaceLikeShare">' +
                             '                               </div>' +
                             '                               <div class="shareCount">' + jsonResponse.jsonArray1[i].shareCount +
                             '                                    Shares' +
                             '                               </div>' +
                             '                              </div>' +*/
                         /*    '                           <div class="likeShareButtons" >' +
                             '                               <input type="button" value="Share" class="share" onclick="shareNewsFeeds(' + jsonResponse.jsonArray1[i].postId + ')">' +
                             '                           </div>' +*/
                             '                        </div>' +
                             '                       </div>' +
                             '                   </div>'


                         postContents.innerHTML += innerPreviouseHTML;


                    }else if(jsonResponse.jsonArray1[i].Caption !==""){

                         let innerPreviouseHTML = postContents.innerHTML;
                         postContents.innerHTML = '        <div class="post">    ' +
                             '              <div class="postContentContainer">' +
                             '                   <div class="postProfileSection">' + '<div id="name">EduClick</div>' +
                             '                           <div class="postProfileImage">' +
                             /* '                               <img class="postProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg"> ' +*/
                             '                           </div>' +
                             '                           <div class="postProfileName " >' +
                             '</div>' +
                             '                           <div class="postTimeAndDate" >' +
                             jsonResponse.jsonArray1[i].time + ' | ' +
                             jsonResponse.jsonArray1[i].date +
                             '                            </div>' +
                             '                    <div class="userOptions">' +
                             '<input class="userOptionsButton" type="button" value="    " id="educationalPostOPtion' + jsonResponse.jsonArray1[i].aPId + '" onclick="showOptionMenu(' + jsonResponse.jsonArray1[i].aPId + ',\'educationalPostOPtion\')">' +
                             '                    </div>' +
                             '                   </div>' +
                             '               </div>' +
                             '               <div class="postContentContainer">' +
                             '                   <div class="postData">' +
                             '                       <div class="postMessage">' + jsonResponse.jsonArray1[i].caption +

                             '                       </div>' +
                             '                       <div class="postPicture">' +
                             /*'                           <div class="postPictureImageContainer">' +
                             '                              <img class="postPictureImage" src="../Resources/Images/NewsFeedImages/' + jsonResponse.jsonArray1[i].aPid + '.jpeg">' +
                             '                           </div>' +*/
                             '                       </div>' +
                             '                     </div>' +
                             '                  </div>' +
                             '                   <div class="postContentContainer">' +
                             /*  '                       <div class="postLikeShareButtons">' +
                               '                           <div class="likeShareButtons" >' +
                               '                               <input type="button" value="Like" class="like" onclick="likeNewsFeeds(' + jsonResponse.jsonArray1[i].postId + ')">' +
                               '                           </div>' +
                               '                           <div class="countOfLikeShare" >' +*/
                             /*'                               <div class="likeCount">' + jsonResponse.jsonArray1[i].likeCount +
                             '                                   Likes' +
                             '                               </div >' +*/
                             /* '                               <div class="emptySpaceLikeShare">' +
                              '                               </div>' +
                              '                               <div class="shareCount">' + jsonResponse.jsonArray1[i].shareCount +
                              '                                    Shares' +
                              '                               </div>' +
                              '                              </div>' +*/
                             /*    '                           <div class="likeShareButtons" >' +
                                 '                               <input type="button" value="Share" class="share" onclick="shareNewsFeeds(' + jsonResponse.jsonArray1[i].postId + ')">' +
                                 '                           </div>' +*/
                             '                        </div>' +
                             '                       </div>' +
                             '                   </div>'


                         postContents.innerHTML += innerPreviouseHTML;


                    }else if (jsonResponse.jsonArray1[i].status=1)
                    {

                         let innerPreviouseHTML = postContents.innerHTML;
                         postContents.innerHTML = '        <div class="post">    ' +
                             '              <div class="postContentContainer">' +
                             '                   <div class="postProfileSection">' + '<div id="name">EduClick</div>' +
                             '                           <div class="postProfileImage">' +
                             /* '                               <img class="postProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg"> ' +*/
                             '                           </div>' +
                             '                           <div class="postProfileName " >' +
                             '</div>' +
                             '                           <div class="postTimeAndDate" >' +
                             jsonResponse.jsonArray1[i].time + ' | ' +
                             jsonResponse.jsonArray1[i].date +
                             '                            </div>' +
                             '                    <div class="userOptions">' +
                             '<input class="userOptionsButton" type="button" value="    " id="educationalPostOPtion' + jsonResponse.jsonArray1[i].aPId + '" onclick="showOptionMenu(' + jsonResponse.jsonArray1[i].aPId + ',\'educationalPostOPtion\')">' +
                             '                    </div>' +
                             '                   </div>' +
                             '               </div>' +
                             '               <div class="postContentContainer">' +
                             '                   <div class="postData">' +
                           /*  '                       <div class="postMessage">' + jsonResponse.jsonArray1[i].caption +*/

                             '                       </div>' +
                             '                       <div class="postPicture">' +
                             '                           <div class="postPictureImageContainer">' +
                             '                              <img class="postPictureImage" src="../Resources/Images/NewsFeedImages/' + jsonResponse.jsonArray1[i].aPId + '.jpeg">' +
                             '                           </div>' +
                             '                       </div>' +
                             '                     </div>' +
                             '                  </div>' +
                             '                   <div class="postContentContainer">' +
                             /*  '                       <div class="postLikeShareButtons">' +
                               '                           <div class="likeShareButtons" >' +
                               '                               <input type="button" value="Like" class="like" onclick="likeNewsFeeds(' + jsonResponse.jsonArray1[i].postId + ')">' +
                               '                           </div>' +
                               '                           <div class="countOfLikeShare" >' +*/
                             /*'                               <div class="likeCount">' + jsonResponse.jsonArray1[i].likeCount +
                             '                                   Likes' +
                             '                               </div >' +*/
                             /* '                               <div class="emptySpaceLikeShare">' +
                              '                               </div>' +
                              '                               <div class="shareCount">' + jsonResponse.jsonArray1[i].shareCount +
                              '                                    Shares' +
                              '                               </div>' +
                              '                              </div>' +*/
                             /*    '                           <div class="likeShareButtons" >' +
                                 '                               <input type="button" value="Share" class="share" onclick="shareNewsFeeds(' + jsonResponse.jsonArray1[i].postId + ')">' +
                                 '                           </div>' +*/
                             '                        </div>' +
                             '                       </div>' +
                             '                   </div>'


                         postContents.innerHTML += innerPreviouseHTML;





                    }

               }



               }else{
                    alert("something went wrong!!!");
               }

          }





}