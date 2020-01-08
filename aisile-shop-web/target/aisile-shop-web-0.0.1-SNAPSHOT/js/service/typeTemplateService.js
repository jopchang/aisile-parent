/**
 * 
 */
app.service("typeTemplateService",function($http){
	
	
	this.dele = function(selectIds){
		return $http.get('../typeTemplate/delete.do?ids='+selectIds)
	}
	
	this.findOne = function(id){
		return $http.get('../typeTemplate/findOne.do?id='+id)
	}
	
	this.search = function(page,rows,searchEntity){
		return $http.post('../typeTemplate/search.do?page='+page+'&rows='+rows,searchEntity)
	}
	
	this.findPage = function(page,rows){
		return $http.get('../typeTemplate/findPage.do?page='+page+'&rows='+rows)
	}
	
	this.findAll = function(){
		return $http.get('../typeTemplate/list.do')
	}
	
	this.add = function(entity){
		return $http.post('../typeTemplate/add.do', entity)
	}
	
	this.update = function(entity){
		return $http.post('../typeTemplate/update.do', entity)
	}
	
	this.findSpecList=function(id){
		return $http.get('../typeTemplate/findSpecList.do?id='+id);
	}
	
})