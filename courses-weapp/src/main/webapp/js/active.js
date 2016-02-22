window.onload = function(){
    var menuItems = document.getElementsByClassName("subHeader").getElementsByTagName("a");
    for(var k = 0; k < menuItems.length; k++){
        menuItems[k].onclick = function() {
            for(var m = 0; m < menuItems.length; m++){
                menuItems[m].removeClass("active");
            }
            menuItems[k].addClass("active");
        }
    }


};