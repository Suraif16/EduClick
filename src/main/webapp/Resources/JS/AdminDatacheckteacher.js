function checkdata(){
    searchFunction();
}

const searchFunction = function ( ) {
    let searchType = "Teacher";
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

        for ( i = 0 ; i < jsonLoginResponse.searchResult.length ; i++ ){

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
                'UserId'+
                '</div>'+
                '<div class="postMessage" >'+jsonLoginResponse.searchResult[i].userId+'</div>'+

                '<div class="postMessage">'+
                'FirstName'+
                '</div>'+
                '<div class="postMessage" >'+jsonLoginResponse.searchResult[i].firstName+'</div>'+

                '<div class="postMessage">'+
                'LastName'+
                '</div>'+
                '<div class="postMessage" >'+jsonLoginResponse.searchResult[i].lastName+'</div>'+

                '<div class="postMessage">'+
                'Gender'+
                '</div>'+
                '<div class="postMessage" >'+jsonLoginResponse.searchResult[i].gender+'</div>'+

                '<div class="postMessage">'+
                'DateofBirth'+
                '</div>'+
                '<div class="postMessage" >'+jsonLoginResponse.searchResult[i].dataOfBirth+'</div>'+

                '<div class="postMessage">'+
                'Country'+
                '</div>'+
                '<div class="postMessage" >'+jsonLoginResponse.searchResult[i].country+'</div>'+

                '<div class="postMessage">'+
                'RegistrationDate'+
                '</div>'+
                '<div class="postMessage" >'+jsonLoginResponse.searchResult[i].dataOfReg+'</div>'+

                '<div class="postMessage">'+
                'RegistrationTime'+
                '</div>'+
                '<div class="postMessage" >'+jsonLoginResponse.searchResult[i].timeOfReg+'</div>'+
                '</div>'+
                '</div>'+
                '</div>';
            postContents.innerHTML += post;
        }
    }

}



