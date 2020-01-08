/**
 * 
 */
app.controller("itemCatController",function($scope,$controller,itemCatService){
			
		$controller("baceController",{$scope:$scope})
	
			$scope.searchEntity={};
			
		$scope.findByParentId=function(parentId){
			itemCatService.findByParentId(parentId).success(
				function(response){
					$scope.list=response;
				}			
			);
		}   
		
		
		$scope.grade=1;//默认为1级	
		//设置级别
		$scope.setGrade=function(value){
			$scope.grade=value;
		}		
		//读取列表
		$scope.selectList=function(p_entity){			
			if($scope.grade==1){//如果为1级
				$scope.entity_1=null;	
				$scope.entity_2=null;
			}		
			if($scope.grade==2){//如果为2级
				$scope.entity_1=p_entity;	
				$scope.entity_2=null;
			}		
			if($scope.grade==3){//如果为3级
				$scope.entity_2=p_entity;		
			}		
			$scope.findByParentId(p_entity.id);	//查询此级下级列表
		}
		
		
			//批量删除 
			$scope.dele=function(){			
					//获取选中的复选框			
					itemCatService.dele($scope.selectIds).success(
							function(response){
								if(response.success){
										$scope.reloadList();//刷新列表
								}						
							}		
					);				
			}
			
			
			//查询一条，回下
			$scope.findOne=function(id){	
				itemCatService.findOne(id).success(
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
					saves = itemCatService.update($scope.entity)
				}else{
					saves = itemCatService.add($scope.entity)
					$scope.entity.parentId=$scope.parentId;//赋予上级ID
				}
				saves.success(
						function(response){
							if(response.success){
								//$scope.reloadList();
								$scope.findByParentId($scope.parentId);//重新加载
							}else{
								alert(response.message)
							}
						}			
				);
			}
			//分页加模糊
			$scope.search=function(page,rows){	
				itemCatService.search(page,rows,$scope.searchEntity).success(
						function(response){
							$scope.list=response.rows;	
							$scope.paginationConf.totalItems=response.total;//更新总记录数
						}			
				);
			}
			//分页 
			$scope.findPage=function(page,rows){	
				itemCatService.findPage(page,rows).success(
						function(response){
							$scope.list=response.rows;	
							$scope.paginationConf.totalItems=response.total;//更新总记录数
						}			
				);
			}
			
			$scope.findAll=function(){
				itemCatService.findAll.success(
					function(response){
						$scope.list=response;
					}			
				);
			}
		});