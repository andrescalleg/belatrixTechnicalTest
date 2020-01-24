package com.belatrix.technicaltest.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import com.belatrix.technicaltest.factory.executor.CommandExecutor;
import com.belatrix.technicaltest.model.LoggerAction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class LoggerBelatrixFactory {

	private CommandExecutor logToConsole;

	private CommandExecutor logToFile;

	private CommandExecutor logToDatabase;

	private Map<LoggerAction,CommandExecutor> actionExecutors;

	public LoggerBelatrixFactory( @Qualifier("logToConsole") CommandExecutor logToConsole,
	                              @Qualifier("logToFile")CommandExecutor logToFile,
	                              @Qualifier("logToDatabase")CommandExecutor logToDatabase
	                             ) {
		this.logToConsole = logToConsole;
		this.logToDatabase = logToDatabase;
		this.logToFile = logToFile;
		actionExecutors = new HashMap<>();
		setExecutorsValues();
	}

	private void setExecutorsValues() {
		actionExecutors.put(LoggerAction.CONSOLE ,this.logToConsole);
		actionExecutors.put(LoggerAction.DATA_BASE ,this.logToDatabase);
		actionExecutors.put(LoggerAction.FILE ,this.logToFile);
	}

	public Optional<CommandExecutor> getLoggerExecutor(LoggerAction action){
		return Optional.of(actionExecutors.get(action));
	}
}
