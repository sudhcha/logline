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
        new ToolDialog().boot();
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
  var searchKeys;

  var specials = function(){
    $("li").each(function(){
        //sql
        var dbMatch = $(this).text().match("Blue2Dao*");
        if(dbMatch){
            $(this).css("color","#104E8B");
            //$(this).html($(this).text().replace("PNASCO","<span class='sccf'>"+XX+"</span>"));
        }
        //jms message
        if($(this).text().match("JMSSender|DispatchQSender|MessageDispatcher|jms/")){
            $(this).css("color","#1874CD");
        }
        //sccf
        var sccfMatch = $(this).text().match("[0-9]{17}");
        if(sccfMatch){
          var keyNotMatch = searchKeys.split(",").indexOf(sccfMatch[0])== -1;
          if(keyNotMatch){
            $(this).html($(this).text().replace(sccfMatch[0],"<span class='sccf'>"+sccfMatch[0]+"</span>"));
          }
        }
        //user-ids


        //time-color


        }//end of each function
    );// end of each li
  };

  var keysUp = function(){
    $("#results").highlight(searchKeys.split(","));
    $(".highlight").wrap("<a class='search'></a>");

  };

  this.boot = function(keys){
     searchKeys = keys;
     specials();
     keysUp();
  };
};
//-------------------------------------------------------------------------------------------------
ToolDialog = function(){
  var allKeys = $(".search");
  var toolDialog;
  this.boot = function(){
     toolDialog = $("#tool-dialog").dialog({
                        autoOpen: true,
                        width: 350,
                        height: 200,
                        position: 'top',
                        resizable: 'false',
                        draggable: true });
     $(window).scroll(function () {
        toolDialog .dialog("option","position","top");
     });

  };//end of boot

  };
//-------------------------------------------------------------------------------------------------
$(document).ready(function() {
	new LogForm().boot();
});
