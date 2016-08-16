package com.hashi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SyncTaskExecutor;

@Configuration
public class AppBeans {
	@Bean
	public SyncTaskExecutor syncTaskExecutor() {
		return new SyncTaskExecutor();
	}

}
