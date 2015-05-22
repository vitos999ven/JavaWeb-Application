jQuery(document).ready(function($){
    var buttonEvents = {
        mouseenter: function(){
            $(this).stop().animate({color:"white", backgroundColor:"#dd7755", borderColor: "#dd7755"}, 300);
        },
        mouseleave: function(){
            $(this).stop().animate({color:"#dd7755", backgroundColor:"white", borderColor: "#dd7755"}, 300);
        },
        click: function(){
			if (this.id){
				Ajax("DELETE", '/LAB3/ShoppingServlet', { id: this.id}, {}, function(returnedData){
							console.log("Shopping Cart Size: " + returnedData);
							location.reload(true);
				});	
			};
        }
    };
    $(".deleteButton").mouseenter(buttonEvents.mouseenter)
            .mouseleave(buttonEvents.mouseleave)
            .click(buttonEvents.click);
});