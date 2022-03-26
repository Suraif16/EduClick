function likeNewsFeeds(NewsFeedspostID){

    console.log(NewsFeedspostID);

    let id = NewsFeedspostID;
    console.log(id+" = lokedPostID");

    let httpreq = new XMLHttpRequest();

    httpreq.onreadystatechange = function () {

        if (this.readyState === 4 && this.status === 200) {

            completeLike(this);

        }
    }
    httpreq.open("POST", "/EduClick_war_exploded/user/likeNewsFeedsServlet", true);
    httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
    httpreq.send("id=" + id );

    function completeLike(httpreq) {

        let jsonResponse = JSON.parse(httpreq.responseText);

        if (jsonResponse.serverResponse === "null Session" || jsonResponse.serverResponse === "Not Allowed") {
            window.location.replace("/EduClick_war_exploded/Login.html");
        } else if (jsonResponse.serverResponse === "Allowed") {

            console.log("success");

        } else {
            alert("something went wrong!!!");
        }


    }}