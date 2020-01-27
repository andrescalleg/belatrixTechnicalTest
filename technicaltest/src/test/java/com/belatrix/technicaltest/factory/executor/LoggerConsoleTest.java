package com.belatrix.technicaltest.factory.executor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.belatrix.technicaltest.model.MessageType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;

@RunWith(MockitoJUnitRunner.class)
public class LoggerConsoleTest {

	@Mock
	private Logger logger;

	@InjectMocks
	private LogToConsoleExecutor logToConsole;

	private LogToConsoleExecutor logToConsoleNoLog;

	private static final String ERROR = "4";

	@Before
	public void setup(){
		logToConsoleNoLog = new LogToConsoleExecutor(null);
	}

	@Test
	public void callLoggerConsoleInfo() throws Exception {
		logToConsole.execute("message", MessageType.MESSAGE);
		verify(logger, times(1)).info(any());
	}

	@Test
	public void callLoggerConsoleError() throws Exception {
		logToConsole.execute("message", MessageType.ERROR);
		verify(logger, times(1)).error(any());
	}

	@Test
	public void callLoggerConsoleWaRn() throws Exception {
		logToConsole.execute("message", MessageType.WARNING);
		verify(logger, times(1)).warn(any());
	}

	@Test
	public void callLoggerConsoleException() throws Exception {
		String message = logToConsoleNoLog.execute("message", MessageType.WARNING);
		Assert.assertEquals(message, ERROR);
	}

}
