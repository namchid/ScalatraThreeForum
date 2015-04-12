function loadLoginForm() {
	$.ajax({
		type: 'POST',
		url: 'resources/php/loginFormFields.php',
		data: {},
		success: function(fields) {	
			$("#form-container").html(fields);
			$("#form-container").hide().fadeIn("slow");
			var width = $(window).width();
                        var nu = $("#new-user");
                        var l = $("#login");

                        l.width(width / 10 + "px");
                        nu.width(width / 12 + "px");
                        l.css("background-color", "#44A2C2");
                        nu.css("background-color", "#B0CFBF");
			$(".input-fields")[0].focus();
		}
	});

}

function loadNewUserForm() {
	$.ajax({
		type: 'POST',
		url: 'resources/php/newUserFormFields.php',
		data: {},
		success: function(fields) {
			$("#form-container").html(fields);
			$("#form-container").hide().fadeIn("slow");
			var width = $(window).width();
			var nu = $("#new-user");
			var l = $("#login");
			
			nu.width(width / 10 + "px");
			l.width(width / 12 + "px");
			nu.css("background-color", "#44A2C2");
			l.css("background-color", "#B0CFBF");
			$('.input-fields')[0].focus();
		}
	});
}
