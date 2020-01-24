package com.belatrix.technicaltest.logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import com.belatrix.technicaltest.factory.LoggerBelatrixFactory;
import com.belatrix.technicaltest.factory.executor.CommandExecutor;
import com.belatrix.technicaltest.model.LoggerAction;
import com.belatrix.technicaltest.model.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobLoggerChanges {

	private static Map params;

	@Autowired
	private LoggerBelatrixFactory loggerFactory;

	JobLoggerChanges() {

		params = new HashMap<String,String>();
		params.put("userName", "userName");
		params.put("password", "password");
		params.put("logFileFolder", "/");
		params.put("dbms", "dbms");
		params.put("serverName", "serverName");
		params.put("portNumber", "portNumber");
	}

	public void logMessage(String messageText, MessageType messageType, LoggerAction action) throws Exception {

		Optional<CommandExecutor> executor = loggerFactory.getLoggerExecutor(action);
		executor.get().execute(messageText,messageType, params);

	}

}
