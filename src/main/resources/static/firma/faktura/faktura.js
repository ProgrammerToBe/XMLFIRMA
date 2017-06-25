administratorFirme.controller('FakturaCtrl', function($scope, $http, $compile, $timeout, $rootScope, $cookies, $window, tokenService){
	
	$scope.faktura = {};
	$scope.saradnici = {};
	$scope.selektovaniSaradnik = {};
	$scope.stavke=[];
	$scope.dodateStavke=[];
	$scope.postojeStavke=false;
	$scope.faktura.artikli=[];
	
	$scope.init = function(){
		$scope.faktura.brojFakture = 1;
		$scope.faktura.pibDobavljaca = 1876543567;
		$scope.faktura.pibKupca = 1234567888;
		$scope.faktura.oznakaValute = 'DIN';
		$scope.faktura.vrednostRobe = 1000;
		$scope.faktura.vrednostUsluge = 200;
		$scope.faktura.ukupanRabat = 100;
		$scope.faktura.ukupanPorez = 100;
		$scope.faktura.racunZaUplatu = '234-7184821064803-82';
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
	this.loadArtikle=function(){
		
		$http({
		    method: 'GET',
		    url: '/artikli'
		   
		}).
		then(function mySucces(response) {
				
				toastr.info("Ucitani artikle!");
				$scope.stavke=response.data;
				

		});
	}
	this.saradnici = function(){
		$http.get('/special/getPartners').
	    then(function(response) {
	    	$scope.saradnici = response.data;
	    });
	}
	this.dodajStavke=function(){
		 // var selectedStavke = [];
		    var aletrMsg= '';
		    angular.forEach($scope.stavke, function(stavka) {
		        if (stavka.selected) {
		        	$scope.dodateStavke.push(stavka);
		        	$scope.faktura.artikli.push(stavka);
		       // aletrMsg += 'Brand : '+ phone.brandname + 'Phone Name: '+ phone.modelname + ' : Price: '+ phone.price +', ';
		      }
		    });
		    if($scope.dodateStavke.length>0){
		    	$scope.postojeStavke=true;
		    	calculateValueRobe();
		    //	$scope.faktura.vrednostRobe=
		    }
			toastr.info("Ucitani artikle! "+ $scope.dodateStavke.length);
		    
	}
	this.calculateAgain=function(){
		calculateValueRobe();
	}
	var  calculateValueRobe=function(){
		var vrijednostRobe=0;
		
		var ukupanRabat=0;
		var vrijednostPoreza=0;
		   angular.forEach($scope.faktura.artikli, function(stavka) {
			   toastr.info("Stavka kolicina "+stavka.kolicina);
		      vrijednostRobe+=stavka.kolicina*stavka.jedinicnaCjena;
		      ukupanRabat+=stavka.kolicina*stavka.jedinicnaCjena*stavka.popust/100;
		      vrijednostPoreza+=(stavka.kolicina*stavka.jedinicnaCjena-stavka.kolicina*stavka.jedinicnaCjena*(stavka.popust/100))*(stavka.pdv/100);
		   
		      
		    });
		   var vrijednostRobeGlavnica = vrijednostRobe - ukupanRabat + vrijednostPoreza;
		   $scope.faktura.vrednostRobe=vrijednostRobeGlavnica;
		   $scope.faktura.ukupanRabat=ukupanRabat;
		   $scope.faktura.ukupanPorez= vrijednostPoreza;
		//   return vrijednostRobe;
	}
	this.postojeStavke=function(){
		return $scope.postojeStavke;
	}
	this.otkazi=function(){
		toastr.info("Otkazi!");
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