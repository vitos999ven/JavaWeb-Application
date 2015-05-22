jQuery(document).ready(function($){
    $('.product-photo-lowimg').each(function(){
        var self = $(this);
        self.load(function(){
            var width = self.width();
            var height = self.height();
            if (width > height){
                self.css("max-height", "50px");
                var coef = -25 * (width - height)/width;
                self.parent().css("margin-left", coef + "px");
            }else{
                self.css("max-width", "50px");
                var coef = -25 * (height - width)/height;
                self.parent().css("margin-top", coef + "px");
            };
        });
        self.click(function(){
            var src = self.attr("src");
            $('.product-photo-bigimg').attr("src", src.substring(0, src.indexOf("low")) + ".jpg");
        });
    });
    
    var insetTitleEvents = {
        click: function(){
            var self = $(this);
            if (self.hasClass("current")) return false;
            $(".insetTitle.current").removeClass("current").stop()
                    .animate({color:"#bbaaff", backgroundColor:"white", borderColor: "#bbaaff"}, 300)
                    .mouseenter(insetTitleEvents.mouseenter)
                    .mouseleave(insetTitleEvents.mouseleave);
            self.addClass("current").stop()
                    .unbind("mouseenter", insetTitleEvents.mouseenter)
                    .unbind("mouseleave", insetTitleEvents.mouseleave)
                    .animate({color:"#white", backgroundColor:"#9988dd", borderColor: "#9988dd"}, 100);
            var contentId = this.id.substring(0, this.id.indexOf("Title")) + "Content";
            $(".insetContent").each(function(){
                var object = $(this);
                if (object.attr("id") === contentId){
                    object.removeClass("hidden");
                    return true;
                }
                object.addClass("hidden");
            });
        },
        mouseenter: function(){
            $(this).stop().animate({color:"white", backgroundColor:"#bbaaff", borderColor: "#bbaaff"}, 300);
        },
        mouseleave: function(){
            $(this).stop().animate({color:"#bbaaff", backgroundColor:"white", borderColor: "#bbaaff"}, 300);
        }
    };
    $(".insetTitle.current").css("color","white").css("backgroundColor", "9988dd").css("borderColor", "9988dd");
    $(".insetTitle").mouseenter(insetTitleEvents.mouseenter)
        .mouseleave(insetTitleEvents.mouseleave)
        .click(insetTitleEvents.click);

    $(".language-img").click(function(){
        setCookie("language", this.id.substring(0, this.id.indexOf("Img")), {expires: 3600});
        location.reload();
    }).mouseenter(function(){
        $(this).stop().animate({opacity: 1}, 200);
    }).mouseleave(function(){
        $(this).stop().animate({opacity: 0.7}, 200);
    });
        
    
});