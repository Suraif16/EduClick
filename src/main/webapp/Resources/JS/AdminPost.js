
const sendServerData = function (){

    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){
            completeLogin( this );
        }

    }

    httpreq.open( "GET" , "/EduClick_war_exploded/AdminPost1" , true);
    function completeLogin( httpreq ) {

            let jsonLoginResponse = JSON.parse(httpreq.responseText);

        if (jsonLoginResponse.serverResponse === "null Session" || jsonLoginResponse.serverResponse === "Not Allowed") {
            window.location.replace("/EduClick_war_exploded/Login.html");
        } else if (jsonLoginResponse.serverResponse === "Allowed") {

            const postContents = document.getElementById("postContents");
            postContents.innerHTML = "";

            let count = jsonLoginResponse.APostIDList.length;
            for( i=0; i< count; i++ ){

                let htmlString ='<div class="post">' +
                    '<div class="postContentContainer">'+
                    '<div class="postProfileSection">'+
                    '<a href="#" class="postProfile">'+
                    '<div class="postProfileImage">'+
                    '<img class="postProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg">'+
                    '</div>'+
                    '<div class="postProfileName" >Admin</div>'+
                    '<div class="postTimeAndDate">'+jsonLoginResponse.AdminPostDetails[i].Date+'|'+ jsonLoginResponse.AdminPostDetails[i].Time+'</div>'+
                    '</a>'+
                    '</div>'+
                    '</div>'+
                    '<div class="postContentContainer">'+
                    '<div class="postData">'+
                    '<div class="postMessage">'+
                    jsonLoginResponse.AdminPostDetails[i].Caption+
                    '</div>'+
                    '</div>'+
                    '</div>'+
                    '</div>'
                    postContents.innerHTML += htmlString;

        }
        } else {
            alert("something went wrong!!!");
        }
    }

    }





