administratorFirme.controller('IzvestajCtrl', function($scope, $http, $compile, $timeout, $rootScope, $cookies, $window, tokenService){
	
	var control = this;
	control.izvod = {};
	
	$scope.datum = new Date();
	$scope.izvestaj = {};
	
	$scope.init = function(){
		
	}
	
	this.submitClick = function(){
		
		$http.post('/special/posaljiZahtevZaIzvestaj', control.izvod).
	    then(function(response) {
	    	$scope.izvestaj = response.data;
	    });
		
	}
	
	
});