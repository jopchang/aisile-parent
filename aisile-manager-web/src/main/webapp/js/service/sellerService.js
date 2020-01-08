
app.service("sellerService",function($http){
	
	this.search = function(page,rows,searchEntity){
		return $http.post('../seller/search.do?page='+page+'&rows='+rows,searchEntity)
	}
	
	this.findOne = function(sellerId){
		return $http.post('../seller/findOne.do?sellerId='+sellerId)
	}
	
	this.update = function(sellerId,status){
		return $http.post('../seller/update.do?sellerId='+sellerId+'&status='+status)
	}
	
})