administratorFirme.controller('FaktureCtrl', function($scope, $http, $compile, $timeout, $rootScope, $cookies, $window, tokenService){
	
	$scope.fakture = [];
	$scope.saradnici = {};
	$scope.selektovaniSaradnik = {};
	$scope.fakturaDTO={};
	
	
	$scope.init = function(){

		
		$http({
		    method: 'GET',
		    url: '/fakture',
		  }).
		then(
				function successCallback(response){
					$scope.fakture=response.data;
				},
				function errorCallback(response){
					
				}
		);		
	}
	
	
	this.pokupiPodatke=function(faktura){
		$scope.fakturaDTO.faktura=faktura;
	
	}
	this.plati=function(){
		$http({
		    method: 'POST',
		    url: '/platiFaktura',
		    data: $scope.fakturaDTO
		    
		  }).
		then(
				function successCallback(response){

					$scope.fakturaDTO={};
				},
				function errorCallback(response){
					$scope.fakturaDTO={};
				}
		);	
	}
	this.odustani=function(){
		$scope.fakturaDTO.svrhaUplate="";
		$scope.fakturaDTO.racunDuznika="";
		$scope.fakturaDTO.rmodelDuznika="";
		$scope.fakturaDTO.pozivNaBrojDuznika="";
		$scope.fakturaDTO.racunDuznika="";
		$scope.fakturaDTO.racunPoverioca="";
		$scope.fakturaDTO.pozivNaBrojPoverioca="";
		$scope.fakturaDTO.modelPoverioca="";
		$scope.fakturaDTO.hitno=false;
	}
	this.submitClick = function(){
		
		$http({
		    method: 'POST',
		    url: '/special/posaljiFakturu',
		    data: $scope.faktura,
		   
		}).
		then(function mySucces(response) {
				
				toastr.info("Proslo!")
//				$scope.faktura = {};
//				$scope.saradnici = {};
				$scope.selektovaniSaradnik = {};
		});
		
		
	}
	
	this.saradnici = function(){
		$http.get('/special/getPartners').
	    then(function(response) {
	    	$scope.saradnici = response.data;
	    });
	}
	
	this.setSelectedSaradnik = function(s){
		$scope.selektovaniSaradnik = s.pib;
	}
	
	this.dismis = function(){
		$scope.selektovaniSaradnik = {};
	}
	
	this.conf = function(){
		$scope.faktura.pibKupca = $scope.selektovaniSaradnik;
	}
});