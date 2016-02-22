window.onload = function(){
    var showStudentsButton = document.getElementsByClassName("show-students-btn")[0];
    if(showStudentsButton !== undefined) {
        showStudentsButton.onclick = function() {
            var studentEnum = document.getElementById("stud-enum");
            var displayValue = studentEnum.style.display;
            if(displayValue == "none" || displayValue == ""){
                studentEnum.style.display = "block";
                showStudentsButton.innerHTML = "hide students";
            }else{
                studentEnum.style.display = "none";
                showStudentsButton.innerHTML = "show students";

            }
        }

        var lis = document.getElementById("stud-enum").getElementsByTagName("li");
        for(var k = 0; k < lis.length; k++) {
            var elemToManage = lis[k].nextSibling.nextSibling;
            var displayValue = elemToManage.style.display;
            lis[k].onclick = function() {
                var elemToManage = this.nextSibling.nextSibling;
                var displayValue = elemToManage.style.display;
                if(displayValue == "none" || displayValue == ""){
                    elemToManage.style.display = "inline";
                }else{
                    elemToManage.style.display = "none";
                }
            }
        }

    }




}