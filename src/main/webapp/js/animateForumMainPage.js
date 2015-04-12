$(document).ready(function() {
//	$("#mainContainer").children().hide().show(1000);
	$.ajax({
		type: 'POST',
		url: 'resources/php/mainForum.php',
		data: {},
		success: function(boards) {
			$("#mainContainer").html(boards);
			$("#mainContainer").hide().fadeIn("slow");
		}	
	});
	
});
