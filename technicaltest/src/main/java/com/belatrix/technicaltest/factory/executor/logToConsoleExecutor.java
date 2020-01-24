package com.belatrix.technicaltest.factory.executor;

import com.belatrix.technicaltest.model.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("logToConsole")
public class logToConsoleExecutor implements CommandExecutor {

	private static final Logger logger = LoggerFactory.getLogger(logToConsoleExecutor.class);

	@Override
	public void execute(String messageText, MessageType messageType) {
		switch (messageType){
			case MESSAGE:
				logger.info(messageText);
				break;
			case ERROR:
				logger.error(messageText);
				break;
			case WARNING:
				logger.warn(messageText);
				break;
		}
	}
}
