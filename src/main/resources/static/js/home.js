HomePage = function(){

      var setupNavigation = function(){
        $('a.config').click(function(){
          $.ajax({url : "config-tool",type : "GET"}).done(updateMainBox);
        });
        $('a.settings').click(function(){
          $.ajax({url : "settings",type : "GET"}).done(updateMainBox);
        });
      };

      var updateMainBox = function(response){
        $('#tool_box').html(response);
        $('#result_box').html('<p>Awaiting input...</p>');
        new FileDiffForm().boot();
      };

      this.boot = function(){
        setupNavigation();
      };

};

$(document).ready(function() {
	new HomePage().boot();
});
