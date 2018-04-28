package org.mallick.springbatchjob.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JobInvokerController {

	public JobInvokerController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public JobLauncher jobLauncher;
	
	@Autowired
	public Job jobProcess;
	
	@RequestMapping("/invokejob")
	public String handle() throws Exception, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		
		JobParameters jobParameter = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
		
		jobLauncher.run(jobProcess, jobParameter);
		
		return "Batch job has been invoked";
	}

}
