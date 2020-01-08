/**
 * 
 */
app.service("specService",function($http){
	
	
	this.dele = function(selectIds){
		return $http.get('../spec/delete.do?ids='+selectIds)
	}
	
	this.findOne = function(id){
		return $http.get('../spec/findOne.do?id='+id)
	}
	
	this.search = function(page,rows,searchEntity){
		return $http.post('../spec/search.do?page='+page+'&rows='+rows,searchEntity)
	}
	
	this.findPage = function(page,rows){
		return $http.get('../spec/findPage.do?page='+page+'&rows='+rows)
	}
	
	this.findAll = function(){
		return $http.get('../spec/list.do')
	}
	
	this.add = function(entity){
		return $http.post('../spec/add.do', entity)
	}
	
	this.update = function(entity){
		return $http.post('../spec/update.do', entity)
	}
	
	this.selectOptionList = function(){
		return $http.post('../spec/selectOptionList.do')
	}
	
})