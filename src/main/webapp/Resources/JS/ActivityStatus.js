const activityStatus = document.getElementById("activityStatus");

const acceptFriendRequestActivityStatus = function (){

    activityStatus.innerHTML += '<div class="innerActivityStatus ">' +
        '                <div class="requestsActivityStatusMessage">' +
        '                    Friend request has been accepted' +
        '                </div>' +
        '                <div class="requestsActivityStatusIcon">' +
        '                    <img class="greenColor" src="../Resources/Icons/done_white_24dp.svg" alt="">' +
        '                </div>' +
        '            </div>'

    setTimeout(function() {
        activityStatus.removeChild(activityStatus.children[0]);
    }, 4000);

}

const declineFriendRequestActivityStatus = function (){

    activityStatus.innerHTML += '<div class="innerActivityStatus ">' +
        '                <div class="requestsActivityStatusMessage">' +
        '                    Friend request has been declined' +
        '                </div>' +
        '                <div class="requestsActivityStatusIcon">' +
        '                    <img class="redColor" src="../Resources/Icons/close_white_24dp.svg" alt="">' +
        '                </div>' +
        '            </div>'

    setTimeout(function() {
        activityStatus.removeChild(activityStatus.children[0]);
    }, 4000);

}

const acceptEnrollRequestActivityStatus = function (){

    activityStatus.innerHTML += '<div class="innerActivityStatus ">' +
        '                <div class="requestsActivityStatusMessage">' +
        '                    Enroll request has been accepted' +
        '                </div>' +
        '                <div class="requestsActivityStatusIcon">' +
        '                    <img class="greenColor" src="../Resources/Icons/done_white_24dp.svg" alt="">' +
        '                </div>' +
        '            </div>'

    setTimeout(function() {
        activityStatus.removeChild(activityStatus.children[0]);
    }, 4000);

}

const declineEnrollRequestActivityStatus = function (){

    activityStatus.innerHTML += '<div class="innerActivityStatus ">' +
        '                <div class="requestsActivityStatusMessage">' +
        '                    Enroll request has been declined' +
        '                </div>' +
        '                <div class="requestsActivityStatusIcon">' +
        '                    <img class="redColor" src="../Resources/Icons/close_white_24dp.svg" alt="">' +
        '                </div>' +
        '            </div>'

    setTimeout(function() {
        activityStatus.removeChild(activityStatus.children[0]);
    }, 4000);

}

/*
acceptEnrollRequestActivityStatus();
acceptFriendRequestActivityStatus();
declineEnrollRequestActivityStatus();
declineFriendRequestActivityStatus();
*/

