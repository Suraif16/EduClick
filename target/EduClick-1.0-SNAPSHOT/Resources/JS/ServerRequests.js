
const serverRequests = function ( method , to , data = ""){

    let result;

    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){

        if (this.readyState === 4 && this.status === 200){
            /*This is where we get the response when the request was successfully sent and a successfully response is received */

            result = JSON.parse(httpreq.responseText); /*here when we receive the response
            from the server, we convert it to JSON as it will be sent as JSON from the servlet.
            Once we parse the response to JSON we use jsonLoginResponse.User to get the value of User member
            in the JSON object specified by the servlet*/

        }

    }
    /* "/EduClick_war_exploded/Login" -> /Login replaced by 'to'
    *
    *     httpreq.send("Email=" + email + "&Password=" + password ); -> data inside brackets
    *
    * */

    /*if method is get? send data in the format
    * ?Email=email&Password=password
    * */

    if( method === "GET"){
        to += data;
    }

    httpreq.open( method , "/EduClick_war_exploded" + to , true);

    if( method === "POST" ){

        httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
        httpreq.send( data );

    }
    else{

        httpreq.send();

    }

    return result;
}