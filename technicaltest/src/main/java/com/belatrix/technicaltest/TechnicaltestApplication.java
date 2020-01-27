package com.belatrix.technicaltest;

import com.belatrix.technicaltest.logger.JobLoggerChanges;
import com.belatrix.technicaltest.model.LoggerAction;
import com.belatrix.technicaltest.model.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TechnicaltestApplication implements CommandLineRunner {

	private static Logger logger = LoggerFactory.getLogger(TechnicaltestApplication.class);

	@Autowired
	private JobLoggerChanges jobLog;

	public static void main(String[] args) {
		logger.info("STARTING THE APPLICATION");
		SpringApplication.run(TechnicaltestApplication.class, args);
		logger.info("APPLICATION FINISHED");
	}

	@Bean
	public Logger consoleLogger(){
		return LoggerFactory.getLogger("consoleLogger");
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("EXECUTING : command line runner");

		String message = "message test";
		jobLog.logMessage(message, MessageType.MESSAGE, LoggerAction.CONSOLE);
		jobLog.logMessage(message, MessageType.MESSAGE, LoggerAction.FILE);

		logger.info("FINISH EXECUTION : command line runner");

	}
}
