HomePage = function(){

      var setupNavigation = function(){
        $('a.logs').click(function(){
          $.ajax({url : "log-tool",type : "GET"}).done(updateMainBoxForLog);
        });
        $('a.settings').click(function(){
          $.ajax({url : "settings",type : "GET"}).done(updateMainBox);
        });
        $('a.config').click(function(){
          $.ajax({url : "config-tool",type : "GET"}).done(updateMainBoxForDiff);
        });
      };
      var updateMainBox = function(response){
        $('#tool_box').html(response);
        $('#results').html('<p>Awaiting input...</p>');
      };

      var updateMainBoxForDiff = function(response){
        updateMainBox(response);
        new FileDiffForm().boot();
      };

      var updateMainBoxForLog = function(response){
        updateMainBox(response);
        new LogForm().boot();
        new LogFetch().boot();
      };

      this.boot = function(){
        setupNavigation();
      };

};

$(document).ready(function() {
	new HomePage().boot();
});
