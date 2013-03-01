FileDiffForm = function(){
    var machine, release;

    var pick = function() {
		machine = $("#machine").val();
		release = $("#release").val();
	};

    var post = function(){
        pick();
        $.ajax({
            url:"config-result",
            data:{machine:machine, release:release}
        }).done(displayResults);
    };

    var displayResults = function(response){
        $("#results").html(response);
    };

    this.boot = function(){
       $("#fetch_diff").click(post);
    };
};

$(document).ready(function() {
	new FileDiffForm().boot();
});
