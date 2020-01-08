/**
 * 
 */
app.controller("typeTemplateController",function($scope,$controller,typeTemplateService,brandService,specService){
			
		$controller("baceController",{$scope:$scope})
	
			$scope.searchEntity={};
			$scope.brandList = {data:[]};
			$scope.specList = {data:[]};
		
			//批量删除 
			$scope.dele=function(){			
					//获取选中的复选框			
				typeTemplateService.dele($scope.selectIds).success(
							function(response){
								if(response.success){
										$scope.reloadList();//刷新列表
								}						
							}		
					);				
			}
			
			
			//新增扩展属性行
			$scope.addTableRow=function(){	
				$scope.entity.customAttributeItems.push({});		
			}
			//新增扩展属性行
			$scope.deleteTableRow=function(index){	
				$scope.entity.customAttributeItems.splice(index,1);		
			}
			
			
			//第一個下拉框賦值
			$scope.brandOptionList = function(){
				brandService.selectOptionList().success(function(response){
					$scope.brandList.data = response;
				})
			}
			//第二個下拉框賦值
			$scope.specOptionList = function(){
				specService.selectOptionList().success(function(response){
					$scope.specList.data = response;
				})
			}
			
			//查询一条，回下
			$scope.findOne=function(id){	
				typeTemplateService.findOne(id).success(
						function(response){
							console.log(response)
							$scope.entity = response
							
							$scope.entity.brandIds=  JSON.parse($scope.entity.brandIds);//转换品牌列表
							$scope.entity.specIds=  JSON.parse($scope.entity.specIds);//转换规格列表
							$scope.entity.customAttributeItems= JSON.parse($scope.entity.customAttributeItems);//转换扩展属性
						}			
				);
			}
			//存储
			$scope.save=function(){	
				var saves = "";
				console.log($scope.entity.id)
				if($scope.entity.id != null){
					saves = typeTemplateService.update($scope.entity)
				}else{
					saves = typeTemplateService.add($scope.entity)
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
				typeTemplateService.search(page,rows,$scope.searchEntity).success(
						function(response){
							$scope.list=response.rows;	
							$scope.paginationConf.totalItems=response.total;//更新总记录数
						}			
				);
			}
			//分页 
			$scope.findPage=function(page,rows){	
				typeTemplateService.findPage(page,rows).success(
						function(response){
							$scope.list=response.rows;	
							$scope.paginationConf.totalItems=response.total;//更新总记录数
						}			
				);
			}
			
			$scope.findAll=function(){
				typeTemplateService.findAll.success(
					function(response){
						$scope.list=response;
					}			
				);
			}
		});