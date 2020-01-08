/**
 * 前台业务层，控制方法的路径
 * @param $http
 * @returns
 */
app.service("loginService",function($http){
	this.loginName = function(){
		return $http.get("../login/name.do")
	}
})