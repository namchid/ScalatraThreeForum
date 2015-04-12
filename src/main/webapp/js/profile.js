$(function() {
	var smallest = 700;
	var leftPanel = $("#left-panel");

	function divsResize() {
		if(window.innerWidth < smallest) {
			leftPanel.hide();
		} else {
			leftPanel.show();
		}
	}

	divsResize();

	$(window).resize(function() {
		divsResize();
	});

});
