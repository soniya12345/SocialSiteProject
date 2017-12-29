/**
 * Job Service
 */
app.factory('JobService',function($http){
	var jobService={}
	
	jobService.addJob=function(job)
	{
		return $http.post("http://localhost:8090/collaborationmiddleware/savejob",job);
	}
	
	jobService.getAllJobs=function()
	{
		return $http.get("http://localhost:8090/collaborationmiddleware/alljobs")
	}
	
	jobService.getJob=function(jobId)
	{
		return $http.get("http://localhost:8090/collaborationmiddleware/getjob/"+jobId)
	}
	
	return jobService;
})