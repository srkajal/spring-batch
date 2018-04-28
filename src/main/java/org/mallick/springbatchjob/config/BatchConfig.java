package org.mallick.springbatchjob.config;

import org.mallick.springbatchjob.step.Processor;
import org.mallick.springbatchjob.step.Reader;
import org.mallick.springbatchjob.step.Writer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	public BatchConfig() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job job(JobCompletionNotificationListener listener) {
		return jobBuilderFactory.get("job1").incrementer(new RunIdIncrementer()).listener(listener).flow(step1()).end().build();

	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<String, String>chunk(1).reader(new Reader()).processor(new Processor())
				.writer(new Writer()).build();
	}

}
