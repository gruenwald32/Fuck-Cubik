$(document).ready(function(){
	$(window).scroll(function() {
		if ($("header").offset().top > 50) {
			$("header").addClass("collapse");
		} else {
			$("header").removeClass("collapse");
		}
	});
});