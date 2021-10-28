const loadFollowersList = function (){
    let httpreq = new XMLHttpRequest();

    httpreq.onreadystatechange = function () {

        if (this.readyState === 4 && this.status === 200) {
            console.log("I am running")
            completeLoad(this); /*This is where we get the response when the request was successfully sent and a successfully response is received */

        }
    }

    httpreq.open("POST", "/EduClick_war_exploded/studentFollowerListLoad", true);
    httpreq.send();

    function completeLoad(httpreq){
        let jsonResponse = JSON.parse(httpreq.responseText);
        console.log(jsonResponse.check)
    }


}
