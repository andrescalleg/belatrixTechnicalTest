package com.belatrix.technicaltest.factory.executor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.HashMap;
import java.util.Map;
import com.belatrix.technicaltest.model.MessageType;
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

	private Map params;

	@Before
	public void setup() throws Exception{
		params = new HashMap<String,String>();
		params.put("userName", "userName");
		params.put("password", "password");
		params.put("logFileFolder", "/");
		params.put("dbms", "dbms");
		params.put("serverName", "serverName");
		params.put("portNumber", "portNumber");
		//logToConsole = new LogToConsoleExecutor(logger);
	}

	@Test
	public void callLoggerConsoleInfo() throws Exception {
		logToConsole.execute("message", MessageType.MESSAGE, params);
		verify(logger, times(1)).info(any());
	}

	@Test
	public void callLoggerConsoleError() throws Exception {
		logToConsole.execute("message", MessageType.ERROR, params);
		verify(logger, times(1)).info(any());
	}

	@Test
	public void callLoggerConsoleWan() throws Exception {
		logToConsole.execute("message", MessageType.WARNING, params);
		verify(logger, times(1)).info(any());
	}

}
