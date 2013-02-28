HomePage = function(){

      var setupNavigation = function(){
        $('a.config').click(function(){
          $.ajax({url : "config",type : "GET"}).done(updateMainBox);
        });
        $('a.settings').click(function(){
          $.ajax({url : "settings",type : "GET"}).done(updateMainBox);
        });
      };

      var updateMainBox = function(response){
        $('#main_box').html(response);
        $('#result_box').html('<p>Awaiting input...</p>');
      };

      this.boot = function(){
        setupNavigation();
      };

};

$(document).ready(function() {
	new HomePage().boot();
});
