

app.controller("goodsController",function($scope,$controller,goodsService,uploadService,itemCatService,typeTemplateService){
	
		$controller("baceController",{$scope:$scope})
		
		$scope.entity={goods:{},goodsDesc:{itemImages:[],specificationItems:[]}};
		
		$scope.add_image_entity=function(){
			$scope.entity.goodsDesc.itemImages.push($scope.image_entity)
		}
		
		$scope.updateSpecAttribute=function($event,name,value){
			var object= $scope.searchObjectByKey($scope.entity.goodsDesc.specificationItems ,'attributeName', name);		
				if(object!=null){	
					if($event.target.checked ){
						object.attributeValue.push(value);		
					}else{//取消勾选				object.attributeValue.splice( object.attributeValue.indexOf(value ) ,1);//移除选项
						//如果选项都取消了，将此条记录移除
						if(object.attributeValue.length==0){
							$scope.entity.goodsDesc.specificationItems.splice(
			$scope.entity.goodsDesc.specificationItems.indexOf(object),1);
						}				
					}
				}else{				
		$scope.entity.goodsDesc.specificationItems.push(
		{"attributeName":name,"attributeValue":[value]});
				}		
			}
		
		
		
		$scope.fileUpload=function(){
			uploadService.uploadFile().success(function(response){
				if(response.success){
					$scope.image_entity.url=response.message;
				}else{
					alert(response.message);
				}
			})
		}
		
		
		$scope.selectItemCat1List=function(){
		      itemCatService.findByParentId(0).success(
		    		 function(response){
		    			 $scope.itemCat1List=response; 
		    		 }
		      );
		}
		
		$scope.$watch('entity.goods.category1Id', function(newValue, oldValue) {          
	    	//根据选择的值，查询二级分类
	    	itemCatService.findByParentId(newValue).success(
	    		function(response){
	    			$scope.itemCat2List=response; 	    			
	    		}
	    	);    	
	}); 
		
		$scope.$watch('entity.goods.category2Id', function(newValue, oldValue) {          
	    	//根据选择的值，查询三级分类
	    	itemCatService.findByParentId(newValue).success(
	    		function(response){
	    			$scope.itemCat3List=response; 	    			
	    		}
	    	);    	
	}); 
		//监听三级分类查询模板
		 $scope.$watch('entity.goods.category3Id', function(newValue, oldValue) {    
		       	itemCatService.findOne(newValue).success(
		       		  function(response){
		       			    $scope.entity.goods.typeTemplateId=response.typeId; //更新模板ID    
		       		  }
		        );    
		    }); 
		
		 $scope.$watch('entity.goods.typeTemplateId', function(newValue, oldValue) {    
			 	typeTemplateService.findOne(newValue).success(
		       		function(response){
		       			  $scope.typeTemplate=response;//获取类型模板
		       			  $scope.typeTemplate.brandIds= JSON.parse( $scope.typeTemplate.brandIds);//品牌列表
		       			  $scope.entity.goodsDesc.customAttributeItems=JSON.parse( $scope.typeTemplate.customAttributeItems);
		       			typeTemplateService.findSpecList(newValue).success(
		       	    		  function(response){
		       	    			  $scope.specList=response;
		       	    		  }
		       	    	); 
		       		}
		     );    
		}); 
		 
		 
		$scope.add=function(){		
			$scope.entity.goodsDesc.introduction=editor.html();
			goodsService.add( $scope.entity ).success(
				function(response){
					if(response.success){
						alert('保存成功');	
						editor.html('');
						$scope.entity={};
					}else{
						alert(response.message);
					}
				}		
			);				
		}
		
		
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