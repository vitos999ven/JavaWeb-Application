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
});