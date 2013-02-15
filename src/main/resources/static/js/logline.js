LogForm = function(){
    var folder, keys;

    var pick = function() {
		folder = $("#folder").val();
		keys = $("#keys").val();
	};

    var post = function(){
    $("#loading-div-background").show();
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
        $("#loading-div-background").hide();
        new LogAnalysis().boot(keys);
        new LogLight().boot();
    };

    this.boot = function(){
        $("#analyse").click(post);
        $("#loading-div-background").css({ opacity: 0.7 });
    };
};

LogAnalysis = function(){
    var threadTabs = function(){
//       $('.content').slideToggle('fast');
       $('.expand').click(function(){
          $(this).siblings('.content').slideToggle('slow');
        });
    };

    var lightUp = function(keys){
      $("#results").highlight(keys.split(","));
    };

    this.boot = function(keys){
        threadTabs();
        lightUp(keys);
    };
};

LogLight = function(){
  this.boot = function(){
    //sql hits
    $("li").each(function(){
      if($(this).text().match("Blue2Dao*")){$(this).css("color","#104E8B")}}
    );
    //message posted
    $("li").each(function(){
      if($(this).text().match("JMSSender|DispatchQSender|MessageDispatcher|jms/")){$(this).css("color","#1874CD")}}
    );
    //

  };
};


$(document).ready(function() {
	new LogForm().boot();
});
