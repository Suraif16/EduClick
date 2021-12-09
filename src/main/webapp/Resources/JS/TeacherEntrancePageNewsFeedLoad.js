
const sendServerDataTeacher = function (){

    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){
            completeLog( this );

        }

    }

    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/teacherEntrancePageNewsFeedsLoad" , true);
    httpreq.send();

    function completeLog( httpreq ) {

            let jsonLoginResponse = JSON.parse(httpreq.responseText);

        if (jsonLoginResponse.serverResponse === "null Session" || jsonLoginResponse.serverResponse === "Not Allowed") {
            window.location.replace("/EduClick_war_exploded/Login.html");
        } else if (jsonLoginResponse.serverResponse === "Allowed") {

            let k = jsonLoginResponse.ImagePath.length;
            for(i=0 ; i <k; i++ ){
                console.log(jsonLoginResponse.ImagePath[i]);
            }

            const postContents = document.getElementById("postContents");
            postContents.innerHTML = "";

            let count = jsonLoginResponse.NewsFeedID.length;
            for( i=0; i< count; i++ ){

                let htmlString =
                '        <div class="post">    ' +
                '              <div class="postContentContainer">' +
                '                   <div class="postProfileSection">' +
                '                        <a href="TeacherProfile.html" class="postProfile">' +
                '                           <div class="postProfileImage">' +
                '                               <img class="postProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg"> ' +
                '                           </div>' +
                '                           <div class="postProfileName" >' + jsonLoginResponse.TeacherName[i].firstName + " " +
                jsonLoginResponse.TeacherName[i].lastName +
                '</div>' +
                '                           <div class="postTimeAndDate" >' +
                jsonLoginResponse.NewsFeedsDetails[i].Time + '|' +
                jsonLoginResponse.NewsFeedsDetails[i].Date +
                '                            </div>' +
                '                        </a>' +
                '                   </div>' +
                '               </div>' +
                '               <div class="postContentContainer">' +
                '                   <div class="postData">' +
                '                       <div class="postMessage">' + jsonLoginResponse.NewsFeedsDetails[i].Caption +

                '                       </div>' +
                '                       <div class="postPicture">' +
                '                           <!--<div class="postPictureImageContainer">-->' +
                '                               <!--<img class="postPictureImage" src="../Resources/Images/seminar-text.jpg">-->' +
                '                          <!-- </div>-->' +
                '                       </div>' +
                '                     </div>' +
                '                  </div>' +
                '                   <div class="postContentContainer">' +
                '                       <div class="postLikeShareButtons">' +
                '                           <div class="likeShareButtons" >' +
                '                               <input type="button" value="Like" class="like">' +
                '                           </div>' +
                '                           <div class="countOfLikeShare" >' +
                '                               <div class="likeCount">' +
                '                                   2793 Likes' +
                '                               </div >' +
                '                               <div class="emptySpaceLikeShare">' +
                '                               </div>' +
                '                               <div class="shareCount">' +
                '                                   18198 Shares' +
                '                               </div>' +
                '                              </div>' +
                '                           <div class="likeShareButtons" >' +
                '                               <input type="button" value="Share" class="share">' +
                '                           </div>' +
                '                        </div>' +
                '                       </div>' +
                '                   </div>'


            postContents.innerHTML += htmlString;



        }
        } else {
            alert("something went wrong!!!");
        }
    }

    }





