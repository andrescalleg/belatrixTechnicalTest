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

@SpringBootApplication
public class TechnicaltestApplication implements CommandLineRunner {

	private static Logger LOG = LoggerFactory.getLogger(TechnicaltestApplication.class);

	@Autowired
	private JobLoggerChanges jobLog;

	public static void main(String[] args) {
		LOG.info("STARTING THE APPLICATION");
		SpringApplication.run(TechnicaltestApplication.class, args);
		LOG.info("APPLICATION FINISHED");
	}

	@Override
	public void run(String... args) throws Exception {
		LOG.info("EXECUTING : command line runner");
		jobLog.logMessage("message test", MessageType.MESSAGE, LoggerAction.CONSOLE);

		for (int i = 0; i < args.length; ++i) {
			LOG.info("args[{}]: {}", i, args[i]);
		}
	}
}
