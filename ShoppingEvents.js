jQuery(document).ready(function($){
    var buttonEvents = {
        mouseenter: function(){
            $(this).stop().animate({color:"white", backgroundColor:"#dd7755", borderColor: "#dd7755"}, 300);
        },
        mouseleave: function(){
            $(this).stop().animate({color:"#dd7755", backgroundColor:"white", borderColor: "#dd7755"}, 300);
        },
        click: function(){
            function leave(){
                $(this).delay(300).animate({color:"#dd7755", backgroundColor:"white", borderColor: "#dd7755"}, 300);
            }
			document.location.href = "/LAB3/booking";
				
        }
    };
    $(".buyButton").mouseenter(buttonEvents.mouseenter)
            .mouseleave(buttonEvents.mouseleave)
            .click(buttonEvents.click);
});    