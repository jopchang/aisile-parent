

app.controller("sellerController",function($scope,$controller,sellerService){
		$controller("baceController",{$scope:$scope})
		
		//存改
		$scope.save = function(){
			sellerService.add($scope.entity).success(function(response){
				if(response){
					location.href="shoplogin.html"
				}else{
					alert(response.message)
				}
			})
		}
})