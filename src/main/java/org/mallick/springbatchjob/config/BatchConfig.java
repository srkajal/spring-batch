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
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableBatchProcessing
public class BatchConfig {

	public BatchConfig() {
	}

	@Autowired
	public Reader reader;
	
	@Autowired
	public Processor processor;
	
	@Autowired
	public Writer writer;
	
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
		return stepBuilderFactory.get("step1")
				.<String, String>chunk(1)
				.reader(reader)
				.processor(processor)
				.writer(writer).build();
	}

}
