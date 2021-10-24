document.onreadystatechange = function (){

    if( document.readyState === 'complete' ){

        searchPageLoad();

    }

}

const userName = document.getElementById( "userName" )
const headerNotification = document.getElementById( "headerNotification" )
const logout = document.getElementById( "logout" );
const logoAnchorLink = document.getElementById( "linkLogo" );
const searchPost = document.getElementById( "searchPost" );
const searchComment = document.getElementById( "searchComment" );


const searchPageLoad = function (){

    let httpreq = new XMLHttpRequest();

    httpreq.onreadystatechange = function (){

        if ( httpreq.readyState === 4 && httpreq.status === 200){

            searchPageData( this );

        }

    }

    httpreq.open("GET", "/EduClick_war_exploded/searchPageOnLoad" ,true);
    httpreq.send();

    const searchPageData = function ( httpreq ){

        let jsonResponse = JSON.parse( httpreq.responseText );
        userName.innerHTML = jsonResponse.UserName;
        logoAnchorLink.setAttribute( "href" , jsonResponse.Url );

        if ( jsonResponse.UserName === 'Guest' ){

            headerNotification.style.display = "none";
            logout.style.display = "none";
            searchPost.style.display = "none";
            searchComment.style.display = "none";

        }

    }
}



/*searchValue.addEventListener( "keyup" , function (event){


    if(event.key === "Enter"){
        searchForTeacher(searchValue.value);
    }

});



function searchForTeacher(searchValue) {

    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){

            completeSearch( this );
        }
    }
    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/searchTeacher" , true);
    httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
    httpreq.send("value=" + value);
    console.log("ggghh" + value);


}


const completeSearch = function( httpreq ){

    let jsonResponse = JSON.parse( httpreq.responseText);

    if( jsonResponse.serverResponse === "null Session" || jsonResponse.serverResponse === "Not Allowed"){
        window.location.replace("/EduClick_war_exploded/Login.html");
    }else if(jsonResponse.serverResponse === "Allowed") {
        /!* This is where I need work everytime as per the authentication filter*!/


        console.log("jsonResponse");

    }else{
        alert("something went wrong!!!");
    }

    

}*/
