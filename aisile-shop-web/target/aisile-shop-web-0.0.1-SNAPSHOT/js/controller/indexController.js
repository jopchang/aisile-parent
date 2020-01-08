app.controller("indexController",function($scope,$controller,loginService){
	
	
	$scope.selectItemCat1List=function(){
	      itemCatService.findByParentId(0).success(
	    		 function(response){
	    			 $scope.itemCat1List=response; 
	    		 }
	      );
	}
	
	
	
	
	/*$scope.showLoginName=function(){
		loginService.loginName().success(function(response){
			console.log(response)
			$scope.loginName = response.loginName
		})
	}*/
})