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
        let topValue = elementLocation.top + window.scrollY;
        let leftValue = elementLocation.left + window.scrollX;

        let optionMenuInnerHtmlValue = '<div class="userOptionMenuInput">'+
            '                <input type="button" value="report" onclick="report('+ id + ',\'' + type +'\')">' +
            '            </div>' +
            '            <div class="userOptionMenuInput">' +
            '                <input type="button" value="delete">' +
            '            </div>'+
            '<div class="userOptionMenuInput">' +
            elementId +
            '</div>'
        optionMenu.innerHTML = optionMenuInnerHtmlValue;
        optionMenu.style.margin = topValue +"px" + " auto auto "+ leftValue +"px";

    }


}

const report = function ( id , type ){

    console.log("report" , id , type )

}