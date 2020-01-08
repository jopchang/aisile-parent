/**
 * 前台业务层，控制方法的路径
 * @param $http
 * @returns
 */
app.service("goodsService",function($http){
	
	this.loginName = function(entity){
		return $http.post("../goods/add.do",entity)
	}
	
})