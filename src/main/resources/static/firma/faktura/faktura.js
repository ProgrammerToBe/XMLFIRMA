administratorFirme.controller('FakturaCtrl', function($scope, $http, $compile, $timeout, $rootScope, $cookies, $window, tokenService){
	
	$scope.faktura = {};
	$scope.saradnici = {};
	$scope.selektovaniSaradnik = {};
	
	
	$scope.init = function(){
		$scope.faktura.brojFakture = 1;
		$scope.faktura.pibDobavljaca = 1876543567;
		$scope.faktura.pibKupca = 1234567888;
		$scope.faktura.oznakaValute = 'DIN';
		$scope.faktura.vrednostRobe = 1000;
		$scope.faktura.vrednostUsluge = 200;
		$scope.faktura.ukupanRabat = 100;
		$scope.faktura.ukupanPorez = 100;
		$scope.faktura.racunZaUplatu = '123-8888-99';
		$scope.faktura.datumFakture = new Date();
		$scope.faktura.datumValute = new Date();
		
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