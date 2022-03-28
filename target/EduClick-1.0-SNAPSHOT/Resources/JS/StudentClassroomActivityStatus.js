const activityStatus = document.getElementById("activityStatus");



const ePostAnswerLoadingActivityStatus = function (){


    activityStatus.innerHTML += '<div class="innerActivityStatus ">' +
        '                <div class="ActivityStatusMessage">' +
        '                    Please wait... while you answering the question' +
        '                </div>' +
        '                <div class="ActivityStatusIcon">' +
        '                    <img class="loadingGif" src="../Resources/Gifs/loading.gif" alt="">' +
        '                </div>' +
        '            </div>'

    setTimeout(function() {
        activityStatus.removeChild(activityStatus.children[0]);
    }, 3000);

}

const ePostAnswerLoadedActivityStatus = function (){

    activityStatus.innerHTML += '<div class="innerActivityStatus ">' +
        '                <div class="ActivityStatusMessage">' +
        'Answer posting Completed' +
        '                </div>' +
        '                <div class="ActivityStatusIcon">' +
        '                    <img class="greenColor" src="../Resources/Icons/done_white_24dp.svg" alt="">' +
        '                </div>' +
        '            </div>'

    setTimeout(function() {
        activityStatus.removeChild(activityStatus.children[0]);
    }, 3000);

}