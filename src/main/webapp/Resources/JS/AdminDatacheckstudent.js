function checkdata(){
    searchFunction();
}

document.onreadystatechange = function (){
    if ( document.readyState === 'complete' ){
        /* when the document is loaded and complete this function will run*/
        searchFunction();
    }
}

const searchFunction = function ( ) {
    let searchType = "Student";
    const userName = document.getElementById( "comment" ).value
    console.log(userName);
    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){
        if ( httpreq.readyState === 4 && httpreq.status === 200){
            displayTeacher( this );
        }
    }

    httpreq.open("POST", "/EduClick_war_exploded/AdminDatacheckServlet" ,true);
    httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
    httpreq.send("userName=" + userName + "&searchType=" + searchType );


    const displayTeacher = function ( httpreq ){
        let jsonLoginResponse = JSON.parse( httpreq.responseText );
        const postContents = document.getElementById( "postContents" );
        postContents.innerHTML = "";
        if(jsonLoginResponse.searchResult.length>0){
        for ( i = 0 ; i < jsonLoginResponse.searchResult.length ; i++ ){
            if(jsonLoginResponse.searchResult[i].profilePic === undefined){
                let post = '<div class="post">'+
                    '<div class="postContentContainer">'+
                    '<div class="postProfileSection">'+
                    '<a href="#" class="postProfile">'+
                    '<div class="postProfileImage">'+
                    '<img class="postProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg">'+
                    '</div>'+
                    '<div class="postProfileName">'+jsonLoginResponse.searchResult[i].firstName+" "+jsonLoginResponse.searchResult[i].lastName+
                    '</div>'+
                    '</a>'+
                    '</div>'+
                    '</div>'+

                    '<div class="postContentContainer">'+
                    '<div class="postData">'+
                    '<div class="postMessage">'+
                    'UserId : '+jsonLoginResponse.searchResult[i].userId+
                    '</div>'+

                    '<div class="postMessage">'+
                    'FirstName : '+jsonLoginResponse.searchResult[i].firstName+
                    '</div>'+

                    '<div class="postMessage">'+
                    'LastName : '+jsonLoginResponse.searchResult[i].lastName+
                    '</div>'+

                    '<div class="postMessage">'+
                    'Gender : '+jsonLoginResponse.searchResult[i].gender+
                    '</div>'+

                    '<div class="postMessage">'+
                    'DateofBirth : '+jsonLoginResponse.searchResult[i].dataOfBirth+
                    '</div>'+

                    '<div class="postMessage">'+
                    'City : '+jsonLoginResponse.searchResult[i].city+
                    '</div>'+

                    '<div class="postMessage">'+
                    'Country : '+jsonLoginResponse.searchResult[i].country+
                    '</div>'+


                    '<div class="postMessage">'+
                    'RegistrationDate : '+jsonLoginResponse.searchResult[i].dataOfReg+
                    '</div>'+

                    '<div class="postMessage">'+
                    'RegistrationTime : '+jsonLoginResponse.searchResult[i].timeOfReg+
                    '</div>'+

                    '</div>'+
                    '</div>'+
                    '</div>';
                postContents.innerHTML += post;
            }else{
                let post = '<div class="post">'+
                    '<div class="postContentContainer">'+
                    '<div class="postProfileSection">'+
                    '<a href="#" class="postProfile">'+
                    '<div class="postProfileImage">'+
                    ' <img class="postProfileIcon" src="../Resources/Images/UserProfileImages/profilePicture' + jsonLoginResponse.searchResult[i].profilePic + '.jpeg">'+
                    '</div>'+
                    '<div class="postProfileName">'+jsonLoginResponse.searchResult[i].firstName+" "+jsonLoginResponse.searchResult[i].lastName+
                    '</div>'+
                    '</a>'+
                    '</div>'+
                    '</div>'+

                    '<div class="postContentContainer">'+
                    '<div class="postData">'+
                    '<div class="postMessage">'+
                    'UserId : '+jsonLoginResponse.searchResult[i].userId+
                    '</div>'+

                    '<div class="postMessage">'+
                    'FirstName : '+jsonLoginResponse.searchResult[i].firstName+
                    '</div>'+

                    '<div class="postMessage">'+
                    'LastName : '+jsonLoginResponse.searchResult[i].lastName+
                    '</div>'+

                    '<div class="postMessage">'+
                    'Gender : '+jsonLoginResponse.searchResult[i].gender+
                    '</div>'+

                    '<div class="postMessage">'+
                    'DateofBirth : '+jsonLoginResponse.searchResult[i].dataOfBirth+
                    '</div>'+

                    '<div class="postMessage">'+
                    'City : '+jsonLoginResponse.searchResult[i].city+
                    '</div>'+

                    '<div class="postMessage">'+
                    'Country : '+jsonLoginResponse.searchResult[i].country+
                    '</div>'+


                    '<div class="postMessage">'+
                    'RegistrationDate : '+jsonLoginResponse.searchResult[i].dataOfReg+
                    '</div>'+

                    '<div class="postMessage">'+
                    'RegistrationTime : '+jsonLoginResponse.searchResult[i].timeOfReg+
                    '</div>'+

                    '</div>'+
                    '</div>'+
                    '</div>';
                postContents.innerHTML += post;
            }

            }
        }else{
            postContents.innerHTML = '<div class="postMessage">'+
                'No Details for Searching value'+
                '</div>';
        }
    }

}



