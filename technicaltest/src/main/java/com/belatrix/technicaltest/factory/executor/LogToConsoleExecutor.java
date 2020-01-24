package com.belatrix.technicaltest.factory.executor;

import java.util.Map;
import com.belatrix.technicaltest.model.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("logToConsole")
public class LogToConsoleExecutor implements CommandExecutor {

	private Logger consoleLogger;

	public LogToConsoleExecutor(Logger consoleLogger) {
		this.consoleLogger = consoleLogger;
	}

	private static final Logger logger = LoggerFactory.getLogger(LogToConsoleExecutor.class);

	@Override
	public void execute(String messageText, MessageType messageType, Map dbParams) {
		logger.info("Executing log to console");
		switch (messageType){
			case MESSAGE:
				consoleLogger.info(messageText);
				break;
			case ERROR:
				consoleLogger.error(messageText);
				break;
			case WARNING:
				consoleLogger.warn(messageText);
				break;
		}
		logger.info("Finish executing log console");
	}


}
