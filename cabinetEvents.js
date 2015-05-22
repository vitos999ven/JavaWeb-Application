jQuery(document).ready(function($){
	$('.date').each(function(){
		this.innerHTML = updateTime(new Date(+this.innerHTML));
	});
    $("#sendComment").click(function(){
		var value = $("#commentText").val();
		var date = new Date();
		if (!!value){
			Ajax("POST", '/LAB3/CommentServlet', { value: value, date: +date}, {}, function(returnedData){
					var content = "<div class='comment'>"+ updateTime(date) + ": " + value + "</div>"
					$('#comments').html($('#comments').html() + content);
			});
		}
	});
	function updateTime(date) {
    var options = {
        year: 'numeric', month: 'numeric', day: 'numeric',
        hour: 'numeric', minute: 'numeric', second: 'numeric'
    };
    var language = getCookie("language");
	language = language.substring(0, 2);
    var text = new Intl.DateTimeFormat(language, options).format(date);
	return text;
};
	setInterval(function(){
		var date = new Date();
		$('#currentTime').html(updateTime(date));
		//$('#currentTime').html((1900 + date.getYear()) 
		//				+ "." + ((date.getMonth() < 9)? "0" : "") + (date.getMonth() + 1) + "." + date.getDate() 
		//				+ ", " + ((date.getHours() < 10)? "0" : "") + date.getHours() + ":" 
		//				+ ((date.getMinutes() < 10)? "0" : "") + date.getMinutes()+":"+ ((date.getSeconds() < 10)? "0" : "") + date.getSeconds());
	}, 1000);
});    