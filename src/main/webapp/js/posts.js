var postLabel;

$(function() {
	/**
	var smallest = 700;
	var hidden = false;
	var breadcrumb = $(".breadcrumb"); */
	postLabel = $("#new-post-label");

	/**	
	function resetBreadcrumb() {
		hidden = false;
		breadcrumb.show();		
	}
	*/

	/**
	function reactBreadcrumb() {
		if(window.innerWidth < smallest && !hidden) {
			breadcrumb.hide();
			hidden = true;
		} else if(window.innerWidth >= smallest && hidden) {
			resetBreadcrumb();
		}
		console.log("resized: " + window.innerWidth);
	}

	reactBreadcrumb();
	

	$(window).resize(function() {
		reactBreadcrumb();
	});
	*/

});

function updatePostLabel() {
	if(postLabel.text() == "New Post") {
		postLabel.text("Edit Post");
	} else postLabel.text("New Post");
}
