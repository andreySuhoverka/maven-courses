window.onload = function(){
    var showStudentsButton = document.getElementById("showStudentsButton");
    showStudentsButton.onclick = function() {
        var studentBox = document.getElementById("studentBox");
        var displayValue = studentBox.style.display;
        if(displayValue != "none"){
            studentBox.style.display = "none"
        }else{
            studentBox.style.display = "block";
        }


    }
}