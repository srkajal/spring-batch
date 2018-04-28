package org.mallick.springbatchjob.step;

import org.springframework.batch.item.ItemProcessor;

public class Processor implements ItemProcessor<String, String> {

	public Processor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String process(String arg0) throws Exception {
		
		return arg0.toUpperCase();
	}

}
