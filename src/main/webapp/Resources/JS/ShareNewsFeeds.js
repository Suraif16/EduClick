function shareNewsFeeds (NewsFeedspostID){

    console.log(NewsFeedspostID);

    let id = NewsFeedspostID;
    console.log(id+" = sharedID");

    let httpreq = new XMLHttpRequest();

    httpreq.onreadystatechange = function () {

        if (this.readyState === 4 && this.status === 200) {

            completeShare(this);

        }
    }

    httpreq.open("POST", "/EduClick_war_exploded/teacher/shareNewsFeedsServlet", true);
    httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
    httpreq.send("id=" + id );

    function completeShare(httpreq){

        let jsonLoginResponse = JSON.parse(httpreq.responseText);

        if (jsonLoginResponse.serverResponse === "null Session" || jsonLoginResponse.serverResponse === "Not Allowed") {
            window.location.replace("/EduClick_war_exploded/Login.html");
        } else if (jsonLoginResponse.serverResponse === "Allowed") {

            console.log("yep yep ");
            }

        else
            {
                alert("something went wrong!!!");
            }
        }}

