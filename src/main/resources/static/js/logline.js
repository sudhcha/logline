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
    var splits = searchKeys.split(",");
    $("li").each(function(){
        //sql
        var dbMatch = $(this).text().match("Blue2Dao*");
        if(dbMatch){
            $(this).css("color","#104E8B");
            var schemaMatch = $(this).text().match("PNASCO.[A-Z|_]+");
            if(schemaMatch){ $(this).html($(this).text().replace(schemaMatch[0],"<span class='sccf'>"+schemaMatch[0]+"</span>"))};
        }
        //jms message
        if($(this).text().match("JMSSender|DispatchQSender|MessageDispatcher|jms/")){
            $(this).css("color","#1874CD");
        }
        //userid
        var userMatch = $(this).text().match("[A-Z0-9]{6,7}")
        if(userMatch){
           var keyNotMatch = splits.indexOf(userMatch[0])== -1;
           if(keyNotMatch && userMatch[0].match('[A-Z]+')&& userMatch[0].match('[0-9]*')&& !userMatch[0].match('SELECT|INSERT|UPDATE') ){
                $(this).html($(this).text().replace(userMatch[0],"<span class='sccf'>"+userMatch[0]+"</span>"));
           }
        }
        //sccf
        var sccfMatch = $(this).text().match("[0-9]{17}");
        if(sccfMatch && !dbMatch){
          var keyNotMatch = splits.indexOf(sccfMatch[0])== -1;
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
    $(".highlight").wrap("<a class='search' href='#'></a>");
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
  var keyCount = 0;

  var scrollTo = function (selector, time, verticalOffset) {
      time = typeof(time) != 'undefined' ? time : 1000;
      verticalOffset = typeof(verticalOffset) != 'undefined' ? verticalOffset : 0;
      element = selector;
      offset = element.offsetTop;
      offsetTop = offset+ verticalOffset;
      $('html, body').animate({
          scrollTop: offsetTop
      }, time);
  };

  var assignButtons = function(){
    $('#key_prev').click(function () {
        scrollTo(allKeys[0]);
    });
    $('#key_next').click(function () {
        var length = allKeys.length -1;
        scrollTo(allKeys[length]);
    });
  };


  this.boot = function(){
     toolDialog = $("#tool-dialog").dialog({
                        autoOpen: true,
                        width: 200,
                        height: 200,
                        position: 'right top',
                        resizable: 'false',
                        draggable: false });
     $(window).scroll(function () {
        toolDialog .dialog("option","position","right top");
     });
     assignButtons();
  };//end of boot

  };
//-------------------------------------------------------------------------------------------------
$(document).ready(function() {
	new LogForm().boot();
});
