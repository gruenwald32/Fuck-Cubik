$(document).ready(function(){
	$(window).scroll(function() {
		if ($("header").offset().top > 50) {
			$("header").addClass("collapse");
		} else {
			$("header").removeClass("collapse");
		}
	});
	
	$(function() {
		$('a.scroll').bind('click', function(event) {
			var $anchor = $(this);
			$('html, body').stop().animate({
				scrollTop: $($anchor.attr('href')).offset().top
			}, 1500, 'easeInOutExpo');
			event.preventDefault();
		});
	});
});