const searchValue = document.getElementById("searchBarText");

searchValue.addEventListener( "keyup" , function (event){

    const searchBarValue = document.getElementById("searchBarText").value;
    console.log(searchBarValue);
    searchForTeacher();

});

function searchForTeacher(){

    const searchBarValue = document.getElementById("searchBarText").value;

    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){
            completeSearch( this ); /*This is where we get the response when the request was successfully sent and a successfully response is received */
        }
    }
    /* ************************ */
    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/teacher" , true);
    httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
    httpreq.send("searchBarText=" + searchBarValue);

    function completeSearch(httpreq){

        console.log(httpreq.responseText);
        let jsonSearchResponse = JSON.parse(httpreq.responseText);

    }



}