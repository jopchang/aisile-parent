/**
 * 
 */
app.service("brandService",function($http){
	
	this.dele = function(selectIds){
		return $http.get('../brand/delete.do?ids='+selectIds)
	}
	
	this.findOne = function(id){
		return $http.get('../brand/findOne.do?id='+id)
	}
	
	this.search = function(page,rows,searchEntity){
		return $http.post('../brand/serach.do?page='+page+'&rows='+rows,searchEntity)
	}
	
	this.findPage = function(page,rows){
		return $http.get('../brand/findPage.do?page='+page+'&rows='+rows)
	}
	
	this.findAll = function(){
		return $http.get('../brand/list.do')
	}
	
	this.add = function(entity){
		return $http.post('../brand/add.do', entity)
	}
	
	this.update = function(entity){
		return $http.post('../brand/update.do', entity)
	}
	
	this.selectOptionList = function(){
		return $http.get('../brand/selectOptionList.do')
	}
	
})