package org.mallick.springbatchjob.config;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	public JobCompletionNotificationListener() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		//super.afterJob(jobExecution);
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			System.out.println("Job Completed");
		}
	}

	
}
