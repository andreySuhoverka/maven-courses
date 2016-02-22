$(document).ready(function() {

    //checks for the button click event
    $("input[type='button']").click(function(e){

        //get the form data using another method
        var parent = this.parentNode;
       // var mark = $("input#mark").val();
        var command = parent.children[0].value;
        var login = parent.children[1].value;
        var courseId = parent.children[2].value;
        var mark = parent.children[3].value;

        dataString = "command=" + command + "&login=" + login + "&courseId=" + courseId + "&mark=" + mark;

        //make the AJAX request
        $.ajax({
            type: "POST",
            url: "/controller",
            data: dataString
        })
        parent.children[3].value = "";

    });

});