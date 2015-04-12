function addPost(topic_id, user_id){
    var formatted_input =  tinymce.activeEditor.getContent();
    alert(formatted_input);
    var result_post;

    if (formatted_input == " "){
        alert("No blank posts please!");
        return;
    }

    $('#formatted_input').val(formatted_input);
    document.getElementById('post-form').submit();


    /*
    if (window.XMLHttpRequest){
        xmlhttp = new XMLHttpRequest();
    }
    else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }

    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState ==4 && xmlhttp.status ==200){
            result_post= xmlhttp.responseText;
            result_post= result_post.trim();
        }

    }


    xmlhttp.open("GET","../Forum/resources/php/addPost.php?topic_id="+topic_id+"&user_id="+user_id+"&input="+formatted_input,false);
    xmlhttp.send();

    console.log (formatted_input);
    console.log (result_post);

    if(result_post == "good"){
        document.Test.action = "../Forum/posts.php?";
        document.Test.submit();
    }
    else
        alert ("Could not post to this topic. Please check post and retry.");
        */

};

/* function editPost() {

 var old_post = '<ul style="list-style-type: disc;"><li style="text-align: center;"><span style="text-decoration: line-through;"><strong>Test mode activated</strong></span></li></ul>';
 tinymce.activeEditor.execCommand('mceInsertRawHTML', false, old_post);
 };*/

