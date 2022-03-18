const activityStatus = document.getElementById("activityStatus");

const acceptFriendRequestActivityStatus = function (){

    activityStatus.innerHTML += '<div class="innerActivityStatus hideElement">' +
        '                <div class="requestsActivityStatusMessage">' +
        '                    Friend request has been accepted' +
        '                </div>' +
        '                <div class="requestsActivityStatusIcon">' +
        '                    <img class="greenColor" src="../Resources/Icons/done_white_24dp.svg" alt="">' +
        '                </div>' +
        '            </div>'

}

const declineFriendRequestActivityStatus = function (){

    activityStatus.innerHTML += '<div class="innerActivityStatus hideElement">' +
        '                <div class="requestsActivityStatusMessage">' +
        '                    Friend request has been declined' +
        '                </div>' +
        '                <div class="requestsActivityStatusIcon">' +
        '                    <img class="redColor" src="../Resources/Icons/close_white_24dp.svg" alt="">' +
        '                </div>' +
        '            </div>'

}

const acceptEnrollRequestActivityStatus = function (){

    activityStatus.innerHTML += '<div class="innerActivityStatus hideElement">' +
        '                <div class="requestsActivityStatusMessage">' +
        '                    Enroll request has been accepted' +
        '                </div>' +
        '                <div class="requestsActivityStatusIcon">' +
        '                    <img class="greenColor" src="../Resources/Icons/done_white_24dp.svg" alt="">' +
        '                </div>' +
        '            </div>'

}

const declineEnrollRequestActivityStatus = function (){

    activityStatus.innerHTML += '<div class="innerActivityStatus hideElement">' +
        '                <div class="requestsActivityStatusMessage">' +
        '                    Enroll request has been declined' +
        '                </div>' +
        '                <div class="requestsActivityStatusIcon">' +
        '                    <img class="redColor" src="../Resources/Icons/close_white_24dp.svg" alt="">' +
        '                </div>' +
        '            </div>'

}