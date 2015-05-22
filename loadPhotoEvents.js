jQuery(document).ready(function($){
    document.getElementById('SendPhotoButton').onclick = function(){
                    if (document.getElementById('fileInput').files.length === 1){
                     document.forms["loadPhoto"].submit();
                     setTimeout(function(){document.location.href = "/LAB3/cart";},500);
                    }
                };
});   