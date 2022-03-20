function postAutoprint (){
    getServerData();
    console.log( "send signal" );
}


const getServerData = function (){
    console.log( "send signal" );

    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){
            complete( this );
        }

    }

    httpreq.open( "GET" , "/EduClick_war_exploded/AdminPost1" , true);
    httpreq.send();

    function complete( httpreq ) {

            let jsonResponse = JSON.parse(httpreq.responseText);
            const postContents = document.getElementById("request");
            postContents.innerHTML = "";

            let count = jsonResponse.AdminPostDetails.length;
            for( i=0; i< count; i++ ){

                let htmlString ='<div class="post">' +
                    '<div class="postContentContainer">'+
                    '<div class="postProfileSection">'+
                    '<a href="#" class="postProfile">'+
                    '<div class="postProfileImage">'+
                    '<img class="postProfileIcon" src="../Resources/Icons/account_circle_white_24dp.svg">'+
                    '</div>'+
                    '<div class="postProfileName" >Admin</div>'+
                    '<div class="postTimeAndDate">'+jsonResponse.AdminPostDetails[i].date+'|'+ jsonResponse.AdminPostDetails[i].time+'</div>'+
                    '</a>'+
                    '</div>'+
                    '</div>'+
                    '<div class="postContentContainer">'+
                    '<div class="postData">'+
                    '<div class="postMessage">'+
                    jsonResponse.AdminPostDetails[i].caption+
                    '</div>'+
                    '</div>'+
                    '</div>'+
                    '</div>';
                    postContents.innerHTML += htmlString;

                    }

    }

}



