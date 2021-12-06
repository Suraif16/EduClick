
const showOptionMenu = function ( id , type ){

    const optionMenu = document.getElementById("userOptionMenu");

    if ( optionMenu.innerHTML === ""){

        console.log( "Yes" );

    }

    console.log( id , type );
    let element = document.getElementById( type + id )
    let elementLocation = element.getBoundingClientRect();
    // let bodyElement = document.getElementById("post1").getBoundingClientRect();
    console.log( elementLocation.top , elementLocation.bottom , elementLocation.right , elementLocation.left );
    optionMenu.style.position = "absolute";
    let topValue = elementLocation.top + window.scrollY /*- bodyElement.top*/;
    let leftValue = elementLocation.left + window.scrollX /*- bodyElement.left*/;
    console.log( topValue , leftValue );
    optionMenu.style.margin = topValue +"px" + " auto auto "+ leftValue +"px";

}