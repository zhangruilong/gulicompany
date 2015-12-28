// JavaScript Document
$(document).ready(function() {
			// Store variables
	var accordion_head = $('.accordion > li > a'),
	accordion_body = $('.accordion li > dd');
			// Open the first tab on load
	accordion_head.first().addClass('current').next().slideDown('normal');
			// Click function
	accordion_head.on('click', function(event) {
				// Disable header links
		event.preventDefault();
				// Show and hide the tabs on click
		if ($(this).attr('class') != 'current'){
			accordion_body.slideUp('normal');
			$(this).next().stop(true,true).slideToggle('normal');
			accordion_head.removeClass('current');
			$(this).addClass('current');
		}
	});
});

function shleft(){
	 if (parent.cen.cols=="10,*"){
	  parent.cen.cols="184,*";
	  window.parent.frames["main"].location.reload();
	  document.getElementById("img_left").src="sysimages/bar_left.gif";
	  document.getElementById("divRsl").style.display="block";
	 }
	 else{
	  parent.cen.cols="10,*";
	  window.parent.frames["main"].location.reload();
	  document.getElementById("img_left").src="sysimages/bar_right.gif";
	  document.getElementById("divRsl").style.display="none";
	 }
}