
const showOptionMenu = function ( id , type ){

    const optionMenu = document.getElementById("userOptionMenu")

    console.log( id , type );
    let element = document.getElementById( type + id )
    let elementLocation = element.getBoundingClientRect();
    console.log( elementLocation.top , elementLocation.bottom , elementLocation.right , elementLocation.left );
    optionMenu.style.position = "fixed";
    optionMenu.style.top = elementLocation.top;
    optionMenu.style.left = elementLocation.left;

}