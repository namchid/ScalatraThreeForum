

$(function() {
	//set up the user-form
	var f = $("#user-form");
	var width = $(window).width();
	var height= $(window).height();
	var thirdWidth = width / 3 + "px";
	var thirdHeight = height / 3 + "px";
	f.width(thirdWidth);
	f.height(thirdHeight);
	f.css("margin-left", thirdWidth);
	f.css("margin-top", thirdHeight);

	//ease in user-form and forum-title
	f.fadeOut(0);
	var ft = $("#forum-title");
	var fadeInSpeed = 2000;
	f.fadeIn(fadeInSpeed);
	ft.fadeOut(0);
	ft.fadeIn(fadeInSpeed + 3000);

	//set up form-selector
	var fs = $("#form-selector");
	fs.width(width / 12 + "px");
	//fs.css("margin-left", 2 * width / 3 + "px");
	//fs.css("margin-top", thirdHeight);
		

	//display login form
	// loadLoginForm();

//////////
	
	//set up the background canvas
	var c = document.getElementById("c");
	var ctx = c.getContext("2d");

	//making the canvas full screen
	c.height = window.innerHeight;
	c.width = window.innerWidth;

	var russian = "Ѡ ѡ Ѣ Ѥ Ѩ Ѭ Ҙ Ҕ Ҩ Ӂ Ӌ Ҿ Ө Ӭ Ӥ Ӹ Ѣ Ѧ ѯ Ѱ".split(" ");

	var font_size = 22;
	var columns = c.width/font_size; //number of columns for the rain
	//an array of drops - one per column
	var drops = [];
	//x below is the x coordinate
	//1 = y co-ordinate of the drop(same for every drop initially)
	for(var x = 0; x < columns; x++) {
		drops[x] = 1; 
	}

	function draw() {
		//Black BG for the canvas
		//translucent BG to show trail
		ctx.fillStyle = "rgba(0, 0, 0, 0.10)";
		ctx.fillRect(0, 0, c.width, c.height);

		ctx.fillStyle = "#0F0"; //green text
		ctx.font = font_size + "px arial";
		//looping over drops
		for(var i = 0; i < drops.length; i++) {
			//a random character to print
			var text = russian[Math.floor(Math.random()*russian.length)];
			//x = i*font_size, y = value of drops[i]*font_size
			ctx.fillText(text, i*font_size, drops[i]*font_size);

			//sending the drop back to the top randomly after it has crossed the screen
			//adding a randomness to the reset to make the drops scattered on the Y axis
			if(drops[i]*font_size > c.height && Math.random() > 0.975) {
				drops[i] = 0;
			}
			
			//incrementing Y coordinate
			drops[i]++;
		}
	}

	var speed = 100;
	setInterval(draw, speed);

	$(window).resize(function() {
		if(c.width < window.innerWidth) {
			c.width = window.innerWidth;
			columns  = c.width/font_size;	
		}
		if(c.height < window.innerHeight) {	
			c.height = window.innerHeight;
		}
	});

});
