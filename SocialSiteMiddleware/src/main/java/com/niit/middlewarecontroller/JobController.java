package com.niit.middlewarecontroller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.Dao.JobDao;
import com.niit.Dao.UserDao;
import com.niit.Model.ErrorClazz;
import com.niit.Model.Job;
import com.niit.Model.User;

@Controller
public class JobController {
	@Autowired
	private JobDao jobDao;
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value="/savejob",method=RequestMethod.POST)
	public ResponseEntity<?> saveJob(@RequestBody Job job,HttpSession session)
	{
		String username=(String)session.getAttribute("username");
		if(username==null)
		{
			ErrorClazz error = new ErrorClazz(5,"Unauthorized Access");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		
		User user = userDao.getUserByUsername(username);
		if(!user.getRole().equals("ADMIN"))
		{
			ErrorClazz error = new ErrorClazz(6,"Access Denied");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		
		job.setPostedOn(new Date());
		
		try 
		{
			jobDao.saveJob(job);
		}
		catch(Exception e)
		{
			ErrorClazz error = new ErrorClazz(7,"Unable insert job details " +e.getMessage());
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Job>(job,HttpStatus.OK);
	}

	@RequestMapping(value="/alljobs",method=RequestMethod.GET)
	public ResponseEntity<?> getAllJob(HttpSession session)
	{
		String username=(String)session.getAttribute("username");
		if(username==null)
		{
			ErrorClazz error = new ErrorClazz(5,"Unauthorized Access");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		
		List<Job> jobs = jobDao.getAllJobs();
		return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
	}
	
	@RequestMapping(value="/getjob/{jobId}",method=RequestMethod.GET)
	public ResponseEntity<?> getJob(@PathVariable int jobId,HttpSession session)
	{
		String username=(String)session.getAttribute("username");
		if(username==null)
		{
			ErrorClazz error = new ErrorClazz(5,"Unauthorized Access");
			return new ResponseEntity<ErrorClazz>(error,HttpStatus.UNAUTHORIZED);
		}
		
		Job job=jobDao.getJob(jobId);
		return new ResponseEntity<Job>(job,HttpStatus.OK);
	}
}

