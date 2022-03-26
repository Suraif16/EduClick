const activityStatus = document.getElementById("activityStatus");

const acceptFriendRequestActivityStatus = function (){

    activityStatus.innerHTML += '<div class="innerActivityStatus ">' +
        '                <div class="ActivityStatusMessage">' +
        '                    Friend request has been accepted' +
        '                </div>' +
        '                <div class="ActivityStatusIcon">' +
        '                    <img class="greenColor" src="../Resources/Icons/done_white_24dp.svg" alt="">' +
        '                </div>' +
        '            </div>'

    setTimeout(function() {
        activityStatus.removeChild(activityStatus.children[0]);
    }, 4000);

}

const declineFriendRequestActivityStatus = function (){

    activityStatus.innerHTML += '<div class="innerActivityStatus ">' +
        '                <div class="ActivityStatusMessage">' +
        '                    Friend request has been declined' +
        '                </div>' +
        '                <div class="ActivityStatusIcon">' +
        '                    <img class="redColor" src="../Resources/Icons/close_white_24dp.svg" alt="">' +
        '                </div>' +
        '            </div>'

    setTimeout(function() {
        activityStatus.removeChild(activityStatus.children[0]);
    }, 4000);

}

const acceptEnrollRequestActivityStatus = function (){

    activityStatus.innerHTML += '<div class="innerActivityStatus ">' +
        '                <div class="ActivityStatusMessage">' +
        '                    Enroll request has been accepted' +
        '                </div>' +
        '                <div class="ActivityStatusIcon">' +
        '                    <img class="greenColor" src="../Resources/Icons/done_white_24dp.svg" alt="">' +
        '                </div>' +
        '            </div>'

    setTimeout(function() {
        activityStatus.removeChild(activityStatus.children[0]);
    }, 4000);

}

const declineEnrollRequestActivityStatus = function (){

    activityStatus.innerHTML += '<div class="innerActivityStatus ">' +
        '                <div class="ActivityStatusMessage">' +
        '                    Enroll request has been declined' +
        '                </div>' +
        '                <div class="ActivityStatusIcon">' +
        '                    <img class="redColor" src="../Resources/Icons/close_white_24dp.svg" alt="">' +
        '                </div>' +
        '            </div>'

    setTimeout(function() {
        activityStatus.removeChild(activityStatus.children[0]);
    }, 4000);

}

const classroomSuccessfullyCreated = function ( classroomName , subject , grade , yearOfExamination ){
    
    activityStatus.innerHTML += '<div class="innerActivityStatus">' +
        '                <div class="ActivityStatusMessage">' +
        '                    Class Created Successfully<br/>' +
         classroomName + " " + subject + " " + grade + " " + yearOfExamination +
        '                </div>' +
        '                <div class="ActivityStatusIcon">' +
        '                    <img class="greenColor" src="../Resources/Icons/done_white_24dp.svg" alt="">' +
        '                </div>' +
        '            </div>'

    setTimeout(function() {
        activityStatus.removeChild(activityStatus.children[0]);
    }, 4000);
    
}

const ePostLoadingActivityStatus = function ( type ){


    activityStatus.innerHTML += '<div class="innerActivityStatus ">' +
        '                <div class="ActivityStatusMessage">' +
        '                    Please wait... while ' + type + ' is loading' +
        '                </div>' +
        '                <div class="ActivityStatusIcon">' +
        '                    <img class="loadingGif" src="../Resources/Gifs/loading.gif" alt="">' +
        '                </div>' +
        '            </div>'

    setTimeout(function() {
        activityStatus.removeChild(activityStatus.children[0]);
    }, 3000);

}

const ePostLoadedActivityStatus = function ( type ){

    activityStatus.innerHTML += '<div class="innerActivityStatus ">' +
        '                <div class="ActivityStatusMessage">' +
        type + ' posting Completed' +
        '                </div>' +
        '                <div class="ActivityStatusIcon">' +
        '                    <img class="greenColor" src="../Resources/Icons/done_white_24dp.svg" alt="">' +
        '                </div>' +
        '            </div>'

    setTimeout(function() {
        activityStatus.removeChild(activityStatus.children[0]);
    }, 3000);

}

/*
acceptEnrollRequestActivityStatus();
acceptFriendRequestActivityStatus();
declineEnrollRequestActivityStatus();
declineFriendRequestActivityStatus();
*/

