LogForm = function(){
    var folder, keys;


    var pick = function() {
		folder = $("#folder").val();
		keys = $("#keys").val();
	};

    var post = function(){
        pick();
        $.ajax({
        	url : "analyse",
        	data : {
        		folder : folder,
        		keys : keys
        	},
        	type : "POST"
        }).done(displayResults);
    };

    var displayResults = function(response){
      $("#results").html(response);
    };

    this.boot = function(){
      $("#analyse").click(post);
    };
}

$(document).ready(function() {
	new LogForm().boot();
});
