package com.belatrix.technicaltest.logger;

import java.util.Optional;
import com.belatrix.technicaltest.factory.LoggerBelatrixFactory;
import com.belatrix.technicaltest.factory.executor.CommandExecutor;
import com.belatrix.technicaltest.model.LoggerAction;
import com.belatrix.technicaltest.model.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobLoggerChanges {

	@Autowired
	private LoggerBelatrixFactory loggerFactory;

	public void logMessage(String messageText, MessageType messageType, LoggerAction action) throws Exception {

		Optional<CommandExecutor> executor = loggerFactory.getLoggerExecutor(action);
		executor.get().execute(messageText,messageType);

	}

}
