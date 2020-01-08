/**
 * 
 */
app.controller("specController",function($scope,$controller,specService	){
			
		$controller("baceController",{$scope:$scope})
	
			$scope.searchEntity={};
			
		
			//新增选项行
			$scope.addTableRow=function(){	
				$scope.entity.specificationOptionList.push({});		
			}
			
			//批量选项删除 
			$scope.deleTablenRow=function(index){			
				$scope.entity.specificationOptionList.splice(index,1);//删除			
			} 
		
			
			//批量删除 
			$scope.dele=function(){			
					//获取选中的复选框			
				specService.dele($scope.selectIds).success(
							function(response){
								if(response.success){
										$scope.reloadList();//刷新列表
								}						
							}		
					);				
			}
		
			//查询一条，回显
			$scope.findOne=function(id){	
				specService.findOne(id).success(
						function(response){
							console.log(response)
							$scope.entity = response
						}			
				);
			}
			//存储
			$scope.save=function(){	
				var saves = "";
				console.log($scope.entity.specification.id)
				if($scope.entity.specification.id != null){
					saves = specService.update($scope.entity)
				}else{
					saves = specService.add($scope.entity)
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
				console.log(page)
				console.log(rows)
				specService.search(page,rows,$scope.searchEntity).success(
						function(response){
							$scope.list=response.rows;	
							$scope.paginationConf.totalItems=response.total;//更新总记录数
						}			
				);
			}
			//分页 
			$scope.findPage=function(page,rows){	
				specService.findPage(page,rows).success(
						function(response){
							$scope.list=response.rows;	
							$scope.paginationConf.totalItems=response.total;//更新总记录数
						}			
				);
			}
			
			$scope.findAll=function(){
				specService.findAll.success(
					function(response){
						$scope.list=response;
					}			
				);
			}
		});