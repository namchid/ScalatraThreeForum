$(document).ready(function() {
	$("a.jump_to_topic").click(function(event) {
		console.log("jump to topic executed")
		var topic_id = this.title;
		$("#topic_id").val(topic_id);
		document.getElementById("jumpToTopic").submit();
	});
});