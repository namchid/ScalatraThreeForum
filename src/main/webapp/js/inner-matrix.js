$(function() {

        var c = document.getElementById("c");
        var ctx = c.getContext("2d");

	c.height = window.innerHeight;
        c.width = window.innerWidth;

	var russian = "Ѡ ѡ Ѣ Ѥ Ѩ Ѭ Ҙ Ҕ Ҩ Ӂ Ӌ Ҿ Ө Ӭ Ӥ Ӹ Ѣ Ѧ ѯ Ѱ".split(" ");

        var font_size = 22;
        var columns = c.width/font_size;
	var drops = [];
	for(var x = 0; x < columns; x++) {
                drops[x] = 1;
        }

	function draw() {
		ctx.fillStyle = "rgba(0, 0, 0, 0.10)";
                ctx.fillRect(0, 0, c.width, c.height);

                ctx.fillStyle = "#0F0";
		ctx.font = font_size + "px arial";

		for(var i = 0; i < drops.length; i++) {
			var text = russian[Math.floor(Math.random()*russian.length)];
			ctx.fillText(text, i*font_size, drops[i]*font_size);
			if(Math.random() > 0.975) {
                                drops[i] = 0;
                        }
			drops[i]++;
                }
        }

        var speed = 150;
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

	
