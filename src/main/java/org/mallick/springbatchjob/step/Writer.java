package org.mallick.springbatchjob.step;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class Writer implements ItemWriter<String> {

	public Writer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void write(List<? extends String> arg0) throws Exception {

		for (String str : arg0) {
			System.out.println("Writer Step Message:" + str);
		}

	}

}
