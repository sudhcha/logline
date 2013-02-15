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
        new LogTabs().boot();
        new LogLight().boot(keys);
    };

    this.boot = function(){
        $("#analyse").click(post);
        $("#loading-div-background").css({ opacity: 0.7 });
    };
};
//-------------------------------------------------------------------------------------------------
LogTabs = function(){
    var threadTabs = function(){
     //$('.content').slideToggle('fast');
       $('.expand').click(function(){
          $(this).siblings('.content').slideToggle('slow');
        });
    };

    this.boot = function(){
        threadTabs();
    };
};
//-------------------------------------------------------------------------------------------------
LogLight = function(){
  var timePattern = new RegExp("([.*])+",'gi');

  var specials = function(){
    $("li").each(function(){
        if($(this).text().match("Blue2Dao*"))
            {$(this).css("color","#104E8B");}

        if($(this).text().match("JMSSender|DispatchQSender|MessageDispatcher|jms/"))
            {$(this).css("color","#1874CD");}

        var match = $(this).text().match("[0-9]{17}");
        if(match)
            {$(this).html($(this).text().replace(match[0],"<span class='sccf'>"+match[0]+"</span>"))}



        }//end of each function
    );// end of each li
  };

  var keysUp = function(keys){
    $("#results").highlight(keys.split(","));
  };

  this.boot = function(keys){
     specials();
     keysUp(keys);
  };
};

//-------------------------------------------------------------------------------------------------
$(document).ready(function() {
	new LogForm().boot();
});
