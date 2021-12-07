let optionMenuStatus = "";


const showOptionMenu = function ( id , type ){

    const optionMenu = document.getElementById("userOptionMenu");
    const elementId = type + id;
    const element = document.getElementById( elementId );
    const elementLocation = element.getBoundingClientRect();
    console.log( elementLocation.top , elementLocation.bottom , elementLocation.right , elementLocation.left );

    if ( optionMenuStatus === elementId ){

        optionMenuStatus = "";
        optionMenu.style.display = "none"

    }else{

        optionMenuStatus = elementId;
        optionMenu.style.display = "flex"
        optionMenu.style.position = "absolute";
        let topValue = elementLocation.top + window.scrollY;
        let leftValue = elementLocation.left + window.scrollX;
        optionMenu.style.margin = topValue +"px" + " auto auto "+ leftValue +"px";

    }


}