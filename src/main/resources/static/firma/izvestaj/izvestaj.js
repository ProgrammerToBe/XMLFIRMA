administratorFirme.controller('IzvestajCtrl', function($scope, $http, $compile, $timeout, $rootScope, $cookies, $window, tokenService){
	
	$scope.datum = new Date();
	$scope.izvestaj = {};
	
	$scope.init = function(){
		
	}
	
	this.submitClick = function(){
		
		$http.get('/special/posaljiZahtevZaIzvestaj/'+$scope.datum).
	    then(function(response) {
	    	$scope.izvestaj = response.data;
	    });
		
	}
	
	
});