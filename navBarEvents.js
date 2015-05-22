jQuery(document).ready(function($){
    $("#language-select").each(function(){
        var self = $(this);
        self.change(function(){
            setCookie("language", this.value, {expires: 3600});
            console.log(this.value);
            location.reload(true);
        });
    });
});   