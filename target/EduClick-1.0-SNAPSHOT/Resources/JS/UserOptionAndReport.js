let optionMenuStatus = "";


const showOptionMenu = function ( id , type ){

    const optionMenu = document.getElementById("userOptionMenu");
    const elementId = type + id;
    const element = document.getElementById( elementId );
    const elementLocation = element.getBoundingClientRect();

    if ( optionMenuStatus === elementId ){

        optionMenuStatus = "";
        optionMenu.innerHTML = "";
        optionMenu.style.display = "none"

    }else{

        optionMenuStatus = elementId;
        optionMenu.style.display = "flex"
        optionMenu.style.position = "absolute";
        let topValue = elementLocation.top + window.scrollY + 5;
        let leftValue = elementLocation.left + window.scrollX + 10;

        let optionMenuInnerHtmlValue = '<div class="userOptionMenuInput">'+
            '                <input type="button" value="report" onclick="report('+ id + ',\'' + type +'\')">' +
            '            </div>' +
            '            <div class="userOptionMenuInput">' +
            '                <input type="button" value="delete">' +
            '            </div>';

        optionMenu.innerHTML = optionMenuInnerHtmlValue;
        optionMenu.style.margin = topValue +"px" + " auto auto "+ leftValue +"px";

    }


}

const report = function ( id , type ){

    console.log("report" , id , type )

    let httpreq = new XMLHttpRequest();
    httpreq.onreadystatechange = function (){
        if (this.readyState === 4 && this.status === 200){
            //completeLogin( this ) /*This is where we get the response when the request was successfully sent and a successfully response is received */
        }
    }

    httpreq.open( "POST" , "/EduClick_war_exploded/teacher/report" , true);
    httpreq.setRequestHeader("Content-type" , "application/x-www-form-urlencoded");
    httpreq.send("id=" + id +"&type=" + type);

}