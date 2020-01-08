/**
 * 
 */
app.service("itemCatService",function($http){
	
	this.findByParentId=function(parentId){
		return $http.get('../itemCat/findByParentId.do?parentId='+parentId);	
	}
	
	this.dele = function(selectIds){
		return $http.get('../itemCat/delete.do?ids='+selectIds)
	}
	
	this.findOne = function(id){
		return $http.get('../itemCat/findOne.do?id='+id)
	}
	
	this.search = function(page,rows,searchEntity){
		return $http.post('../itemCat/serach.do?page='+page+'&rows='+rows,searchEntity)
	}
	
	this.findPage = function(page,rows){
		return $http.get('../itemCat/findPage.do?page='+page+'&rows='+rows)
	}
	
	this.findAll = function(){
		return $http.get('../itemCat/list.do')
	}
	
	this.add = function(entity){
		return $http.post('../itemCat/add.do', entity)
	}
	
	this.update = function(entity){
		return $http.post('../itemCat/update.do', entity)
	}
	
	this.selectOptionList = function(){
		return $http.get('../itemCat/selectOptionList.do')
	}
	
})