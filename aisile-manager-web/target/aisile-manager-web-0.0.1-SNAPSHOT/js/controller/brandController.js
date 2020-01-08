/**
 * 
 */
app.controller("brandController",function($scope,$controller,brandService){
			
		$controller("baceController",{$scope:$scope})
	
			$scope.searchEntity={};
			
			//批量删除 
			$scope.dele=function(){			
					//获取选中的复选框			
					brandService.dele($scope.selectIds).success(
							function(response){
								if(response.success){
										$scope.reloadList();//刷新列表
								}						
							}		
					);				
			}
			
			
			//查询一条，回下
			$scope.findOne=function(id){	
				brandService.findOne(id).success(
						function(response){
							$scope.entity = response
						}			
				);
			}
			//存储
			$scope.save=function(){	
				var saves = "";
				console.log($scope.entity)
				if($scope.entity.id!=null){
					saves = brandService.update($scope.entity)
				}else{
					saves = brandService.add($scope.entity)
				}
				saves.success(
						function(response){
							if(response.success){
								$scope.reloadList();
							}else{
								alert(response.message)
							}
						}			
				);
			}
			//分页加模糊
			$scope.search=function(page,rows){	
				brandService.search(page,rows,$scope.searchEntity).success(
						function(response){
							$scope.list=response.rows;	
							$scope.paginationConf.totalItems=response.total;//更新总记录数
						}			
				);
			}
			//分页 
			$scope.findPage=function(page,rows){	
				brandService.findPage(page,rows).success(
						function(response){
							$scope.list=response.rows;	
							$scope.paginationConf.totalItems=response.total;//更新总记录数
						}			
				);
			}
			
			$scope.findAll=function(){
				brandService.findAll.success(
					function(response){
						$scope.list=response;
					}			
				);
			}
		});