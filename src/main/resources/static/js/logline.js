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
      new LogAnalysis().boot();
    };

    this.boot = function(){
      $("#analyse").click(post);
    };
};

LogAnalysis = function(){
    var threadTabs = function(){
       $('.content').slideToggle('fast');
       $('.expand').click(function(){
          $(this).siblings('.content').slideToggle('slow');
        });
    };

    this.boot = function(){
        threadTabs();
    };
};

$(document).ready(function() {
	new LogForm().boot();
});
