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


function onPageClick(page, topicID){
	var url = "/posts?topic_id="+topicID+"&page="+page ;
	window.location = url;
}
function onCatClick(catID){
	var url = "/categories?cat_id="+catID ;
	window.location = url;
}
