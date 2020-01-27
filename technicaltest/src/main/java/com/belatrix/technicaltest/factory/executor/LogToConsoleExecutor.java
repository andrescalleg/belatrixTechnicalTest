package com.belatrix.technicaltest.factory.executor;

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

	private static final String ERROR = "4";

	@Override
	public String execute(String messageText, MessageType messageType) {
		logger.info("Executing log to console");
		try {
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
		}catch (Exception e){
			logger.error("Error executing log console of message [{}]{}", messageType.name(),e);
			return ERROR;
		}

		logger.info("Finish executing log console of message [{}]", messageType.name());
		return messageType.getValue();
	}


}
