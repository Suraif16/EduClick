function showAnswers( id ){

    let answerId = "answersInPost" + id;
    let answerContainer = document.getElementById( answerId );
    let com = document.getElementById("ans" + id) ;

    if (answerContainer.style.display === "none"){

        answerContainer.style.display = "flex";

        com.style.display = "flex";

    }else{

        answerContainer.style.display = "none";
        com.style.display = "none"
    }

}