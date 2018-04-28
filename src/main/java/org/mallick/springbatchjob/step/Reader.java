package org.mallick.springbatchjob.step;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Reader implements ItemReader<String> {
	
private String[] messages = {"Hello World!", "Welcome to Spring Batch!"};
	
	private int count=0;
	
	@Value("${message}")
	private String message;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	public Reader() {
	}

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		
		logger.info("Message:{}", message);
		if(count <messages.length) {
			return messages[count++];
		}
		else {
			count = 0;
		}
		return null;
	}

}
