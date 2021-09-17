
function enableDisableStatus( id ){

    let enableStringValue = "enable" + id;

    let disableStringValue = "disable" + id;

    let enableButton = document.getElementById(enableStringValue);

    let disableButton = document.getElementById(disableStringValue);

    if (disableButton.style.display === "none"){

        /*defaultView.getComputedStyle(enableButton)*/
        disableButton.style.display = "block";
        enableButton.style.display = "none";


    }else{

        disableButton.style.display = "none";
        enableButton.style.display = "block";

    }



}

