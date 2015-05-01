$(document).ready(function() {
	$('a.category').click(function(event) {
		console.log("js ------ here")
		var str_split = event.target.title.split("~");
		var cat_id = str_split[0];
		var cat_name = str_split[1];
		var board_id = str_split[2];
		var board_name = str_split[3];
		console.log("should have just posted, str_split[1] = " + str_split[1]);
		$('#cat_id').val(cat_id);
		$('#cat_name').val(cat_name);
		$('#board_name').val(board_name);
		$('#board_id').val(board_id);
		document.getElementById('toCategory').submit();
	});
});