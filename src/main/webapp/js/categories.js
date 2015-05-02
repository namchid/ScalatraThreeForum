
    $(document).ready(function () {
        $('tr.topic').click(function (event){
            var topic_id = this.title;
            $('#topic_id').val(topic_id);
           // alert("topic ="+topic_id+ "  #topic_id's val = "+$('#topic_id').val()+ "   this.title= "+this.title);
           // document.getElementById('toTopic').submit();
           var url = "/posts?topic_id="+ topic_id+ "&page=1" ;
           
           window.location = url;
        });
        
        $('#pageNav li.page').click(function (event){
            var clickedPage = this.value;
            $('#myPage').val(clickedPage);
            document.getElementById('pagesForm').submit();
        });
    });

    