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

	private CommandExecutor logConsole;

	private Map<LoggerAction,CommandExecutor> actionExecutors;

	public LoggerBelatrixFactory( @Qualifier("logToConsole") CommandExecutor logToConsole){
		this.logConsole = logToConsole;
		actionExecutors = new HashMap<>();
		setExecutorsValues();
	}

	private void setExecutorsValues() {
		actionExecutors.put(LoggerAction.CONSOLE ,this.logConsole);
	}

	public Optional<CommandExecutor> getValidateExecutor(LoggerAction action){
		return Optional.of(actionExecutors.get(action));
	}
}
