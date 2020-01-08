

app.controller("sellerController",function($scope,$controller,sellerService){
		$controller("baceController",{$scope:$scope})
		
		$scope.searchEntity={}
		
		//分页加模糊
		$scope.search=function(page,rows){	
			console.log($scope.searchEntity)
			console.log(page)
			console.log(rows)
			sellerService.search(page,rows,$scope.searchEntity).success(function(response){
						$scope.list=response.rows;	
						$scope.paginationConf.totalItems=response.total;//更新总记录数
					}			
			);
		}
		
		$scope.findOne = function(sellerId){
			sellerService.findOne(sellerId).success(function(response){
				$scope.entity = response
			})
		}
		
		$scope.updateStatus = function(sellerId,status){
			sellerService.update(sellerId,status).success(function(response){
				if(response.success){
					$scope.reloadList();//刷新列表
				}else{
					alert("失败")
				}
			})
		}
})