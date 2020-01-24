package com.belatrix.technicaltest.factory.executor;

import java.util.Optional;
import com.belatrix.technicaltest.factory.LoggerBelatrixFactory;
import com.belatrix.technicaltest.model.LoggerAction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FactoryTest {

	@Mock(name = "logToConsole")
	CommandExecutor logToConsole;

	@Mock(name = "logToFile")
	CommandExecutor logToFile;

	@Mock(name = "logToDatabase")
	CommandExecutor logToDatabase;

	LoggerBelatrixFactory loggerFactory;

	@Before
	public void setup(){
		loggerFactory = new LoggerBelatrixFactory(logToConsole, logToFile, logToDatabase);
	}

	@Test
	public void callLoggerConsoleInfo(){
		Optional<CommandExecutor> executor = loggerFactory.getLoggerExecutor(LoggerAction.CONSOLE);
		Assert.assertEquals(executor.get(),logToConsole);
	}

	@Test
	public void callLoggerDatabaseInfo(){
		Optional<CommandExecutor> executor = loggerFactory.getLoggerExecutor(LoggerAction.DATA_BASE);
		Assert.assertEquals(executor.get(),logToDatabase);
	}

	@Test
	public void callLoggerTextInfo(){
		Optional<CommandExecutor> executor = loggerFactory.getLoggerExecutor(LoggerAction.FILE);
		Assert.assertEquals(executor.get(),logToFile);
	}
}
