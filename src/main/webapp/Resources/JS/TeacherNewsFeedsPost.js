const mimeTypeArray = [ "image/apng" , "image/avif" , "image/jpeg" , "image/png" , "image/webp" , "image/bmp" , "image/x-icon" , "image/tiff" , "image/heic" , "image/heif" ];

const showNewsFeedsPostForm = function (){

    const addEducationPostForm = document.getElementById( "addNewsFeedForm" );

    if ( addEducationPostForm.style.display === "flex" ){

        addEducationPostForm.style.display = "none";

    }else{

        addEducationPostForm.style.display = "flex";

    }

}

const postNewsFeeds = function () {

    let message = document.getElementById("addNewsFeedFormTextArea").value;
    let images = document.getElementById("inputImage").files;

    // console.log( message , images );

    if (message === "" && images.length === 0) {

        console.log("empty")


    } else {

        let isAllImageValid = false;

        if (images.length === 0) {

            isAllImageValid = true;

        }

        for (let i = 0; i < images.length; i++) {

            isAllImageValid = isImageAccepted(images[i].type)
            if (!isAllImageValid) {
                console.log("break");
                break;

            }

        }

        if (isAllImageValid) {

            let httpreq = new XMLHttpRequest();
            let formData = new FormData();
            httpreq.onreadystatechange = function () {

                if (this.readyState === 4 && this.status === 200) {

                    complete(this);


                }

            }

            for (let i = 0; i < images.length; i++) {

                let x = "photo" + [i];
                formData.append(x, images[i]);
                console.log(i);

            }

            formData.append("message", message);

            httpreq.open("POST", "/EduClick_war_exploded/teacher/NewsFeedsInsert", true);
            httpreq.send(formData);


        } else {

            console.log("image type invalid");


        }

    }


    const complete = function (httpreq) {

        let jsonResponse = JSON.parse(httpreq.responseText);
        showNewsFeedsPostForm();

        if (jsonResponse.serverResponse === "null Session" || jsonResponse.serverResponse === "Not Allowed") {
            window.location.replace("/EduClick_war_exploded/Login.html");
        } else if (jsonResponse.serverResponse === "Allowed") {

            const postContents = document.getElementById("postContents");
            postContents.innerHTML='';
            let now = new Date().getTime();
            let extraTime = 3000;
            while (new Date().getTime() < now + extraTime) {
            }

         //   console.log(jsonResponse.NewsFeedsPost.imageStatus);
         //   console.log(jsonResponse.NewsFeedsPost.caption  + "qqqq");
            console.log(jsonResponse.jsonArray1[1]);


            if (jsonResponse.NewsFeedsPost.imageStatus === 'true' && jsonResponse.NewsFeedsPost.caption !== '' ) {

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


            }else if(jsonResponse.NewsFeedsPost.imageStatus === 'false' && jsonResponse.NewsFeedsPost.caption !==''){

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

                    '                     </div>' +
                    '                  </div>' +
                    '                   <div class="postContentContainer">' +
                    '                       <div class="postLikeShareButtons">' +
                    '                           <div class="likeShareButtons" >' +
                    '                               <input type="button" value="Like" class="like" onclick="lIKENewsFeeds(' + jsonResponse.NewsFeedsPost.postID + ')">' +
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

            }else if (jsonResponse.NewsFeedsPost.imageStatus === 'true' && jsonResponse.NewsFeedsPost.caption === '')
            {

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


            }
        } else {
            alert("something went wrong!!!");
        }
    }
}

const isImageAccepted = function ( type ){

    const arrayLength = mimeTypeArray.length;

    for ( let i = 0; i < arrayLength; i++ ) {

        if ( mimeTypeArray[i] === type ){

            return true;

        }

    }

    return false;

}