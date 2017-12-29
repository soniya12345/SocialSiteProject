/**
 * UserService
 */
app.factory('UserService',function($http){
	var BASE_URL="http://localhost:8090/collaborationmiddleware"
	
	var userService={}
	
	userService.registerUser=function(user)
	{
		//http://localhost:8090/collaborationmiddleware  /registeruser
		//BASE_URL                                + "/registeruser"
		console.log(user)
		return $http.post(BASE_URL + "/registerUser",user)//4
		//8
	}
	
	userService.login=function(user)
	{
		return $http.post(BASE_URL + "/login",user)
	}
	
	userService.logout=function()
	{
		return $http.get(BASE_URL + "/logout")
	}
	
	userService.getUser=function()
	{
		return $http.get(BASE_URL + "/getuser")
	}
	
	userService.editUserProfile=function(user)
	{
		return $http.put(BASE_URL + "/edituserprofile",user)
	}
	
	return userService;
})