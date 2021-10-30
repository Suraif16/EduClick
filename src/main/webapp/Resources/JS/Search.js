const search = document.getElementById( "searchBarText" );

search.addEventListener( "keyup" , function ( event ){

    if(event.key === "Enter"){


        let httpreq = new XMLHttpRequest();

        httpreq.onreadystatechange = function (){

            if ( httpreq.readyState === 4 && httpreq.status === 200){

                window.location.replace("/EduClick_war_exploded/Search.html")

            }
        }
        let url = "/EduClick_war_exploded/Search?searchValue="+search.value+"&searchType=Teacher";
        httpreq.open( "GET" , url ,true);
        httpreq.send();

    }

});