package org.mallick.springbatchjob;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
public class SpringbatchjobApplication {

	public static void main(String[] args) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		SpringApplication app = new SpringApplication(SpringbatchjobApplication.class);
		//app.setWebEnvironment(false);
		ConfigurableApplicationContext ctx = app.run(args);
		
		JobLauncher joblauncjer = ctx.getBean(JobLauncher.class);
		
		Job job1 = ctx.getBean(Job.class);
		
		JobParameters jobParameter = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
		
		joblauncjer.run(job1, jobParameter);
		
		System.out.println("End of Batch Job");
		
		System.exit(0);
		
		//SpringApplication.run(SpringbatchjobApplication.class, args);
	}
}
