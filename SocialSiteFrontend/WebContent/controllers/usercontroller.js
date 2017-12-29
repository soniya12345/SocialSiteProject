/**
 * UserController
 */
app.controller('UserController',function($scope,UserService,$location,$rootScope,$cookieStore){
	
	if($rootScope.currentUser!=undefined)
		{
			UserService.getUser().then(function(response){
				$scope.user=response.data
			},
			function(response)
			{
				if(response.status==401)
					{
						$location.path('/login')				
					}
			})
		}
	
	
	$scope.registerUser=function() //2
	{
		console.log($scope.user)
		UserService.registerUser($scope.user)//3
		.then(function(response){
			$location.path('/login')
		},
		function(response)
		{
			console.log(response.data)
			console.log(response.status)
			$scope.error=response.data  //ErrorClazz object in JSON
		})
	}
	
	$scope.login=function()
	{
		UserService.login($scope.user).then(function(response)
				{
					$rootScope.currentUser=response.data
					$cookieStore.put('currentUser',response.data)
					$location.path('/home')
				},
				function(response)
				{
					if(response.status==401)
					{
						$scope.error=response.data//errorClazz in JSON format
						$location.path('/login')
					}
				})
	}
	
	$scope.editUserProfile=function()
	{
		UserService.editUserProfile($scope.user).then(function(response)
				{
					alert('Updated Successfull')
					$location.path('/home')
				},
				function(response)
				{
				if(response.status==401)
					$location.path('/login')
				if(response.status==500)
					{
						$scope.error=response.data
						$location.path('/editprofile')
					}
		})
	}
	
	
})