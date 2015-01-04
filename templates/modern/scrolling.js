﻿$(document).ready(function()
{
	$(window).scroll(function()
	{
		if ($("header").offset().top > 50)
		{
			$("header").addClass("collapse");
		}else
		{
			$("header").removeClass("collapse");
		}
	});
	
	$(function()
	{
		$('a[href*=#]').click(function()
		{
			if (location.pathname.replace(/^\//,'') == this.pathname.replace(/^\//,'') && location.hostname == this.hostname)
			{
				var target = $(this.hash);
				target = target.length ? target : $('[name=' + this.hash.slice(1) +']');
				$('html,body').animate(
				{
					scrollTop: target.offset().top
				}, 1500);
				return false;
			}
		});
	});
});