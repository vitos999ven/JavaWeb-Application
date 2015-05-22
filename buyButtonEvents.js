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
			if (this.id){
				Ajax("POST", '/LAB3/ShoppingServlet', { id: this.id}, {}, function(returnedData){
					console.log("Shopping Cart Size: " + returnedData);
					document.location.href = "/LAB3/cart";
				});	
				};
			$(this).stop().unbind("mouseenter", buttonEvents.mouseenter)
                    .unbind("mouseleave", buttonEvents.mouseleave)
                    .mouseleave(leave)
                    .animate({color:"white", backgroundColor:"red", borderColor: "red"}, {
                        duration: 300,
                        complete: function(){
                            $(this).unbind("mouseleave", leave)
                                .mouseenter(buttonEvents.mouseenter)
                                .mouseleave(buttonEvents.mouseleave);
                            
                        }
            });		
        }
    };
    $(".buyButton").mouseenter(buttonEvents.mouseenter)
            .mouseleave(buttonEvents.mouseleave)
            .click(buttonEvents.click);
});