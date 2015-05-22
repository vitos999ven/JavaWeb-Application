jQuery(document).ready(function($){
	function search(){
		var search = getCookie("productsPageNum");
		$('.product').each(function(){
			var self = $(this);
			/*if (!self.find(".header").val().contain(search)){
				self.css("display", "none");
			}else{
				self.css("display", "block");
			}*/
		})
	};
    $('#search-input').val(getCookie("productsSearch"));
    $(".page-link:not(.current)").each(function(){
        var self = $(this);
        self.click(function(){
            setCookie("productsPageNum", this.id.substring(4), {expires: 3600});
            search();
        });
    });
    $("#search-submit").click(function(){
		var search = $("#search-input").val();
        setCookie("productsSearch", search, {expires: 10});
		console.log("search: " + search);
        document.location.href = "products?search=" + encodeURI(search);
    });
    $('.product>.header').each(function(){
        var self = this;
        $(this).click(function(){
            var product = self.parentNode.id;
            document.location.href = "product?id=" + product;
        });
    });
});    